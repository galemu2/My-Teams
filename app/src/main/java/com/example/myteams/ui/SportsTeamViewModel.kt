package com.example.myteams.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myteams.data.models.Matches
import com.example.myteams.data.models.Team
import com.example.myteams.data.models.Teams
import com.example.myteams.repositories.FavTeamsRepository
import com.example.myteams.repositories.SportsRepository
import com.example.myteams.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class SportsTeamViewModel @Inject constructor(
    private val repository: SportsRepository,
    private val favTeamsRepository: FavTeamsRepository
) : ViewModel() {

    val searchTextState: MutableState<String> = mutableStateOf("")
    val searchAppBarOpenState: MutableState<Boolean> = mutableStateOf(false)
    var teamTobeDeleted: MutableState<Team?> = mutableStateOf(null)


    private val _searchTeam = mutableStateOf<Resource<Teams>>(Resource.Loading())
    val searchTeam: MutableState<Resource<Teams>>
        get() = _searchTeam

    fun searchFavTeam(searchQuery: String) {
        viewModelScope.launch {

            try {
                val res = repository.getTeams(query = searchQuery)
                _searchTeam.value = handleTeamSearch(res)
            } catch (
                e: Exception
            ) {
                _searchTeam.value = Resource.Error(message = e.message ?: "Unknown Error")
            }

        }
    }

    private fun handleTeamSearch(res: Response<Teams>): Resource<Teams> {
        if (res.isSuccessful) {
            res.body()?.let { teams ->
                return Resource.Success(teams)
            }
        }
        return Resource.Error(message = res.message())
    }

    // team match search
    private val _teamMatches = mutableStateOf<Resource<Matches>>(Resource.Loading())
    val teamMatches: MutableState<Resource<Matches>>
        get() = _teamMatches

    fun getTeamMatchHistory(matchId: String) {

        viewModelScope.launch {
            try {
                val res = repository.getMatches(matchId = matchId)
                _teamMatches.value = matchSearchSearch(res)
            } catch (e: Exception) {
                _teamMatches.value = Resource.Error(message = e.message ?: "Unknon error")
            }


        }
    }

    private fun matchSearchSearch(res: Response<Matches>): Resource<Matches> {
        if (res.isSuccessful) {
            res.body()?.let { teams ->
                return Resource.Success(teams)
            }
        }
        return Resource.Error(message = res.message())
    }


    // fav teams
    private val _getFavTeams = MutableStateFlow<Resource<List<Team>>>(Resource.Loading())
    val getFavTeams: StateFlow<Resource<List<Team>>>
        get() = _getFavTeams

    private fun getAllFavTeams() {
        try {
            viewModelScope.launch {
                favTeamsRepository.getAllFavTeams.collect {
                    _getFavTeams.value = Resource.Success(it)
                }
            }
        } catch (e: Exception) {
            _getFavTeams.value = Resource.Error(message = e.message.toString())
        }
    }

    fun addFavTeam(favTeam: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            favTeamsRepository.addFavTeam(favTeam = favTeam)
        }
    }

    fun deleteFavTeam(favTeam: Team) {
        viewModelScope.launch(Dispatchers.Main) {
            favTeamsRepository.deleteFavTeam(favTeam = favTeam)
        }
    }

    init {
        getAllFavTeams()
    }
}