package com.ricardogwill.guessthenumber
// Note: I created this on my own, based on my own research, without using any specific tutorial.

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var numberToGuess: Int = 0
    var attemptsInt: Int = 0
    var fewestAttemptsInt: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitB.visibility = View.INVISIBLE

        startB.setOnClickListener(){
            startNewGame()
        }

        submitB.setOnClickListener() {
            submit()
        }

    }

    fun startNewGame(): Int {
        // Resets attemptsInt count, the attemptsTV TextView, and deletes the congratulatory test for the previous game.
        attemptsInt = 0
        attemptsTV.setText("Attempts: 0")
        answerTV.setText("      ")

        val randomInteger = (1..100).shuffled().first()

//        answerTV.setText(randomInteger.toString())

        startB.visibility = View.INVISIBLE
        submitB.visibility = View.VISIBLE

        numberToGuess = randomInteger

        return numberToGuess

    }

    fun submit() {
        // Tells the player if they are too high, too low, or if they are correct with their guess.
        if (guessET.text.toString() != "") {
            if (guessET.text.toString().toInt() > 0 && guessET.text.toString().toInt() < 101) {
//                answerTV.setText(guessET.text.toString())
                if (guessET.text.toString().toInt() == numberToGuess) {
                    answerTV.setText("You are correct!\nThe answer is...\n$numberToGuess!")
                    submitB.visibility = View.INVISIBLE
                    startB.visibility = View.VISIBLE
                    updateAttempts()
                    updateFewestAttempts()
                } else if (guessET.text.toString().toInt() > numberToGuess) {
                    answerTV.setText("Your number is too high.\nTry a LOWER number.")
                    updateAttempts()
                } else {
                    answerTV.setText("Your number is too low.\nTry a HIGHER number.")
                    updateAttempts()
                }
            } else {
                answerTV.setText("Please guess a number from 1-100.")
            }
        } else {
            answerTV.setText("Please guess a number from 1-100.\n(Field is blank.)")
        }
        // Clears the EditText.
        guessET.setText("")
    }

    fun updateAttempts() {
        attemptsInt++
        attemptsTV.setText("Attempts: $attemptsInt")
    }

    fun updateFewestAttempts() {
        if (attemptsInt < fewestAttemptsInt) {
            fewestAttemptsInt = attemptsInt
            fewestAttemptsTV.setText("Fewest Attempts: $fewestAttemptsInt")
        }
    }

}
