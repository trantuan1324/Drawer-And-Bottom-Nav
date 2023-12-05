package com.rabbyte.bottomnavgation.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rabbyte.bottomnavgation.R
import com.rabbyte.bottomnavgation.bottom_fragment.CartFragment
import com.rabbyte.bottomnavgation.bottom_fragment.CategoryFragment
import com.rabbyte.bottomnavgation.bottom_fragment.HistoryFragment
import com.rabbyte.bottomnavgation.bottom_fragment.NotificationFragment
import com.rabbyte.bottomnavgation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_category -> {
                    replaceFragment(CategoryFragment())
                    activity?.title = getString(R.string.category)
                }
                R.id.bottom_history -> {
                    replaceFragment(HistoryFragment())
                    activity?.title = getString(R.string.history)
                }
                R.id.bottom_notification -> {
                    replaceFragment(NotificationFragment())
                    activity?.title = getString(R.string.notification)
                }
                R.id.bottom_cart -> {
                    replaceFragment(CartFragment())
                    activity?.title = getString(R.string.cart)
                }
            }
            true
        }

        replaceFragment(CategoryFragment())
        activity?.title = getString(R.string.category)
        bottomNavigationView.selectedItemId = R.id.bottom_category
        binding.btnAdd.setOnClickListener {
            Toast.makeText(context, "This feature is developing", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottom_fragment, fragment)
            .commit()
    }

}