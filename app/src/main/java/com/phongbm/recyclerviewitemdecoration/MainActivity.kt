package com.phongbm.recyclerviewitemdecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phongbm.itemdecoration.LinearSpacingItemDecoration
import com.phongbm.recyclerviewitemdecoration.databinding.ActivityMainBinding

/**
 * Create by PhongBM on 03/13/2022
 */

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lstList.setHasFixedSize(true)

        val d16 = (16 * resources.displayMetrics.density).toInt()
        binding.lstList.addItemDecoration(LinearSpacingItemDecoration.verticalBoth(d16, true))

        val adapter = ItemAdapter()
        binding.lstList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}