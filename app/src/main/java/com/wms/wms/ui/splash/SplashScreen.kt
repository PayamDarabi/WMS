package com.wms.wms.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.wms.wms.R
import com.wms.wms.data.UserManager
import com.wms.wms.ui.home.HomeActivity
import com.wms.wms.ui.login.LoginActivity


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        UserManager.initilize( applicationContext)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        UserManager.addLoginListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        UserManager.addLogoutListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val user = UserManager.get()
            if(user === null || user.accessToken == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                //Home
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }

            finish()

        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}