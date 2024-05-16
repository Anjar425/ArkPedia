package com.example.arkpedia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.arkpedia.R


private const val ARG_DATA = "param1"

class FragmentStat : Fragment() {
    private var data: OperatorsData? = null

    @Suppress("DEPRECATION")
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

    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameLayout = view.findViewById<TextView>(R.id.name_operators)
        val imageLayout = view.findViewById<ImageView>(R.id.image_operators)
        val classImageLayout = view.findViewById<ImageView>(R.id.className_image_stat)
        val nameClass = view.findViewById<TextView>(R.id.className_stat)
        nameLayout.text = data?.name
        nameClass.text = data?.className
        Glide.with(requireContext())
            .load(data?.image)
            .into(imageLayout)

        val resourceId = resources.getIdentifier(
            data?.className?.lowercase(), "drawable", requireContext().packageName
        )
        classImageLayout.setImageResource(resourceId)

        val levelE0Seek : SeekBar = view.findViewById(R.id.level_e1)

        when (data?.rarity) {
            6, 5 -> levelE0Seek.max = 50
            4 -> levelE0Seek.max = 45
            3 -> levelE0Seek.max = 40
            else -> levelE0Seek.max = 30
        }


        levelE0Seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
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