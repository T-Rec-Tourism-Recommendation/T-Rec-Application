package com.example.t_rec.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.t_rec.R
import com.example.t_rec.data.Result
import com.example.t_rec.data.DestinationAdapter
import com.example.t_rec.databinding.ActivityHomeBinding
import com.example.t_rec.model.DestinasiViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: DestinasiViewModel by viewModels()

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.rv_home)

        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager

        destinationAdapter = DestinationAdapter(emptyList())
        recyclerView.adapter = destinationAdapter

        loadData()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    true
                }
                R.id.action_favorite -> {
                    true
                }
                R.id.action_faq -> {
                    true
                }
                else -> false
            }
        }

        val filterButton = findViewById<ImageView>(R.id.filter_btn)
        filterButton.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun loadData() {
        showLoading(true)
        viewModel.destinationList.observe(this) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    destinationAdapter.setData(result.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.searchDestinations("")
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.layout.activity_home, menu)

        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.sv_home)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchDestinations(it) }
                return true
            }
        })
        return true
    }
    private fun showFilterDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Find Your Destination")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Ok") { _, _ ->
            val filterText = input.text.toString()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}
