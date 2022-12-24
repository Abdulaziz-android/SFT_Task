package com.yakubjonov.sft_task.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yakubjonov.sft_task.R
import com.yakubjonov.sft_task.databinding.ActivitySftBinding
import com.yakubjonov.sft_task.presentation.ui.dialog.StateDialog

class SftActivity : AppCompatActivity(), SftView {

    private lateinit var binding: ActivitySftBinding
    private lateinit var stateDialog : StateDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stateDialog = StateDialog(this)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_sft)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_basic, R.id.navigation_history, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun dialogShow(message: String) {
        stateDialog.show(message)
    }

    override fun dialogHide() {
        stateDialog.hide()
    }

    override fun dialogShowError(messageId:Int) {
        stateDialog.showError(getString(messageId))
    }
}