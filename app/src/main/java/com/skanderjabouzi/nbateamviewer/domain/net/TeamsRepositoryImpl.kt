package com.skanderjabouzi.nbateamviewer.domain.net

import android.content.Context
import com.skanderjabouzi.nbateamviewer.data.db.PlayersDao
import com.skanderjabouzi.nbateamviewer.data.db.TeamDao
import com.skanderjabouzi.nbateamviewer.data.db.TeamDatabase
import com.skanderjabouzi.nbateamviewer.data.model.db.PlayerEntity
import com.skanderjabouzi.nbateamviewer.data.model.db.TeamEntity
import com.skanderjabouzi.nbateamviewer.data.model.net.Players
import com.skanderjabouzi.nbateamviewer.data.model.net.Teams
import com.skanderjabouzi.nbateamviewer.data.net.Network
import com.skanderjabouzi.nbateamviewer.data.net.RetrofitClient
import retrofit2.Response

class TeamsRepositoryImpl(val context: Context): TeamsRepository {

  var db = TeamDatabase.getInstance(context)
  var retrofitClient: RetrofitClient
  private val teamDao: TeamDao = db.teamDao()
  private val playersDao: PlayersDao = db.playersDao()

  init {
    retrofitClient = RetrofitClient(Network.getRetrofit(context))
  }


  override suspend fun getSavedTeams(): List<TeamEntity> {
    return teamDao.getTeams()
  }

  override suspend fun getSavedPlayers(teamId: Int): List<PlayerEntity> {
    return playersDao.getPlayers(teamId)
  }

  override suspend fun saveTeams(teams: List<TeamEntity>) {
    for (team in teams) {
      teamDao.insert(team)
    }
  }

  override suspend fun savePlayers(players: List<PlayerEntity>) {
    for (player in players) {
      playersDao.insert(player)
    }
  }

  override suspend fun getTeams(): Response<Teams> {
    return retrofitClient.getTeams()
  }

  override suspend fun getPlayers(teamId: Int): Response<Players> {
    return retrofitClient.getPlayers(teamId)
  }
}