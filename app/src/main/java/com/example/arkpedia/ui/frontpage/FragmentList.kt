package com.example.arkpedia.ui.frontpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arkpedia.R
import com.example.arkpedia.data.response.UserResponse
import com.example.arkpedia.data.retrofit.ApiConfig
import com.example.arkpedia.databinding.FragmentListBinding
import com.example.arkpedia.ui.adapter.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentList : Fragment() {

    private lateinit var rvOperators: RecyclerView
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: UserAdapter
    private lateinit var progressBar: ProgressBar
    private var call: Call<ArrayList<UserResponse>>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val rootView = binding.root

        rvOperators = rootView.findViewById(R.id.rvOperators)
        adapter = UserAdapter(requireContext(), arrayListOf())

        rvOperators.adapter = adapter
        rvOperators.setHasFixedSize(true)
        rvOperators.layoutManager = LinearLayoutManager(requireContext())

        progressBar = rootView.findViewById(R.id.progressBar)

        getUsers()

        return rootView
    }

    override fun onPause() {
        super.onPause()
        call?.cancel()
    }

    private fun getUsers() {
        progressBar.visibility = View.VISIBLE
        call = ApiConfig.apiService().getOperators()
        call?.enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                Log.d("Error", t.stackTraceToString())
            }
        })
    }

    private fun setData(data: ArrayList<UserResponse>) {
        if(data.isNotEmpty()) {
            adapter.setData(data)
        }
    }
}
