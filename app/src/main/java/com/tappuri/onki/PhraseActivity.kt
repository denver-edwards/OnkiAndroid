package com.tappuri.onki

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PhraseActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrase)

        val phrase = intent.getStringExtra("phraseName")

        databaseHelper = DatabaseHelper(this)
        val selectedPhrase = databaseHelper.findPhrase(phrase)

        val tvEnglish = findViewById<TextView>(R.id.tv_english)
        val tvJapanese = findViewById<TextView>(R.id.tv_japanese)
        val tvSummary = findViewById<TextView>(R.id.tv_summary)

        if (phrase != null) {
            tvEnglish.text = selectedPhrase.english
            tvJapanese.text = selectedPhrase.japanese
            tvSummary.text = selectedPhrase.summary
        }
    }
}