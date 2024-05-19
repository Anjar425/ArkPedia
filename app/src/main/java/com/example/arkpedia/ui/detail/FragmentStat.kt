package com.example.arkpedia.ui.detail

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
import com.example.arkpedia.data.parcelize.OperatorsData


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

        // Declare Layout
        val nameLayout: TextView = view.findViewById(R.id.name_operators)
        val imageLayout: ImageView= view.findViewById(R.id.image_operators)
        val classImageLayout: ImageView = view.findViewById(R.id.className_image_stat)
        val classNameLayout:TextView = view.findViewById(R.id.className_stat)
        val levelE0Seek: SeekBar = view.findViewById(R.id.seek_level_e0)
        val hpLayout:TextView = view.findViewById(R.id.maxHP_e0)
        val atkLayout:TextView = view.findViewById(R.id.atk_e0)
        val defLayout:TextView = view.findViewById(R.id.def_e0)
        val resLayout:TextView = view.findViewById(R.id.res_e0)
        val redeployLayout:TextView = view.findViewById(R.id.redeploy_e0)
        val dpLayout:TextView = view.findViewById(R.id.dp_e0)
        val blockLayout:TextView = view.findViewById(R.id.block_e0)
        val intervalLayout:TextView = view.findViewById(R.id.atk_interfal_e0)
        val levelLayout:TextView = view.findViewById(R.id.level)

        // Declare Value
        val name = data?.name
        val className = data?.className
        val image = data?.image
        val rarity = data?.rarity

        var hp: Double = (data?.base?.hp?.toDoubleOrNull() ?: 0.0)
        var atk: Double = (data?.base?.atk?.toDoubleOrNull() ?: 0.0)
        var def: Double = (data?.base?.def?.toDoubleOrNull() ?: 0.0)
        val res: Double = (data?.base?.resist?.toDoubleOrNull() ?: 0.0)
        val redeploy: Double = (data?.base?.deploy?.toDoubleOrNull() ?: 0.0)
        val dp: Double = (data?.base?.cost?.toDoubleOrNull() ?: 0.0)
        val block: Double = (data?.base?.block?.toDoubleOrNull() ?: 0.0)
        val interval: Double = (data?.base?.interval?.toDoubleOrNull() ?: 0.0)

        nameLayout.text = name
        classNameLayout.text = className
        Glide.with(requireContext())
            .load(image)
            .into(imageLayout)

        val resourceId = resources.getIdentifier(
            className?.lowercase(), "drawable", requireContext().packageName
        )
        classImageLayout.setImageResource(resourceId)

        when (rarity) {
            6, 5 -> levelE0Seek.max = 50
            4 -> levelE0Seek.max = 45
            3 -> levelE0Seek.max = 40
            else -> levelE0Seek.max = 30
        }

        val maxLevel = levelE0Seek.max

        hpLayout.text = "Max HP: " + hp.toInt().toString()
        atkLayout.text = "ATK: " + atk.toInt().toString()
        defLayout.text = "DEF: " +  def.toInt().toString()
        resLayout.text = "RES: " +  res.toInt().toString()
        redeployLayout.text ="Redeployment Time: " + redeploy.toInt().toString()
        dpLayout.text = "DP Cost: " + dp.toInt().toString()
        blockLayout.text = "Block: " + block.toInt().toString()
        intervalLayout.text = "Interval: " + interval.toInt().toString()
        levelLayout.text = "Level : 1"


        levelE0Seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hp = calculateStat((data?.base?.hp?.toDoubleOrNull() ?: 0.0), (data?.e0max?.hp?.toDoubleOrNull() ?: 0.0), progress, maxLevel)
                atk = calculateStat((data?.base?.atk?.toDoubleOrNull() ?: 0.0), (data?.e0max?.atk?.toDoubleOrNull() ?: 0.0), progress, maxLevel)
                def = calculateStat((data?.base?.def?.toDoubleOrNull() ?: 0.0), (data?.e0max?.def?.toDoubleOrNull() ?: 0.0), progress, maxLevel)

                hpLayout.text = "Max HP: " + hp.toInt().toString()
                atkLayout.text = "ATK: " + atk.toInt().toString()
                defLayout.text = "DEF: " +  def.toInt().toString()
                levelLayout.text = "Level : " + progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    fun calculateStat(baseData: Double, finalData: Double, level: Int, maxLevel: Int): Double {
        return baseData + ((finalData - baseData) * (level - 1) * baseData) / ((maxLevel - 1) * baseData)
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