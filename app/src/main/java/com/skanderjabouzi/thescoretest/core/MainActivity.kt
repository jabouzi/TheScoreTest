package com.skanderjabouzi.thescoretest.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.skanderjabouzi.thescoretest.R


class MainActivity : AppCompatActivity(){

  private lateinit var navigationController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    navigationController = findNavController(R.id.navigationHostFragment)
  }

  override fun onBackPressed() {}

  companion object {
    fun getIntent(context: Context): Intent {
      return Intent(context, MainActivity::class.java)
    }
  }

}
