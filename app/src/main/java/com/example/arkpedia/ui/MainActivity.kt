package com.example.arkpedia.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arkpedia.data.response.UserResponse
import com.example.arkpedia.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.arkpedia.databinding.ActivityMainBinding

// Activity utama yang menampilkan daftar user
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginisialisasi binding menggunakan view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi adapter untuk RecyclerView
        adapter = UserAdapter(this@MainActivity, arrayListOf())

        // Mengatur adapter, layout manager, dan fixed size untuk RecyclerView
        binding.rvUser.adapter = adapter
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        // Memuat data user dari API
        getUsers()
    }

    // Memuat data user dari API menggunakan Retrofit
    private fun getUsers() {
        // Memanggil API untuk mendapatkan data user
        ApiConfig.apiService().getOperators().enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        // Mengatur data user ke adapter
                        setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                // Menampilkan pesan log jika terjadi kesalahan
                Log.d("Error", t.stackTraceToString())
            }
        })
    }

    // Mengatur data user ke adapter
    private fun setData(data: ArrayList<UserResponse>) {
        if(data.isNotEmpty()) {
            adapter.setData(data)
        }
    }
}
