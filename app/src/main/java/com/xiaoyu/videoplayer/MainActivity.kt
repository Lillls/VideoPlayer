package com.xiaoyu.videoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xiaoyu.videoplayer.viewmodel.SourcesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sourceAdapter = SourceAdapter()
        recyclerView.adapter = sourceAdapter
        val vm = ViewModelProvider(this).get(SourcesViewModel::class.java)
        vm.getData().observe(this, Observer {
            sourceAdapter.setData(it)
        })

        //DisplayHelper.setFullScreen(this)

    }
}
