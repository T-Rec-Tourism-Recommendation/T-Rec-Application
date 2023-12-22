package com.example.t_rec.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.t_rec.R
import com.example.t_rec.adapter.QuestionAdapter


class FAQActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var questionList: List<QuestionActivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expandablelayout)

        recyclerView = findViewById(R.id.recyclerViewFAQ)

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val questionAdapter = QuestionAdapter(questionList)
        recyclerView.adapter = questionAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun initData() {
        questionList = ArrayList()
        questionList += QuestionActivity("How to use the search?", "First click on the search bar, then enter the name of the city or type of destination you want in the search bar. Next, the tourist destination you are looking for will appear.")
        questionList += QuestionActivity("How to use nlp?", "On the homepage, click the filter icon next to the search bar. Enter the desired destination description, then click 'ok' to confirm, but if you want to cancel, click 'cancel'. After that, a list of desired destinations has appeared.")
        questionList += QuestionActivity("How to use filter?", "On the home page, click the filter icon present inside the search bar. After entering the filter page, click the destination category icon you want, then click the city icon you want to go to. Next, click the 'apply' icon. Done, a list of desired destinations has appeared. If you want to cancel the search filter then you can click 'Reset All', and it will take you directly to the Home page.")
        questionList += QuestionActivity("How to use favorite?", "To add a destination to your favorites, go to the home page, click the destination box, and then on the details page, tap the love icon to save it. Your favorite destinations are accessible on the favorites page. To view them, return to the home page and click the love icon in the bottom navigation. Unfavorite a destination by tapping the love icon on the favorites page. This streamlined process makes managing your favorite destinations quick and easy.")
    }
}