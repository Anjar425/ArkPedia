package com.example.arkpedia.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arkpedia.R

private const val ARG_DATA = "data"
private lateinit var rvVoiceLines: RecyclerView
class FragmentVoicelines : Fragment() {
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
        return inflater.inflate(R.layout.fragment_voicelines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvVoiceLines = view.findViewById(R.id.rvVoiceLines)

        showRecycleList(data!!.voicelines)


    }

    private fun showRecycleList(list: ArrayList<VoiceLines>) {
        rvVoiceLines.layoutManager = LinearLayoutManager(requireContext())
        val listAnimeAdapter = VoiceLinesAdapter(list)
        rvVoiceLines.adapter = listAnimeAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(data: OperatorsData) =
            FragmentVoicelines().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA, data)
                }
            }
    }
}