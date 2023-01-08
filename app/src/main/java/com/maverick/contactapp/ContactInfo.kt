package com.maverick.contactapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.text.SimpleDateFormat
import java.util.*


class ContactInfo : AppCompatActivity() {
    private lateinit var contactName: TextView
    private lateinit var mobileNumber: TextView
    private lateinit var date: TextView
    private lateinit var sendOtp: Button
    private lateinit var jsonObject: JSONObject
    private lateinit var name: String
    private lateinit var number: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)

        contactName = findViewById(R.id.tv_contact_name)
        mobileNumber = findViewById(R.id.tv_phone_number)
        date = findViewById(R.id.tv_date)
        sendOtp = findViewById(R.id.send_otp_btn)

        val contact = intent.getStringExtra("contact")
        try {
            jsonObject = JSONTokener(contact).nextValue() as JSONObject
            name = "Name: " + jsonObject.getString("firstName") + " " + jsonObject.getString(
                "lastName"
            ).trim()
            contactName.text = name
            number = "Mobile no: " + jsonObject.getString("mobileNo")
            mobileNumber.text = number

            val calendar = Calendar.getInstance().time
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate: String = "Date: " + simpleDateFormat.format(calendar)
            date.text = formattedDate
        } catch (e: Exception) {
            e.printStackTrace()
        }

        sendOtp.setOnClickListener {
            val intent = Intent(this@ContactInfo, SendMessage::class.java)
            try {
                intent.putExtra(
                    "name", jsonObject.getString("firstName") + " " + jsonObject.getString(
                        "lastName"
                    )
                )
                intent.putExtra("phoneNo", number)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@ContactInfo, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        super.onBackPressed()
    }
}