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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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
    val teamTobeDeleted: MutableState<Team?> = mutableStateOf(null)

    private val _searchTeam = mutableStateOf<Resource<Teams>>(Resource.Loading())
    val searchTeam: MutableState<Resource<Teams>>
        get() = _searchTeam

    fun searchFavTeam(searchQuery: String) {
        viewModelScope.launch {
            val res = repository.getTeams(query = searchQuery)
            _searchTeam.value = handleTeamSearch(res)
        }
    }


    private val _teamMatches = mutableStateOf<Resource<Matches>>(Resource.Loading())
    val teamMatches: MutableState<Resource<Matches>>
        get() = _teamMatches

    fun listMatches(matchId:String){
        viewModelScope.launch {
            val res = repository.getMatches(matchId = matchId)
            _teamMatches.value = handleMatchSearchSearch(res)
        }
    }

    private fun handleMatchSearchSearch(res: Response<Matches>): Resource<Matches> {
        if (res.isSuccessful) {
            res.body()?.let { teams ->
                return Resource.Success(teams)
            }
        }
        return Resource.Error(message = res.message())
    }

    private fun handleTeamSearch(res: Response<Teams>): Resource<Teams> {
        if (res.isSuccessful) {
            res.body()?.let { teams ->
                return Resource.Success(teams)
            }
        }
        return Resource.Error(message = res.message())
    }

    var favTeams: StateFlow<List<Team>> = favTeamsRepository
        .getAllFavTeams.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun addFavTeam(favTeam: Team) {
        viewModelScope.launch {

            favTeamsRepository.addFavTeam(favTeam = favTeam)
        }
    }

    fun deleteFavTeam(favTeam: Team) {
        viewModelScope.launch {
            favTeamsRepository.deleteFavTeam(favTeam = favTeam)
        }
    }

}