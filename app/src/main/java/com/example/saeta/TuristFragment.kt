import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saeta.Places_routes
import com.example.saeta.R
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class TuristFragment : Fragment() {
    private var listplaces = ArrayList<Places_routes>()
    private lateinit var adapter: AdapterPlaces

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_turist, container, false)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list()
        recycleview()

        view.findViewById<TextInputEditText>(R.id.etBuscador).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })

    }
    // Función que almacena datos estáticos
    private fun list() {
        listplaces.add(Places_routes("Pomoca", R.drawable.foto, "Esta en villahermosa", 1202.02, 1020.20))
        listplaces.add(Places_routes("Acapulco", R.drawable.foto2, "Queda en merida", 1202.02, 1020.20))
        listplaces.add(Places_routes("Choapas", R.drawable.ic_heart, "Queda por chiapas", 1202.02, 1020.20))
        listplaces.add(Places_routes("Merida", R.drawable.baseline_search_24, "Esta por el tren maya", 1202.02, 1020.20))
        listplaces.add(Places_routes("Pomoca", R.drawable.ic_route, "Esta en villahermosa", 1202.02, 1020.20))
        listplaces.add(Places_routes("Acapulco", R.drawable.ic_home, "Queda en merida", 1202.02, 1020.20))
        listplaces.add(Places_routes("Choapas", R.drawable.ic_heart, "Queda por chiapas", 1202.02, 1020.20))
        listplaces.add(Places_routes("Merida", R.drawable.baseline_search_24, "Esta por el tren maya", 1202.02, 1020.20))
    }

    // Función que llena el RecyclerView con los datos de la lista
    private fun recycleview() {
        view?.findViewById<RecyclerView>(R.id.rvLista)?.layoutManager = LinearLayoutManager(requireContext())
        adapter = AdapterPlaces(listplaces)
        view?.findViewById<RecyclerView>(R.id.rvLista)?.adapter = adapter
    }

    // Función para filtrar la lista
    private fun filter(texto: String) {
        val listaFiltrada = arrayListOf<Places_routes>()

        listplaces.forEach {
            if (it.title.toLowerCase(Locale.ROOT).contains(texto.toLowerCase(Locale.ROOT))) {
                listaFiltrada.add(it)
            }
        }

        adapter.filter(listaFiltrada)
    }
}
