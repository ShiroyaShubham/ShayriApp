package com.example.shayriapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayriapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var category:String ?=null
    var id : Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        var sharyriDatabase=SharyriDatabase(this)
        var list =sharyriDatabase.readData()

        var categoryAdapter=CategoryAdapter(this,list)

        var manager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvList.layoutManager=manager
        binding.rcvList.adapter=categoryAdapter

        binding.imgMenu.setOnClickListener {
            binding.drawer.openDrawer(Gravity.LEFT)
        }

    }

}

