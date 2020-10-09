package com.example.ecommerceapp.Fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.transition.Explode
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.ecommerceapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ForgotPasswordFragment : Fragment() {
    lateinit var resetContainer:ViewGroup
    lateinit var redEmail:ImageView
    lateinit var resetPasswordText:TextView
    lateinit var progressBar2:ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_forgot_password, container, false)
        resetContainer=view.findViewById(R.id.linearLayout)
        redEmail=view.findViewById(R.id.redEmail)
        resetPasswordText=view.findViewById(R.id.resetPasswordText)
        progressBar2=view.findViewById(R.id.progressBar2)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        submit.setOnClickListener(){
            if(email.text.toString().toString().length==0 ){
                email.error="Please Enter your register email address"
            }
            else{
                resetPasswordText.visibility=View.GONE
                submit.isEnabled=false
                val transition=Explode()
                transition.duration=3000
                progressBar2.visibility=View.VISIBLE
                TransitionManager.beginDelayedTransition(resetContainer,transition)
                redEmail.visibility=View.VISIBLE
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.text.toString())
                    .addOnSuccessListener {

                        redEmail.setImageResource(R.drawable.ic_greenemail)
                        TransitionManager.beginDelayedTransition(resetContainer,transition)
                        resetPasswordText.visibility=View.VISIBLE
                        redEmail.visibility=View.VISIBLE
                        submit.isEnabled=false
                        submit.setTextColor(resources.getColor(R.color.grey))
                        Toast.makeText(context,"link to user email",Toast.LENGTH_LONG).show()
                        Log.d("reset","password sent successfully")
                    }.addOnFailureListener(){
                        submit.isEnabled=true
                        Toast.makeText(context,"fail to reset password ", Toast.LENGTH_LONG).show()
                    }
                progressBar2.visibility=View.GONE
                redEmail.visibility=View.GONE
            }
        }


    }



}