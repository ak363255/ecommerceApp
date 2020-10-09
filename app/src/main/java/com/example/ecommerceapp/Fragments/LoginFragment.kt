package com.example.ecommerceapp.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ecommerceapp.HomeActivity
import com.example.ecommerceapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newUser.setOnClickListener(){
            var fragmetTransaction=activity!!.supportFragmentManager.beginTransaction()
            fragmetTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right)
            fragmetTransaction.replace(R.id.fragment_container,RegisterFragment()).commit()
        }

        forgot_password.setOnClickListener(){

            var fragmetTransaction=activity!!.supportFragmentManager.beginTransaction()
            fragmetTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right)
            fragmetTransaction.add(R.id.fragment_container,ForgotPasswordFragment())
                .addToBackStack(null)
                .commit()
        }

        login.setOnClickListener(){
            var pd=ProgressDialog(context)
            pd.setMessage("Logging in...")
            pd.show()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnSuccessListener {
                    pd.dismiss()
                    context!!.startActivity(Intent(activity, HomeActivity::class.java))
                }
                .addOnFailureListener(){
                    pd.dismiss()
                    Toast.makeText(context,"fail to login ", Toast.LENGTH_LONG).show()
                }
        }

    }

}