package com.example.saeta

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*

class RoutesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_routes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(requireContext() ,DetailedActivity::class.java)
            intent.putExtra("ruta", it)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        return view
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList(){
        mList.add(LanguageData("Ruta 01", R.drawable.foto))
        mList.add(LanguageData("Ruta 02", R.drawable.foto2))
        mList.add(LanguageData("Ruta 03", R.drawable.foto))
        mList.add(LanguageData("Ruta 04", R.drawable.foto))
        mList.add(LanguageData("Ruta 05", R.drawable.foto2))
        mList.add(LanguageData("Ruta 06", R.drawable.foto))
        mList.add(LanguageData("Ruta 07", R.drawable.foto))
        mList.add(LanguageData("Ruta 08", R.drawable.foto))
    }
}