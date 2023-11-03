package com.example.moblist1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private val pytania = listOf(
        Question("2+2", "4", "5", "3", "Tak jak partia mówi", 0),
        Question("Polska jest członkiem:","NATO","Uni Europejskiej","NATO i EU","Paktu Warszawskiego",2),
        Question("Czy ten quiz jest trudny","Tak","Bardzo","Jak najbardziej","Nie",3),
        Question("1/3","0.3","0.(3)","0.3333333333333","0.300000...9",1),
        Question("KKK to","Hinduska bojówka nacjonalistyczna","Kaszubskie Kotlety Kute","Producent czapek dla smerfów","Rasistowska organicacja promująca białą wyższość",3),
        Question("Kto jest naszym największym sąsiadem","Kalingrad","Rosja","Moskwa","Niemcy",1),
        Question("Poprawna odpowiedź to 3","1","2","3","4",2),
        Question("Google najbardziej jest znany jako","Wyszukiwarka","Media społecznościowe","Kantor","Organizacja szukająca leku na raka",0),
        Question("By zaliczeyć listę trzeba:","Tylko ją wysłać do prowadzącego","Pokazać na zajęciach że aplikacja działa","Zrobić o niej prezentację","Mieć udowodnioną obecność na wykładzie",1),
        Question("To ostatnie pytanie, tak?","Nie","Jeszcze dziesięć","Nie ma możliwości stwierdzenia","Tak",3)
    )

    private var nrpytania = 0 // numer pytania
    private var punkt = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val nextButton = findViewById<Button>(R.id.button)
        updateQuestionNumber()

        nextButton.setOnClickListener {
            val currentQuestion = pytania[nrpytania]
            val selectedAnswerIndex = radioGroup.indexOfChild(findViewById(radioGroup.checkedRadioButtonId))

            if (selectedAnswerIndex == currentQuestion.cor) {
                // punkty
                punkt += 10
            }
            if (nrpytania < pytania.size - 1) {
                nrpytania++ //następne
                updateProgressBar()
                updateQuestionNumber()
                displayCurrentQuestion() // nowe pytanie
            } else {
                showResult()
            }
        }

        // pytanie
        displayCurrentQuestion()
    }
    data class Question(val que:String="",val ansp:String="",val ansd:String="",val anst:String="",val ansc:String="",val cor:Int=0)
    fun displayCurrentQuestion() {
        val questionTextView = findViewById<TextView>(R.id.textView2)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val currentQuestion = pytania[nrpytania]

        // wypisuje pytanie
        questionTextView.text = currentQuestion.que

        // Odpowiedzi
        for (i in 0 until radioGroup.childCount) {
            val radioButton = radioGroup.getChildAt(i) as RadioButton
            radioButton.text = when (i) {
                0 -> currentQuestion.ansp
                1 -> currentQuestion.ansd
                2 -> currentQuestion.anst
                3 -> currentQuestion.ansc
                else -> ""
            }
        }
    }

    fun updateProgressBar() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progress = ((nrpytania) * 100) / pytania.size
        progressBar.progress = progress
    }

    fun updateQuestionNumber() {
        val questionNumberTextView = findViewById<TextView>(R.id.textView)
        val questionNumberText = "Pytanie ${nrpytania + 1} / ${pytania.size}"
        questionNumberTextView.text = questionNumberText
    }
    fun showResult() {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val TextView = findViewById<TextView>(R.id.textView)
        val TextView2 = findViewById<TextView>(R.id.textView2)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val button = findViewById<Button>(R.id.button)
        TextView.text = "Gratulacje"
        resultTextView.text = "Punkty: $punkt"
        resultTextView.isVisible = true
        radioGroup.isVisible = false
        progressBar.isVisible = false
        TextView2.isVisible = false
        button.isVisible = false
    }
}

