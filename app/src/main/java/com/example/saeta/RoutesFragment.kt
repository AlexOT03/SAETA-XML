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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saeta.API.Stop
import com.example.saeta.API.ViewModels.RouteViewModel
import com.example.saeta.API.ViewModels.StopsViewModel
import java.util.*

class RoutesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter
    private val routeViewModel: RouteViewModel by viewModels()


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

        adapter = LanguageAdapter()
        recyclerView.adapter = adapter

        observeStops()


        adapter.onItemClick = {route->
            val intent = Intent(requireContext() ,DetailedActivity::class.java)
            intent.putExtra("ruta", route.id.toString())
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
            val filteredList = adapter.getStops().filter { it.long_name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setStops(filteredList)
            }
        }
    }

    private fun observeStops() {
        routeViewModel.routes.observe(viewLifecycleOwner) { routes ->
            adapter.setStops(routes)
        }
        //logica para mostrar el error en caso
        //de que falle la conexion
        //routeViewModel.error.observe(viewLifecycleOwner){error->
        //de preferencia un TextView para mostrarlo
        // Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        //}
    }

    private fun addDataToList(){
        mList.add(LanguageData("Ruta 01", R.drawable.ruta))
        mList.add(LanguageData("Ruta 02", R.drawable.ruta))
        mList.add(LanguageData("Ruta 03", R.drawable.ruta))
        mList.add(LanguageData("Ruta 04", R.drawable.ruta))
        mList.add(LanguageData("Ruta 05", R.drawable.ruta))
        mList.add(LanguageData("Ruta 06", R.drawable.ruta))
        mList.add(LanguageData("Ruta 07", R.drawable.ruta))
        mList.add(LanguageData("Ruta 08", R.drawable.ruta))
    }
}