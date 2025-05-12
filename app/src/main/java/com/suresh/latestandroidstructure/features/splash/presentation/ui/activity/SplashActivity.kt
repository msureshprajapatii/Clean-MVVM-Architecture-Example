package com.suresh.latestandroidstructure.features.splash.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suresh.latestandroidstructure.databinding.ActivitySplashBinding
import com.suresh.latestandroidstructure.features.auth.presentation.login.ui.activity.LoginActivity
import com.suresh.latestandroidstructure.features.home.presentation.ui.activity.HomeActivity
import com.suresh.latestandroidstructure.features.splash.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.navigateTo.collect { destination ->
                when (destination) {
                    SplashViewModel.Destination.Home -> {
                        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                        finish()
                    }

                    SplashViewModel.Destination.Login -> {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }

                    null -> {}
                }
            }
        }
    }
}