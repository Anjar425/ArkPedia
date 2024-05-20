package com.example.arkpedia.ui.frontpage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.arkpedia.R

class FragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val githubBtn: Button = view.findViewById(R.id.github_id)
        val sendMeBtn: Button = view.findViewById(R.id.sendme_id)

        githubBtn.setOnClickListener{
            val githubPage = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Anjar425"))
            startActivity(githubPage)
        }

        sendMeBtn.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ekoginanjar4@gmail.com"))
        intent.putExtra(Intent.EXTRA_TEXT, "Nama : Eko Ginanjar Basuki Rahmat\n NIM : L0122052")
        intent.setType("message/rcf822")
        startActivity(Intent.createChooser(intent, "Choose Email :"))
    }
}