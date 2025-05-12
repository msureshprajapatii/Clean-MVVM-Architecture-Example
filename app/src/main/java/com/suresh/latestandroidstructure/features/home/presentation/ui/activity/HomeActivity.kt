package com.suresh.latestandroidstructure.features.home.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.suresh.latestandroidstructure.R
import com.suresh.latestandroidstructure.databinding.ActivityHomeBinding
import com.suresh.latestandroidstructure.features.newscategories.presentation.ui.CategoriesFragment
import com.suresh.latestandroidstructure.features.home.presentation.ui.fragments.NewsFragment
import com.suresh.latestandroidstructure.features.profile.presentation.ui.ProfileFragment
import com.suresh.latestandroidstructure.features.home.presentation.ui.fragments.SavedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(NewsFragment())

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_news -> {
                    updateHeader("Today's Highlights")
                    loadFragment(NewsFragment())
                }
                R.id.nav_categories -> {
                    updateHeader("Categories")
                    loadFragment(CategoriesFragment())
                }
                R.id.nav_saved -> {
                    updateHeader("Saved")
                    loadFragment(SavedFragment())
                }
                R.id.nav_profile -> {
                    updateHeader("Profile")
                    loadFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun updateHeader(title:String) {
        binding.tvHeaderTitle.text = title
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}