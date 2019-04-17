package io.github.zeleven.changelanguagesample

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var chineseButton: Button
    private lateinit var englishButton: Button

    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        }
        changeLanguage(this, sharedPreferences?.getString("language_code", "zh"))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chineseButton = findViewById(R.id.chinese_button)
        englishButton = findViewById(R.id.english_button)
        chineseButton.setOnClickListener {
            sharedPreferences?.edit()?.putString("language_code", "zh")?.apply()
            restart()
        }
        englishButton.setOnClickListener {
            sharedPreferences?.edit()?.putString("language_code", "en")?.apply()
            restart()
        }
    }

    private fun changeLanguage(context: Context?, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context?.resources?.configuration)
        config.setLocale(locale)
        context?.resources?.updateConfiguration(config, context.resources.displayMetrics)
    }

    private fun restart() {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
