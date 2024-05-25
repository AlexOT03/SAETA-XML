package com.example.saeta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest

class HomeFragment : Fragment() {
    private lateinit var adView: AdView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val imageSlider = view.findViewById<ImageSlider>(R.id.slider)

        val slideModels = ArrayList<SlideModel>()

        slideModels.add(SlideModel(R.drawable.foto,ScaleTypes.CENTER_CROP));
        slideModels.add(SlideModel ("https://fastly.picsum.photos/id/866/500/500.jpg?hmac=FOptChXpmOmfR5SpiL2pp74Yadf1T_bRhBF1wJZa9hg",ScaleTypes.CENTER_CROP));
        slideModels.add(SlideModel ("https://fastly.picsum.photos/id/270/500/500.jpg?hmac=MK7XNrBrZ73QsthvGaAkiNoTl65ZDlUhEO-6fnd-ZnY",ScaleTypes.CENTER_CROP));
        slideModels.add(SlideModel ("https://fastly.picsum.photos/id/320/500/500.jpg?hmac=2iE7TIF9kIqQOHrIUPOJx2wP1CJewQIZBeMLIRrm74s",ScaleTypes.CENTER_CROP));
        slideModels.add(SlideModel ("https://fastly.picsum.photos/id/798/500/500.jpg?hmac=Bmzk6g3m8sUiEVHfJWBscr2DUg8Vd2QhN7igHBXLLfo",ScaleTypes.CENTER_CROP));
        slideModels.add(SlideModel ("https://fastly.picsum.photos/id/95/500/500.jpg?hmac=0aldBQ7cQN5D_qyamlSP5j51o-Og4gRxSq4AYvnKk2U",ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        MobileAds.initialize(requireContext())

        adView = view.findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)

        adView.visibility = View.GONE

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adView.visibility = View.VISIBLE
                super.onAdLoaded()
            }
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }
}