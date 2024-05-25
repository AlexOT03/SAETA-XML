package com.example.saeta

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val btnFacebook = view.findViewById<Button>(R.id.textButton_facebook)
        val btnGmail = view.findViewById<Button>(R.id.textButton_gmail)
        val btnBlog = view.findViewById<Button>(R.id.textButton_blog)

        btnFacebook.setOnClickListener {
            val facebookUrl = "https://www.facebook.com/profile.php?id=100066940217670"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
            startActivity(intent)
        }

        btnGmail.setOnClickListener {
            Toast.makeText(
                activity, "No disponible 😥",
                Toast.LENGTH_LONG).show();

        }

        btnBlog.setOnClickListener {
            val webPageUrl = "https://saetavsa.blogspot.com/?fbclid=IwZXh0bgNhZW0CMTAAAR1Sz8ULJWAbaserjkfk-6UjwHoeRtmE7PuEXMnXHYlvqHjoxyP6-1HhQ6U_aem_AU1GEOYLvVJTDhg4PjCpmMs3lrqXmeok9nbblNzlog18FyCQbErXLpdECY_6WW9eVMNndubIe_D2N28UwEzZm0tw"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webPageUrl))
            startActivity(intent)
        }

        return view
    }
}