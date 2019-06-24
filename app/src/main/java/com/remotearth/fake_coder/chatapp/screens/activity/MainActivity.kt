package com.remotearth.fake_coder.chatapp.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.remotearth.fake_coder.chatapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setUpActionBarAndTopLevelDestination()
        configureHidingStatusOfActionBar()
    }

    private fun setUpActionBarAndTopLevelDestination() {
        setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration.Builder(
                setOf(
                    R.id.loginFragment,
                    R.id.chatListFragment
                )
            ).build()
        )
    }

    private fun configureHidingStatusOfActionBar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.signUpFragment || destination.id == R.id.chatFragment || destination.id == R.id.chatListFragment) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()
}
