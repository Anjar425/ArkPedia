package com.example.arkpedia.ui.frontpage

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.arkpedia.R
import com.example.arkpedia.data.response.UserResponse
import com.example.arkpedia.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class FragmentHome : Fragment() {
    private lateinit var progressBar: ProgressBar
    private var call: Call<ArrayList<UserResponse>>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image: ImageView = view.findViewById(R.id.image_home)
        val appName: TextView = view.findViewById(R.id.app_name_home)
        val desc: TextView = view.findViewById(R.id.desc_home)
        progressBar = view.findViewById(R.id.progressBar_home)

        progressBar.visibility = View.VISIBLE

        try {
            setViewLayout(image, appName, desc)
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun onPause() {
        super.onPause()
        call?.cancel()
    }

    private fun setViewLayout(image: ImageView, textName: TextView, textDesc: TextView) {
        progressBar.visibility = View.VISIBLE
        try {
            call = ApiConfig.apiService().getOperators()
            call?.enqueue(object : Callback<ArrayList<UserResponse>> {
                override fun onResponse(call: Call<ArrayList<UserResponse>>, response: Response<ArrayList<UserResponse>>) {
                    if (response.isSuccessful) {
                        val operators = response.body() ?: arrayListOf()

                        if (operators.isNotEmpty()) {
                            var randomOperator = operators.random()
                            var artFilter = randomOperator.art.filter { it.name != "Base" && it.name != "E1" }

                            while (artFilter.isEmpty()){
                                randomOperator = operators.random()
                                artFilter = randomOperator.art.filter { it.name != "Base" && it.name != "E1" }
                            }

                            val randomArt = artFilter.random()

                            randomArt.let {
                                Glide.with(requireContext())
                                    .load(it.link)
                                    .listener(object : RequestListener<Drawable> {
                                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                            progressBar.visibility = View.VISIBLE
                                            return false
                                        }

                                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                            textName.text = "ArkPedia"
                                            textDesc.text = "Aplikasi yang menyajikan data-data dari setiap Operators pada Game Arknights mulai dari detail, background, serta voiceline dari setiap Operators"
                                            progressBar.visibility = View.GONE
                                            return false
                                        }
                                    })
                                    .into(image)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                    Log.e("API Error", "Failed to retrieve operators", t)
                }
            })
        } catch (e: Exception) {
            Log.e("Exception", "Error in setViewLayout", e)
        }
    }
}