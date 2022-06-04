package com.example.myteams.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myteams.data.models.Teams
import com.example.myteams.repositories.SportsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavTeamsViewModel @Inject constructor(
    private val repository: SportsRepository
) : ViewModel() {


    companion object {
        const val DEFAULT_QUERY = "Arsenal"
    }


    var sampleOutput: Teams = Teams(emptyList())

    init {

        viewModelScope.launch {
            sampleOutput = repository.getTeams(query = "Arsenal")

            sampleOutput.teams.forEach { team ->
                Log.d("TAG", "ouput: $team")
            }
        }
    }

    fun searchFavTeam() {
        viewModelScope.launch {
            repository.getTeams(query = DEFAULT_QUERY)
        }
    }
}