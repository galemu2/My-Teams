package com.example.myteams.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myteams.data.models.Teams
import com.example.myteams.repositories.SportsRepository
import com.example.myteams.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        searchFavTeam()
    }

    private val _searchTeam = MutableStateFlow<Resource<Teams>>(Resource.Loading())

    val searchTeam: StateFlow<Resource<Teams>>
        get() = _searchTeam

    fun searchFavTeam() {
        viewModelScope.launch {
            val res = repository.getTeams(query = "fc")
            Log.d("CustomTAG", "searchFavTeam: ${res.message()} ")
            _searchTeam.value = handleTeamSearch(res)
        }
    }

    private fun handleTeamSearch(res: Response<Teams>): Resource<Teams> {
        if (res.isSuccessful) {
            res.body()?.let { teams ->
                return Resource.Success(teams)
            }
        }

        Log.d("TAG", "handleTeamSearch: ${res.message()} ")
        return Resource.Error(message = res.message())
    }


}