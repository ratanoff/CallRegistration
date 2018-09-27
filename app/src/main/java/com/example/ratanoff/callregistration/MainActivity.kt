package com.example.ratanoff.callregistration

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 3)
        }
    }

    fun validate(number: String) {
        if (number.takeLast(4) != code.text.toString()) {
            result.visibility = View.VISIBLE
            result.text = "Failed"
            return
        }
        result.text = "Registered!"

        val stateSet = intArrayOf(android.R.attr.state_checked)
        checkbox.visibility = View.VISIBLE
        checkbox.setImageState(stateSet, true)
    }

    override fun onStart() {
        super.onStart()
        val receiver = CallReceiver()
        receiver.setMainActivityHandler(this)
        val intentFilter = IntentFilter("android.intent.action.PHONE_STATE")
        registerReceiver(receiver, intentFilter)
    }


    class CallReceiver : PhonecallReceiver() {

        lateinit var mainActivity: MainActivity

        fun setMainActivityHandler(main: MainActivity) {
            mainActivity = main
        }

        override fun onIncomingCallStarted(ctx: Context, number: String, start: Date) {
            mainActivity.validate(number)
        }
    }

}
