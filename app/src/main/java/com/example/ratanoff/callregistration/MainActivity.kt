package com.example.ratanoff.callregistration

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var input: EditText
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = code
        textView = result

        if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 3)
        }
    }


    class CallReceiver : PhonecallReceiver() {

        interface listener{
            fun validate()
        }

        override fun onIncomingCallStarted(ctx: Context, number: String, start: Date) {
            Toast.makeText(ctx, "Call $number", Toast.LENGTH_SHORT).show()

        }
    }

}
