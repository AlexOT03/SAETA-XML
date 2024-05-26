package com.example.saeta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.widget.TextView
import android.widget.ImageView

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var title: String? = null
    private var description: String? = null
    private var extraInfo: String? = null
    private var imageResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            description = it.getString(ARG_DESCRIPTION)
            extraInfo = it.getString(ARG_EXTRA_INFO)
            imageResId = it.getInt(ARG_IMAGE_RES_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val extraInfoTextView = view.findViewById<TextView>(R.id.extraInfoTextView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)

        titleTextView.text = title
        descriptionTextView.text = description
        extraInfoTextView.text = extraInfo
        imageResId?.let { imageView.setImageResource(it) }


        return view
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_EXTRA_INFO = "extra_info"
        private const val ARG_IMAGE_RES_ID = "image_res_id"

        fun newInstance(title: String, description: String, extraInfo: String, imageResId: Int): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putString(ARG_EXTRA_INFO, extraInfo)
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = args
            return fragment
        }
    }
}