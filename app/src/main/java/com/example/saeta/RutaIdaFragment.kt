package com.example.saeta

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class RutaIdaFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ruta_ida, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mGoogleMap = googleMap

        mGoogleMap?.addMarker(MarkerOptions()
            .position(LatLng(12.321,13.432))
            .title("Parada 1")
            .snippet("Description for Marker 1")
        )

        mGoogleMap?.addMarker(MarkerOptions()
            .position(LatLng(12.987, 14.345))
            .title("parada 3")
            .snippet("Description for Draggable Marker")
        )

        mGoogleMap?.addMarker(MarkerOptions()
            .position(LatLng(12.654, 13.987))
            .title("parada 2")
            .snippet("Description for Custom Marker")
            .zIndex(1f)
        )

        val routePoints = listOf(
            LatLng(12.321, 13.432),
            LatLng(12.654, 13.987),
            LatLng(12.987, 14.345)
        )

        drawRoute(routePoints)
        zoomToRoute(routePoints)

        mGoogleMap.setOnMarkerClickListener { marker ->
            showMarkerInfoBottomSheet(marker)
            true
        }
    }

    private fun drawRoute(routePoints: List<LatLng>) {
        val polylineOptions = PolylineOptions()
            .addAll(routePoints)
            .color(Color.BLUE) // Elige el color que prefieras
            .width(10f) // Ajusta el ancho de la línea

        mGoogleMap.addPolyline(polylineOptions)
    }

    private fun zoomToRoute(routePoints: List<LatLng>) {
        val builder = LatLngBounds.Builder()
        for (point in routePoints) {
            builder.include(point)
        }
        val bounds = builder.build()
        val padding = 100 // Espacio adicional alrededor de los bordes de la ruta
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        mGoogleMap.moveCamera(cu)
    }

    private fun showMarkerInfoBottomSheet(marker: Marker) {
        val bottomSheet = when (marker.zIndex.toInt()) {
            1 -> BottomSheetFragment.newInstance(marker.title ?: "", marker.snippet ?: "", "Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2Información extra para Parada 2", R.drawable.foto)
            else -> BottomSheetFragment.newInstance(marker.title ?: "", marker.snippet ?: "", "Información extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otrosInformación extra para otros", R.drawable.foto2)
        }
        bottomSheet.show(childFragmentManager, bottomSheet.tag)
    }

}