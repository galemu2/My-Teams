package com.example.myteams.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myteams.data.models.FavTeam
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

    private val _searchTeam = mutableStateOf<Resource<Teams>>(Resource.Loading())
    val searchTeam: MutableState<Resource<Teams>>
        get() = _searchTeam

    fun searchFavTeam(searchQuery: String = "fc") {
        viewModelScope.launch {
            val res = repository.getTeams(query = searchQuery)
            _searchTeam.value = handleTeamSearch(res)
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

    var favTeams: StateFlow<List<FavTeam>> = favTeamsRepository
        .getAllFavTeams.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun addFavTeam(favTeam: FavTeam) {
        viewModelScope.launch {

            favTeamsRepository.addFavTeam(favTeam = favTeam)
        }
    }

    fun deleteFavTeam(favTeam: FavTeam) {
        viewModelScope.launch {
            favTeamsRepository.deleteFavTeam(favTeam = favTeam)
        }
    }

}