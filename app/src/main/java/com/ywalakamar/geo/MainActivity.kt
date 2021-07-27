package com.ywalakamar.geo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG="MainActivity"
private const val KEY_INDEX="index"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private var correctAnswers:Int=0

    /* lazy allows you to make the geoViewModel property a val instead of a var */
    private val geoViewModel:GeoViewModel by lazy {
        ViewModelProvider(this).get(GeoViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putInt(KEY_INDEX, geoViewModel.currentIndex)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Reference widgets*/
        trueButton=findViewById(R.id.true_button)
        falseButton=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        prevButton=findViewById(R.id.previous_button)
        questionTextView=findViewById(R.id.question_text_view)

        /*Setting listeners*/
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            geoViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            geoViewModel.moveToPrevious()
            updateQuestion()
        }
        val questionId=geoViewModel.currentQuestion
        questionTextView.setText(questionId)
    }

    private fun updateQuestion(){
        /*toggle button state based on question state(answered or not)*/
        trueButton.isEnabled=!geoViewModel.questionsAnswered[geoViewModel.currentIndex]
        falseButton.isEnabled=!geoViewModel.questionsAnswered[geoViewModel.currentIndex]

        val questionId=geoViewModel.currentQuestion
        questionTextView.setText(questionId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer=geoViewModel.currentQuestionAnswer

        /*specify that the question in this position has been answered*/
        geoViewModel.questionsAnswered[geoViewModel.currentIndex]=true

        /*disable buttons*/
        trueButton.isEnabled=false
        falseButton.isEnabled=false

        val feedback=if(userAnswer==correctAnswer){
            correctAnswers++
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(
            this,
            feedback,
            Toast.LENGTH_SHORT
        ).show()

        /*show score*/
        calculateScore()
    }

    private fun calculateScore(){
        val score:Int=correctAnswers*100/geoViewModel.numberOfQuestions
        val message=getString(R.string.score, score)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
