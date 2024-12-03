package com.example.passtask1thecontest

import android.media.MediaActionSound
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.media.MediaPlayer


class MainActivity : AppCompatActivity() {
    private var score = 0
    private lateinit var scoretext: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var winSound: MediaPlayer
    private lateinit var clickSound: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        winSound = MediaPlayer.create(this, R.raw.bugle_tune)
        clickSound = MediaPlayer.create(this, R.raw.beep_short)

        scoretext = findViewById(R.id.score_text)
        score = savedInstanceState?.getInt("SCORE_KEY") ?: 0
        updateScore()

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener{ incrementScore()}
        button2.setOnClickListener{ decrementScore()}
        button3.setOnClickListener{ reset()}

        }
    private fun incrementScore(){
        score++
        updateScore()
        Log.i("a game status", "score increased by 1")
        clickSound.start()
        if(score==15){
            Log.i("game status", "layer1 with 15 points")
            winSound.start()

        }
    }
    private fun decrementScore(){
        score--
        updateScore()
        Log.i("a game status", "score decreased by 1")
        clickSound.start()

    }
    private fun reset(){
        score=0
        updateScore()
        Log.i("a game status", "score has been reset")
    }
    private fun updateScore(){
        scoretext.text = getString(R.string.score_text, score)


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SCORE_KEY", score)
        Log.d("ActivityState", "Score saved: $score")
    }

    override fun onDestroy() {
        super.onDestroy()
        winSound.release()
        clickSound.release()
    }
}