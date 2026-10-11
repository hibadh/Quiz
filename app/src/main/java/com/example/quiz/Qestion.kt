package com.example.quiz

data class Question(
    val texte: String,
    val image: Int,
    val choix: Array<String>,
    val bonneReponse: Int
)