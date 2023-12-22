package com.example.t_rec.activity

import androidx.appcompat.app.AppCompatActivity

//class SearchNLPActivity : AppCompatActivity() {
//    private lateinit var binding: ActivitySearchNLPBinding
//    private lateinit var viewModel: SearchNLPViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySearchNLPBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        viewModel = ViewModelProvider(this)[SearchNLPViewModel::class.java]
//
//        val sectionsPagerAdapter = PagerAdapter(this)
//        sectionsPagerAdapter.username = intent.getStringExtra(EXTRA_USER).toString()
//        val viewPager = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs = binding.tabs
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//    }
//}