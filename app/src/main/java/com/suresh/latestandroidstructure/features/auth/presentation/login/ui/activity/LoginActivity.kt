package com.suresh.latestandroidstructure.features.auth.presentation.login.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suresh.latestandroidstructure.R
import com.suresh.latestandroidstructure.core.state.UiState
import com.suresh.latestandroidstructure.core.ui.components.showCustomDialog
import com.suresh.latestandroidstructure.databinding.ActivityLoginBinding
import com.suresh.latestandroidstructure.features.auth.presentation.login.viewmodel.LoginViewModel
import com.suresh.latestandroidstructure.features.auth.presentation.signup.ui.activity.SignUpActivity
import com.suresh.latestandroidstructure.features.home.presentation.ui.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()
        observeFormState()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupBinding() {
        binding.apply {
            lifecycleOwner = this@LoginActivity
            viewModel = loginViewModel

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }

            edtPassword.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableEnd = 2
                    if (event.rawX >= (edtPassword.right - edtPassword.compoundDrawables[drawableEnd].bounds.width())) {
                        loginViewModel.togglePasswordVisibility()
                        return@setOnTouchListener true
                    }
                }
                false
            }
        }
    }

    private fun observeFormState() {
        lifecycleScope.launch {
            loginViewModel.uiState.collect { event ->
                event?.getContentIfNotHandled()?.let { state ->
                    when (state) {
                        is UiState.Error -> {
                            showCustomDialog(
                                context = this@LoginActivity,
                                title = "Error",
                                message = state.message
                            )
                        }

                        is UiState.Success -> {
                            showCustomDialog(
                                context = this@LoginActivity,
                                title = "Success",
                                message = state.data,
                                onPositiveClick = {
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            )
                        }

                        else -> Unit
                    }
                }
            }
        }

        lifecycleScope.launch {
            loginViewModel.isPasswordVisible.collect { isVisible ->
                val inputType = if (isVisible) {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }

                binding.edtPassword.apply {
                    this.inputType = inputType
                    this.setSelection(this.text?.length ?: 0)
                    val icon = if (isVisible) R.drawable.ic_eye_show else R.drawable.ic_eye_hide
                    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
                }
            }
        }
    }
}