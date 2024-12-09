package muhammadfauzi.polbeng.ac.id.githubprofile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import muhammadfauzi.polbeng.ac.id.githubprofile.activities.MainActivity
import muhammadfauzi.polbeng.ac.id.githubprofile.databinding.ActivitySplashScreenBinding
import muhammadfauzi.polbeng.ac.id.githubprofile.helpers.Config

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, Config.SPLASH_SCREEN_DELAY)
    }
}