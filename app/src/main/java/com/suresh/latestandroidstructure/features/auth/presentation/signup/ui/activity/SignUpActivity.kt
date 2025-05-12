package com.suresh.latestandroidstructure.features.auth.presentation.signup.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suresh.latestandroidstructure.core.data.local.room.RoomDB
import com.suresh.latestandroidstructure.core.state.UiState
import com.suresh.latestandroidstructure.core.ui.components.showCustomDialog
import com.suresh.latestandroidstructure.databinding.ActivitySignUpBinding
import com.suresh.latestandroidstructure.features.auth.presentation.signup.viewmodel.SignUpViewModel
import com.suresh.latestandroidstructure.features.home.presentation.ui.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    @Inject
    lateinit var database: RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        observeFormState()
    }

    private fun setupBinding() {
        binding.apply {
            lifecycleOwner = this@SignUpActivity
            viewModel = signUpViewModel

            btnLogin.setOnClickListener {
                finish()
            }
        }
    }

    private fun observeFormState() {
        lifecycleScope.launch {
            signUpViewModel.uiState.collect { event ->
                event?.getContentIfNotHandled()?.let { state ->
                    when (state) {
                        is UiState.Error -> {
                            showCustomDialog(
                                context = this@SignUpActivity,
                                title = "Error",
                                message = state.message
                            )
                        }

                        is UiState.Success -> {
                            showCustomDialog(
                                context = this@SignUpActivity,
                                title = "Success",
                                message = state.data
                            ) {
                                val intent =
                                    Intent(this@SignUpActivity, HomeActivity::class.java).apply {
                                        flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    }
                                startActivity(intent)
                                finish()
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}