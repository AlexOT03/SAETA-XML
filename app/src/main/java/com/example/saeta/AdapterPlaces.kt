import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saeta.Detail_Place_Activity
import com.example.saeta.Places_routes
import com.example.saeta.R


class AdapterPlaces(
    var List_routes: ArrayList<Places_routes>
) : RecyclerView.Adapter<AdapterPlaces.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageplace: ImageView = itemView.findViewById(R.id.imageplace)
        val titleplace: TextView = itemView.findViewById(R.id.titleplace)
        val descriptionplace: TextView = itemView.findViewById(R.id.descriptionplace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = List_routes[position]

        holder.titleplace.text = place.title
        holder.descriptionplace.text = place.description
        holder.imageplace.setImageResource(place.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Detail_Place_Activity::class.java)

            // Puedes pasar los valores del elemento de tarjeta a través del Intent
            intent.putExtra("titulo", place.title)
            intent.putExtra("descripcion", place.description)
            intent.putExtra("imagen", place.image)

            // Iniciar la nueva actividad
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return List_routes.size
    }
    fun filter(list_filter: ArrayList<Places_routes>) {
        this.List_routes = list_filter
        notifyDataSetChanged()
    }
}
