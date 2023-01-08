package com.maverick.contactapp

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.maverick.contactapp.database.HistoryDatabase
import com.maverick.contactapp.models.History
import com.maverick.contactapp.viewmodel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.*

class SendMessage : AppCompatActivity() {
    lateinit var viewModel: HistoryViewModel
    private lateinit var history: History
    private lateinit var smsButton: Button
    private lateinit var message:TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var textSms:String
    private lateinit var number:String
    private lateinit var name:String
//    private lateinit var historyFragment: HistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        smsButton = findViewById(R.id.sms_btn)
        message = findViewById(R.id.et_text_message)
        progressBar = findViewById(R.id.progress_bar)

        val randomNumber = (11111..99999).shuffled().last()
        name = intent.getStringExtra("name").toString()
        number = intent.getStringExtra("number").toString()
        textSms = "Hi $name, your OTP is $randomNumber"
        message.text = textSms

//        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

//        viewModel.allHistory.observe(this, androidx.lifecycle.Observer { list ->
//            list?.let {
//                historyFragment = HistoryFragment()
//                historyFragment.updateList(it)
//                historyFragment.adapter.notifyDataSetChanged()
//            }
//        })

        smsButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                progressBar.visibility = View.GONE
                sendSms(textSms,number)
            }else{
                requestPermissions(arrayOf(android.Manifest.permission.SEND_SMS),1)
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSms(textSms,number)
        }else{
            Toast.makeText(this,"Permission denied.",Toast.LENGTH_LONG).show()
        }
    }

    private fun sendSms(textSms:String,number:String){
        try {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(number,null,textSms,null,null)
            Toast.makeText(this,"Sms sent successfully!",Toast.LENGTH_LONG).show()
//            saveToDatabase()
        }catch (e:Exception){
            Toast.makeText(this,"Failure occurred!",Toast.LENGTH_LONG).show()
        }
    }

    private fun saveToDatabase() {
        val calendar = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val formattedDate: String = simpleDateFormat.format(calendar)
        viewModel.addHistory(History(name,textSms,formattedDate))

    }
}