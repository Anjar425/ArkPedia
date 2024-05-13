package com.example.arkpedia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arkpedia.R
import com.example.arkpedia.data.response.UserResponse
import com.example.arkpedia.data.retrofit.ApiConfig
import com.example.arkpedia.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentList : Fragment() {

    private lateinit var rvOperators: RecyclerView
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: UserAdapter


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

        getUsers()

        return rootView
    }

    private fun getUsers() {
        ApiConfig.apiService().getOperators().enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
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
