package com.maverick.contactapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.maverick.contactapp.models.History
import com.maverick.contactapp.viewmodel.HistoryViewModel


class HistoryFragment : Fragment() {
    //    private lateinit var historyListView: ListView
//    private lateinit var allHistory: ArrayList<History>
//    lateinit var viewModel: HistoryViewModel
//    lateinit var adapter: ArrayAdapter<History>
//    var adapterList:MutableList<History> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
//        historyListView = view.findViewById(R.id.list_view_history)
//        viewModel = ViewModelProvider(
//            this, ViewModelProvider.AndroidViewModelFactory.getInstance(
//                context?.applicationContext as Application
//            )
//        )[HistoryViewModel::class.java]
//
//        historyAdapter()
//        Log.d("history", viewModel.allHistory.value.toString())
        return view
//    }
//
//    private fun historyAdapter() {
//        allHistory = ArrayList()
//        adapterList.addAll(allHistory)
//        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, adapterList)
//        historyListView.adapter = adapter
//    }
//
//    fun updateList(newList: List<History>) {
//        allHistory = ArrayList()
//        allHistory.clear()
//        allHistory.addAll(newList)
//    }
    }
}