package com.edanuryildirim.itunes.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edanuryildirim.itunes.R
import com.edanuryildirim.itunes.view.ui.ChoosenActivity
import java.lang.Exception

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(
                        baseContext,
                        ChoosenActivity::class.java
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
        background.start()
    }
}