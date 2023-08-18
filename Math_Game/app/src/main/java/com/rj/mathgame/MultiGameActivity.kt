//package com.rj.mathgame
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MultiGameActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_multi_game)
//    }
//}

package com.rj.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class MultiGameActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var score: TextView
    lateinit var life: TextView
    lateinit var time: TextView
    lateinit var ok: Button
    lateinit var next: Button

    var correctAnwer=0
    var userLife=3
    var userScore=0

    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long = 20000
    var timeLeftInMillis: Long= startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title="Addition"

        editText=findViewById(R.id.editTextAnswer)
        textView=findViewById(R.id.textViewQuestion)
        score=findViewById(R.id.textViewScore)
        life=findViewById(R.id.textViewLife)
        time=findViewById(R.id.textViewTime)
        ok=findViewById(R.id.buttonOk)
        next=findViewById(R.id.buttonNext)

        gameContinue()


        ok.setOnClickListener {
            var ans= editText.text.toString()
            if(ans==""){
                Toast.makeText(applicationContext,"Please enter an answer or click the next button", Toast.LENGTH_LONG).show()
            }
            else{

                pauseTimer()

                var userAns= ans.toInt()
                if(userAns==correctAnwer){
                    userScore=userScore+10

                    textView.text="Congratulations, your answer is correct"
                    score.text=userScore.toString()
                }
                else{
                    userLife--
                    textView.text="Sorry, your answer is incorrect"
                    life.text=userLife.toString()
                }
            }

        }

        next.setOnClickListener {

            pauseTimer()
            resetTimer()
            editText.setText("")

            if(userLife==0){

                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent= Intent(this@MultiGameActivity, ResultActivity::class.java)
                intent.putExtra("Score", userScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()

            }
        }

    }

    fun gameContinue(){


        val num1=Random.nextInt(0,100)
        val num2=Random.nextInt(0,20)

        textView.text= "$num1 * $num2"

        correctAnwer= num1*num2

        startTimer()
    }

    fun startTimer(){
        timer= object: CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis= millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                life.text= userLife.toString()
                textView.text="Sorry, time is up"
            }

        }.start()
    }

    fun updateText(){
        var remainingTime: Int=(timeLeftInMillis/1000).toInt()
        time.text=String.format(Locale.getDefault(),"%2d", remainingTime)
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeftInMillis=startTimerInMillis
        updateText()
    }
}