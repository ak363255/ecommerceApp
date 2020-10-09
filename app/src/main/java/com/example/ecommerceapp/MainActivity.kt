package com.example.ecommerceapp

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleAnimation()

    }

    fun handleAnimation()
    {
        var objectAnimator:ObjectAnimator= ObjectAnimator.ofFloat(logo,"y",500F)
        var objectAnimator2:ObjectAnimator=ObjectAnimator.ofFloat(logo_text,"y",800F)
        var objectAnimnator3:ObjectAnimator=ObjectAnimator.ofFloat(splash_screen, View.ALPHA,0.3F,1F)
        objectAnimator.setDuration(1000)
        objectAnimator2.setDuration(1000)
        objectAnimnator3.setDuration(1000)
        var animatorSet:AnimatorSet= AnimatorSet()
        animatorSet.playTogether(objectAnimator,objectAnimator2,objectAnimnator3)
        animatorSet.start()
        animatorSet.addListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                var intent:Intent=Intent(this@MainActivity,LoginOrRegisterActivity::class.java)
                startActivity(intent)
                finish()

            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })

    }
}