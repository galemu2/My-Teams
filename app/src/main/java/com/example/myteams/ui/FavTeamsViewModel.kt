package com.example.myteams.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myteams.data.models.Teams
import com.example.myteams.repositories.SportsRepository
import com.example.myteams.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class FavTeamsViewModel @Inject constructor(
    private val repository: SportsRepository
) : ViewModel() {


    companion object {
        const val DEFAULT_QUERY = "Arsenal"
    }


    init {
        // todo load saved teams
        // searchFavTeam()
    }

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


}