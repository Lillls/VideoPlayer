package com.xiaoyu.videoplayer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xiaoyu.videoplayer.viewmodel.SourceModelItem
import java.lang.reflect.Type

class AssetsRepository {

    fun loadData(context: Context): ArrayList<SourceModelItem> {
        val jsonStr = readFromAssets(context)
        val type: Type = object : TypeToken<ArrayList<SourceModelItem>>() {}.type
        return Gson().fromJson<ArrayList<SourceModelItem>>(jsonStr, type)
    }

    private fun readFromAssets(context: Context): String {
        val assets = context.assets.open("media.exolist.json")
        val length: Int = assets.available()
        val buffer = ByteArray(length)
        assets.read(buffer)
        assets.close()
        return String(buffer)
    }
}