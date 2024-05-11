package com.example.arkpedia.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.arkpedia.R


private const val ARG_DATA = "param1"

class FragmentStat : Fragment() {
    // TODO: Rename and change types of parameters
    private var data: OperatorsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameLayout = view.findViewById<TextView>(R.id.name_operators)
        val imageLayout = view.findViewById<ImageView>(R.id.image_operators)
        nameLayout.text = data?.name
        Glide.with(requireContext())
            .load(data?.image)
            .into(imageLayout)
    }

    companion object {
        @JvmStatic
        fun newInstance(data: OperatorsData) =
            FragmentStat().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA, data)
                }
            }
    }
}