package com.example.util

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.Looper

object SoundEffects {
    private var toneGenerator: ToneGenerator? = null

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playCorrectSound() {
        try {
            // Light, encouraging medium-high tone
            toneGenerator?.startTone(ToneGenerator.TONE_DTMF_A, 120)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playWrongSound() {
        try {
            // Low, standard error buzz tone
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 250)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playSuccessSound() {
        try {
            // Friendly triplet of upbeat notes for level victory
            toneGenerator?.startTone(ToneGenerator.TONE_DTMF_1, 100)
            Handler(Looper.getMainLooper()).postDelayed({
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_5, 100)
            }, 120)
            Handler(Looper.getMainLooper()).postDelayed({
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_9, 150)
            }, 240)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
