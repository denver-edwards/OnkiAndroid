package com.tappuri.onki

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView


class MainActivity : ComponentActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var searchButton: ImageButton
    private lateinit var cvWakuwaku: CardView
    private lateinit var cvKukkiri: CardView
    private lateinit var cvGamigami: CardView
    private lateinit var tvWakuwaku: TextView
    private lateinit var tvWakuwakuJp: TextView
    private lateinit var tvWakuwakuSummary: TextView
    private lateinit var tvKukkiri: TextView
    private lateinit var tvKukkiriJp: TextView
    private lateinit var tvKukkiriSummary: TextView
    private lateinit var tvGamigami: TextView
    private lateinit var tvGamigamiJp: TextView
    private lateinit var tvGamigamiSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        cvWakuwaku = findViewById(R.id.cv_wakuwaku)
        cvKukkiri = findViewById(R.id.cv_kukkiri)
        cvGamigami = findViewById(R.id.cv_gamigami)
        tvWakuwaku = findViewById(R.id.tv_wakuwaku)
        tvWakuwakuJp = findViewById(R.id.tv_wakuwaku_jp)
        tvWakuwakuSummary = findViewById(R.id.tv_wakuwaku_summary)
        tvKukkiri = findViewById(R.id.tv_kukkiri)
        tvKukkiriJp = findViewById(R.id.tv_kukkiri_jp)
        tvKukkiriSummary = findViewById(R.id.tv_kukkiri_summary)
        tvGamigami = findViewById(R.id.tv_gamigami)
        tvGamigamiJp = findViewById(R.id.tv_gamigami_jp)
        tvGamigamiSummary = findViewById(R.id.tv_gamigami_summary)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        searchButton.setOnClickListener { performSearch() }
        databaseHelper.copyDatabaseFromAssets()

        cvWakuwaku.setOnClickListener {
            startPhraseActivity("Waku Waku")
        }
        cvKukkiri.setOnClickListener {
            startPhraseActivity("Kukkiri")
        }
        cvGamigami.setOnClickListener {
            startPhraseActivity("Gami Gami")
        }

        setUpCardViews()
    }

    private fun performSearch() {
        val minLength = 3 // Minimum character limit

        if (searchEditText.length() < minLength) {
            searchEditText.error = "Minimum character limit is $minLength";
        } else {

            val searchText = searchEditText.text.toString()
            val searchResults = databaseHelper.searchRecords(searchText)

            val intent = Intent(this, SearchResultActivity::class.java)
            intent.putStringArrayListExtra(
                SearchResultActivity.EXTRA_SEARCH_RESULTS,
                ArrayList(searchResults)
            )
            startActivity(intent)
        }
    }

    private fun startPhraseActivity(phrase: String) {
        val intent = Intent(this, PhraseActivity::class.java)
        intent.putExtra("phraseName", phrase)
        this.startActivity(intent)
    }

    private fun setUpCardViews() {
        val waku: Phrase = databaseHelper.findPhrase("Waku Waku")
        val kukkiri: Phrase = databaseHelper.findPhrase("Kukkiri")
        val gami: Phrase = databaseHelper.findPhrase("Gami Gami")

        tvWakuwaku.text = waku.english
        tvWakuwakuJp.text = waku.japanese
        tvWakuwakuSummary.text = waku.summary
        tvKukkiri.text = kukkiri.english
        tvKukkiriJp.text = kukkiri.japanese
        tvKukkiriSummary.text = kukkiri.summary
        tvGamigami.text = gami.english
        tvGamigamiJp.text = gami.japanese
        tvGamigamiSummary.text = gami.summary
    }
}