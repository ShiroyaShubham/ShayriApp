package com.example.shayriapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayriapp.databinding.ActivityShayariBinding

class ShayariActivity : AppCompatActivity() {
    lateinit var binding:ActivityShayariBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        var category=intent.getStringExtra("categoryType")
        binding.txtCategory.text = category

        binding.btnBack.setOnClickListener {
            finish()
        }

        var database=SharyriDatabase(this)
        var shayariList=database.shayariReadData()
        var displayShayariAdapter=DisplayShayariAdapter(shayariList)
        var manager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvShayariList.layoutManager=manager
        binding.rcvShayariList.adapter=displayShayariAdapter
    }
}