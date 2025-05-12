package com.suresh.latestandroidstructure.features.profile.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suresh.latestandroidstructure.core.state.UiState
import com.suresh.latestandroidstructure.core.ui.components.showCustomDialog
import com.suresh.latestandroidstructure.databinding.FragmentProfileBinding
import com.suresh.latestandroidstructure.features.auth.presentation.login.ui.activity.LoginActivity
import com.suresh.latestandroidstructure.features.profile.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        observeFormState()
    }

    private fun setupBinding() {
        binding.apply {
            lifecycleOwner = this@ProfileFragment
            viewModel = profileViewModel

            btnLogout.setOnClickListener {
                showCustomDialog(
                    context = requireContext(),
                    title = "Logout",
                    message = "Are you sure you want to logout?",
                    positiveText = "Logout",
                    negativeText = "Cancel",
                    onPositiveClick = {
                        profileViewModel.logout()
                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                        requireActivity().finish()
                    },
                    onNegativeClick = {

                    }
                )
            }
        }
    }

    private fun observeFormState() {
        lifecycleScope.launch {
            profileViewModel.uiState.collect { event ->
                event?.getContentIfNotHandled()?.let { state ->
                    when (state) {
                        is UiState.Error -> {
                            showCustomDialog(
                                context = requireContext(),
                                title = "Error",
                                message = state.message
                            )
                        }

                        is UiState.Success -> {
                            showCustomDialog(
                                context = requireContext(),
                                title = "Success",
                                message = state.data
                            )
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}