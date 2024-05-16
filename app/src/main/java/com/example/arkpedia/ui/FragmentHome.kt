package com.example.arkpedia.ui

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var progressBar: ProgressBar
    private var call: Call<ArrayList<UserResponse>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        val app_name: TextView = view.findViewById(R.id.app_name_home)
        val desc: TextView = view.findViewById(R.id.desc_home)
        progressBar = view.findViewById(R.id.progressBar_home)

        progressBar.visibility = View.VISIBLE

        try {
            setViewLayout(image, app_name, desc)
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun onPause() {
        super.onPause()
        call?.cancel()
    }

    private fun setViewLayout(image: ImageView, text_name: TextView, text_decs: TextView) {
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
                                            text_name.text = "ArkPedia"
                                            text_decs.text = "Aplikasi yang menyajikan data-data dari setiap Operators pada Game Arknights mulai dari detail, background, serta voiceline dari setiap Operators"
                                            progressBar.visibility = View.GONE
                                            return false
                                        }
                                    })
                                    .into(image)
                            }
                        } else {
                            // Handle case when operators collection is empty
                            // You may want to show an error message or handle this case according to your application logic
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                    Log.e("API Error", "Failed to retrieve operators", t)
                    // Handle failure case
                }
            })
        } catch (e: Exception) {
            Log.e("Exception", "Error in setViewLayout", e)
            // Handle any other exceptions
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}