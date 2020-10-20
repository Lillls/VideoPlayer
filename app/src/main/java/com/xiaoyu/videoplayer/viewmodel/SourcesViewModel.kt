package com.xiaoyu.videoplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xiaoyu.videoplayer.AssetsRepository
import kotlinx.coroutines.launch


class SourcesViewModel(application: Application) : AndroidViewModel(application) {
    private val data: MutableLiveData<ArrayList<SourceModelItem>> = MutableLiveData()

    fun getData(): MutableLiveData<ArrayList<SourceModelItem>> {
        viewModelScope.launch {
            data.postValue(loadData())
        }
        return data
    }

    private fun loadData(): ArrayList<SourceModelItem> {
        return AssetsRepository().loadData(getApplication())
    }

}