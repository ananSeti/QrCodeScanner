package com.toobeeinfo.qrcodescanner.ui.ui.mainactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.toobeeinfo.qrcodescanner.ui.ui.scanned_history.ScannedHistoryFragment
import com.toobeeinfo.qrcodescanner.ui.ui.scanner.QrScannerFragment

class MainPagerAdapter(var fm:FragmentManager) :FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
       // TODO 1.("Not yet implemented")
        return 3
    }

    override fun getItem(position: Int): Fragment {
        //TODO 2.("Not yet implemented")
       return when(position){
           0->QrScannerFragment.newInstance()
           1->ScannedHistoryFragment.newInstance()
           2->ScannedHistoryFragment.newInstance()
           else -> QrScannerFragment.newInstance()
       }
    }
}