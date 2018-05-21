package com.kubiakpatryk.raicoonedatabasesamples

import android.app.Application
import com.kubiakpatryk.raicoonedatabasesamples.objectBox.MyObjectBox
import io.objectbox.BoxStore

class App : Application() {

    companion object {
        var boxStore : BoxStore? = null
    }

    override fun onCreate() {
        super.onCreate()
        boxStore = MyObjectBox.builder().androidContext(applicationContext).build()
    }
}
