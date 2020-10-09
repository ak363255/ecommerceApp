package com.example.ecommerceapp.Fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ecommerceapp.HomeActivity
import com.example.ecommerceapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    lateinit var username:EditText
    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var confirmPassword:EditText
    lateinit var registerButton:Button
    lateinit var map:MutableMap<String,String>
   var myview:View?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        myview=inflater.inflate(R.layout.fragment_register, container, false)
        return myview;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        username=myview!!.findViewById(R.id.user_name)
        name=myview!!.findViewById(R.id.name)
        email=myview!!.findViewById(R.id.email)
        password=myview!!.findViewById(R.id.password)
        confirmPassword=myview!!.findViewById(R.id.confirm_password)
        registerButton=myview!!.findViewById(R.id.register_button)

        registerButton.setOnClickListener(){
            if(checkInputs()){
                var pd= ProgressDialog(context)
                pd.setMessage("Logging in...")
                pd.show()
                map= mutableMapOf()
                map.put("user",username.text.toString())
                map.put("name",name.text.toString())
                map.put("email",email.text.toString())
              FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                  .addOnSuccessListener {
                      var db= Firebase.firestore
                      db.collection("users").add(map)
                          .addOnSuccessListener {
                              pd.dismiss()
                              startActivity(Intent(context,HomeActivity::class.java))

                          }.addOnFailureListener(){
                              pd.dismiss()
                              Toast.makeText(context,"fail to register user ",Toast.LENGTH_LONG).show()
                              Log.d("Login","fail to load data in database")
                          }
                  }.addOnFailureListener(){
                      Log.d("Login","message is "+it.message.toString())
                  }

            }
        }
        already_have_account_text.setOnClickListener(){
            var fragmentTransaction=activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right)
            fragmentTransaction.replace(R.id.fragment_container,LoginFragment()).commit()
        }
    }

    private fun checkInputs() :Boolean{

        var usernameText=username.text.toString()
        var nameText=name.text.toString()
        var emailText=email.text.toString()
        var passwordText=password.text.toString()
        var conformPasswordText=confirmPassword.text.toString()
        if(usernameText.length==0){
            username.error="Please Enter your username"
            return false
        }
        else{
            if(nameText.length==0){
                name.error="Please enter your name"
                return false
            }
            else{
                if(emailText.length==0){
                    email.error="please enter you email"
                    return false
                }
                else{
                    if(passwordText.length<4){
                        password.error="Password lenght should be atlest 4"
                        return false
                    }
                    else{
                        if(conformPasswordText.length<=4||!conformPasswordText.equals(passwordText)){
                            confirmPassword.error="password do not match"
                            return false
                        }


                    }
                }
            }
        }

        return true

    }

}