package com.toobeeinfo.qrcodescanner.ui.ui.scanned_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toobeeinfo.qrcodescanner.R



class ScannedHistoryFragment : Fragment() {

  companion object {

      fun newInstance() : ScannedHistoryFragment{
          return ScannedHistoryFragment()
      }
  }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanned_history, container, false)
    }


}