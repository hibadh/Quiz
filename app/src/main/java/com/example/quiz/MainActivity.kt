package com.example.quiz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var numeroQuestion = 0
    private var score = 0

    private val questions = arrayOf(
        Question(
            texte = "Quelle forme voyez-vous ?",
            image = R.drawable.triangle,
            choix = arrayOf("Triangle", "Cercle", "Carré"),
            bonneReponse = 0
        ),
        Question(
            texte = "Quelle forme voyez-vous ?",
            image = R.drawable.carre,
            choix = arrayOf("Rectangle", "Carré", "Cercle"),
            bonneReponse = 1
        ),
        Question(
            texte = "Quelle forme voyez-vous ?",
            image = R.drawable.cercle,
            choix = arrayOf("Étoile", "Triangle", "Cercle"),
            bonneReponse = 2
        ),
        Question(
            texte = "Quelle forme voyez-vous ?",
            image = R.drawable.etoile,
            choix = arrayOf("Étoile", "Carré", "Rectangle"),
            bonneReponse = 0
        ),
        Question(
            texte = "Quelle forme voyez-vous ?",
            image = R.drawable.rectangle,
            choix = arrayOf("Cercle", "Rectangle", "Triangle"),
            bonneReponse = 1
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        afficherQuestion()

        binding.btnChoix1.setOnClickListener {
            verifierReponse(0)
        }

        binding.btnChoix2.setOnClickListener {
            verifierReponse(1)
        }

        binding.btnChoix3.setOnClickListener {
            verifierReponse(2)
        }

        binding.btnSuivant.setOnClickListener {
            numeroQuestion++
            if (numeroQuestion < questions.size) {
                afficherQuestion()
            } else {
                afficherResultat()
            }
        }

        binding.btnRejouer.setOnClickListener {
            rejouer()
        }
    }

    private fun afficherQuestion() {
        setQuizElementsVisible(true)
        binding.layoutResultat.visibility = View.GONE

        val question = questions[numeroQuestion]

        binding.txtQuestionNumber.text =
            "Question ${numeroQuestion + 1} sur ${questions.size}"

        binding.txtScore.text =
            "Score : $score/${questions.size}"

        binding.progressQuiz.progress =
            numeroQuestion

        binding.imgQuestion.setImageResource(question.image)

        binding.txtQuestion.text =
            question.texte

        binding.btnChoix1.text =
            question.choix[0]

        binding.btnChoix2.text =
            question.choix[1]

        binding.btnChoix3.text =
            question.choix[2]

        binding.txtFeedback.text = ""

        binding.btnChoix1.isEnabled = true
        binding.btnChoix2.isEnabled = true
        binding.btnChoix3.isEnabled = true

        binding.btnSuivant.visibility =
            View.GONE
    }

    private fun verifierReponse(reponse: Int) {

        val question = questions[numeroQuestion]

        if (reponse == question.bonneReponse) {
            score++

            binding.txtFeedback.text =
                "Bonne réponse ! 🎉"
        } else {
            binding.txtFeedback.text =
                "Mauvaise réponse."
        }

        binding.txtScore.text =
            "Score : $score/${questions.size}"

        binding.progressQuiz.progress =
            numeroQuestion + 1

        binding.btnChoix1.isEnabled = false
        binding.btnChoix2.isEnabled = false
        binding.btnChoix3.isEnabled = false

        binding.btnSuivant.visibility =
            android.view.View.VISIBLE
    }

    private fun afficherResultat() {
        setQuizElementsVisible(false)
        binding.btnSuivant.visibility = View.GONE
        binding.layoutResultat.visibility = View.VISIBLE
        binding.txtResultat.text = "Quiz terminé !\nVotre score : $score / ${questions.size}"
    }

    private fun rejouer() {
        numeroQuestion = 0
        score = 0
        afficherQuestion()
    }

    private fun setQuizElementsVisible(visible: Boolean) {
        val visibility = if (visible) View.VISIBLE else View.GONE
        binding.txtQuestionNumber.visibility = visibility
        binding.txtScore.visibility = visibility
        binding.progressQuiz.visibility = visibility
        binding.imgQuestion.visibility = visibility
        binding.txtQuestion.visibility = visibility
        binding.btnChoix1.visibility = visibility
        binding.btnChoix2.visibility = visibility
        binding.btnChoix3.visibility = visibility
        binding.txtFeedback.visibility = visibility
    }
}