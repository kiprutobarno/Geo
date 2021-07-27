package com.ywalakamar.geo

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG="GeoViewModel"

class GeoViewModel:ViewModel() {

    var currentIndex=0

    private val questionBank=listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_middle_east, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    val currentQuestionAnswer:Boolean get()=questionBank[currentIndex].answer
    val currentQuestion:Int get()=questionBank[currentIndex].id

    /*boolean array to keep track of answered questions*/
    val questionsAnswered: BooleanArray get() = BooleanArray(questionBank.size)

    val numberOfQuestions:Int get()=questionBank.size

    fun moveToNext(){
        currentIndex=(currentIndex+1)%questionBank.size
    }

    fun moveToPrevious(){
        currentIndex=(currentIndex+questionBank.size-1)%questionBank.size
    }

}
