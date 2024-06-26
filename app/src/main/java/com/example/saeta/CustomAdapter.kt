package com.example.saeta;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.saeta.API.Route
import com.example.saeta.API.Stop

class CustomAdapter(private var routes:List<Route> = emptyList()): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    val titles = arrayOf("Ruta01",
        "Ruta02",
        "Ruta03",
        "Ruta04")
    val details = arrayOf("Detalles 01",
        "Detalles 02",
        "Detalles 03",
        "Detalles 04")
    val images = intArrayOf(R.drawable.ruta,
        R.drawable.ruta,
        R.drawable.ruta,
        R.drawable.ruta)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            val route = routes[i]
            viewHolder.itemTitle.text = route.long_name
            viewHolder.itemDetail.text = route.route_type
            viewHolder.itemImage.setImageResource(images[i])

    }

    override fun getItemCount(): Int {
        return routes.size
    }
    fun updateRoutes(newRoutes: List<Route>) {
        routes = newRoutes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
        }
    }
}
