package com.rabbyte.bottomnavgation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.rabbyte.bottomnavgation.databinding.ActivityMainBinding
import com.rabbyte.bottomnavgation.nav_fragment.HomeFragment
import com.rabbyte.bottomnavgation.nav_fragment.ProfileFragment
import com.rabbyte.bottomnavgation.nav_fragment.SettingFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val onBackPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }


    }

    private fun onBackPressedMethod() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMainActivity.toolbar)

        val header = binding.navigationView.getHeaderView(0)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallBack)

        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout, binding.appBarMainActivity.toolbar,
            R.string.open_drawer, R.string.close_drawer
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(HomeFragment())
        binding.navigationView.setCheckedItem(R.id.nav_home)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val msg = Toast.makeText(this, "Feature is developing", Toast.LENGTH_SHORT)
        when(item.itemId) {
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
            }
            R.id.nav_profile -> {
                replaceFragment(ProfileFragment())
                title = resources.getString(R.string.profile)
            }
            R.id.nav_setting -> {
                replaceFragment(SettingFragment())
                title = resources.getString(R.string.setting)
            }
            R.id.nav_share -> {
                msg.show()
            }
            R.id.nav_logout -> {
                msg.show()
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}