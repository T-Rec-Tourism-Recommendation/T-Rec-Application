package com.example.t_rec.activity

class QuestionActivity(private var question: String, private var answer: String) {
    var expandable = false

    fun isExpandable(): Boolean {
        return expandable
    }

    fun setCustomExpandable(expandable: Boolean) {
        this.expandable = expandable
    }


    fun getQuestion(): String {
        return question
    }

    fun setQuestion(question: String) {
        this.question = question
    }

    fun getAnswer(): String {
        return answer
    }

    fun setAnswer(answer: String) {
        this.answer = answer
    }

    override fun toString(): String {
        return "QuestionActivity(" +
                "question='$question', " +
                "answer='$answer')"
    }
}