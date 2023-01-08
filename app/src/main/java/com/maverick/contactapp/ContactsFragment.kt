package com.maverick.contactapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException


class ContactsFragment : Fragment() {
    private lateinit var contactListView: ListView

    companion object {
        const val TAG = "ContactsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        contactListView = view.findViewById(R.id.list_view)

        val jsonArray = getContactsFromAsset(this.requireContext())
        Log.d(TAG, jsonArray.toString())
        if (jsonArray != null)
            contactAdapter(jsonArray)
        return view
    }

    private fun contactAdapter(jsonArray: JSONArray) {
        val adapterList: MutableList<String> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            adapterList.add(jsonObject.getString("firstName") + " " + jsonObject.getString("lastName"))
        }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, adapterList)
        contactListView.adapter = adapter
        contactListView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val item = parent.getItemAtPosition(position) as String
                val intent = Intent(context, ContactInfo::class.java)
                try {
                    intent.putExtra("contact", jsonArray.getJSONObject(position).toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                startActivity(intent)
            }
    }

    private fun getContactsFromAsset(context: Context): JSONArray? {
        lateinit var jsonString: String
        val jsonArray: JSONArray?
        try {
            jsonString = context.assets.open("ContactsJson.json")
                .bufferedReader()
                .use { it.readText() }
            val jsonObject: JSONObject = JSONTokener(jsonString).nextValue() as JSONObject
            jsonArray = jsonObject.getJSONArray("contacts")
            Log.d(TAG, jsonArray.toString())
        } catch (ioException: IOException) {
            Log.d("ContactsFragment", "could not read json file.")
            return null
        }
        return jsonArray
    }
}