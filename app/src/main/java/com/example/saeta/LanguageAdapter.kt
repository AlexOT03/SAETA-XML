package com.example.saeta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saeta.API.Route
import com.example.saeta.API.Stop

class LanguageAdapter: RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>(){
    private var routes: List<Route> = emptyList()
    var onItemClick : ((Route) -> Unit)? = null
    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
    }

    fun setFilteredList(stops: List<Route>){
        this.routes = stops
        notifyDataSetChanged()
    }
    fun setStops(stops: List<Route>) {
        this.routes = stops
        notifyDataSetChanged()
    }
    fun getStops(): List<Route> = routes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val route = routes[position]
        holder.logo.setImageResource(R.drawable.ruta)
        holder.titleTv.text = route.long_name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(route)
        }
    }

    override fun getItemCount(): Int {
        return routes.size
    }

}