package com.skanderjabouzi.thescoretest.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.skanderjabouzi.thescoretest.R
import com.skanderjabouzi.thescoretest.domain.usecase.ConnectionType
import com.skanderjabouzi.thescoretest.domain.usecase.ConnectionType.*
import com.skanderjabouzi.thescoretest.presentation.action
import com.skanderjabouzi.thescoretest.presentation.snack
import com.skanderjabouzi.thescoretest.util.ConnectivityLiveData
import com.skanderjabouzi.thescoretest.util.ConnectivityLiveData.Companion.STATE
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){

  private lateinit var navigationController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigationController = findNavController(R.id.navigationHostFragment)
    val connectivityLiveData = ConnectivityLiveData(TheScoreApp.INSTANCE)
    connectivityLiveData.observe(this, Observer<Boolean>(){
      when (it) {
        true -> if (STATE == NOT_CONNECTED) {
          showMessage(getString(R.string.connection_on))
          STATE = CONNECTED
        }
        false -> {
          showMessage(getString(R.string.no_connection))
          STATE = NOT_CONNECTED
        }
      }
    })
  }

  private fun showMessage(message: String) {
    mainLayout.snack(message, Snackbar.LENGTH_INDEFINITE) {
      action(getString(R.string.ok)) {}
    }
  }

  override fun onBackPressed() {}

  companion object {
    fun getIntent(context: Context): Intent {
      return Intent(context, MainActivity::class.java)
    }
  }

}
