package com.maverick.contactapp.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.maverick.contactapp.ContactsFragment
import com.maverick.contactapp.HistoryFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ContactsFragment()
            1 -> HistoryFragment()
            else -> ContactsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
         when(position){
            0 -> return "Contacts"
            1 -> return "History"
        }
        return super.getPageTitle(position)
    }

}