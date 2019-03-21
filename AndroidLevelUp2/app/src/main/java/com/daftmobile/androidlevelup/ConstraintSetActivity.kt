package com.daftmobile.androidlevelup

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_square.*

class ConstraintSetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square)
//        nextConstraints = ConstraintSet().apply {
//            clone(contentView.context, R.layout.activity_square_alter)
//        }

        square.setOnClickListener{
            relayout(contentView)
        }
    }

    private fun relayout(contentView: ConstraintLayout){
        // create new constraints
//        val oldConstraints = ConstraintSet().apply{
//            clone(contentView)
//        }

        val newConstraints = ConstraintSet()

        newConstraints.apply {
            clone(contentView.context, R.layout.activity_square_alter)
        }


        // begin transition
        TransitionManager.beginDelayedTransition(contentView)

        // apply new constraints
        newConstraints.applyTo(contentView)
    }

}
