package com.toobeeinfo.qrcodescanner.ui.ui.scanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.zxing.Result
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.DBHelper
import com.toobeeinfo.qrcodescanner.ui.db.DBHelperI
import com.toobeeinfo.qrcodescanner.ui.db.database.QrResultDataBase
import com.toobeeinfo.qrcodescanner.ui.dialogs.QrCodeResultDialog
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

// TODO: 1 Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QrScannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QrScannerFragment : Fragment() , ZXingScannerView.ResultHandler {
    // TODO: Rename and change types of parameters
   // private var param1: String? = null
   // private var param2: String? = null
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QrScannerFragment.
         */
        // TODO: Rename and change types and number of parameters
        // @JvmStatic
        fun newInstance():QrScannerFragment {
            return QrScannerFragment()
        }
    }
  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/
    //anan
    private lateinit var  mView: View
    private lateinit var scannerView: ZXingScannerView
    private lateinit var resultDialog:QrCodeResultDialog
    private lateinit var dbHelperI: DBHelperI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        init()
        //initialzeQrScanner()
        //replace with
        initView()
        onClicks()
        return  mView.rootView
    }

    private fun init() {
       // TODO("Not yet implemented")
        dbHelperI = DBHelper(QrResultDataBase.getAppDatabase(context!!)!!)
    }

    private fun initView() {
       // TODO("Not yet implemented")
        initialzeQrScanner()
        setResultDialog()
    }

    private fun setResultDialog() {
       // TODO("Not yet implemented")
        resultDialog = QrCodeResultDialog(context!!)
        resultDialog.setOnDissmissListener(object:QrCodeResultDialog.OnDissmissListener{
            override fun onDismiss() {
                //TODO("Not yet implemented")
                scannerView.resumeCameraPreview(this@QrScannerFragment)
            }
        })

    }

    private fun onClicks() {
        //TODO("Not yet implemented")
        mView.flashToggle.setOnClickListener{
            if(it.isSelected){
                offFlashLight()
            }else {
                onFlashLight()
            }
        }
    }

    private fun offFlashLight() {
        //TODO("Not yet implemented")
        mView.flashToggle.isSelected = false
        scannerView.flash = false
    }

    private fun onFlashLight() {
        //TODO("Not yet implemented")
        mView.flashToggle.isSelected = true
        scannerView.flash = true
    }

    private fun initialzeQrScanner() {
        //TODO("Not yet implemented")
        scannerView = ZXingScannerView(context!!)
        scannerView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorTranslucent))
        scannerView.setBorderColor(ContextCompat.getColor(context!!,R.color.colorPrimaryDark))
        scannerView.setLaserColor(ContextCompat.getColor(context!!,R.color.colorPrimaryDark))
        scannerView.setBorderStrokeWidth(10)
        scannerView.setAutoFocus(true)
        scannerView.setSquareViewFinder(true)
        scannerView.setResultHandler(this)
        startQrCamera()
        mView.containerScanner.addView(scannerView)

    }
    private fun startQrCamera(){
        scannerView.startCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }
    override fun handleResult(rawResult: Result?) {
       // TODO("Not yet implemented")
        onQrResult(rawResult!!.text)
       // Toast.makeText(context!!,rawResult?.text,Toast.LENGTH_SHORT).show()
      //  scannerView.resumeCameraPreview(this)
    }

    private fun onQrResult(text: String?) {
       if(text.isNullOrEmpty()){
           Toast.makeText(context!!,"Empty Qr Code", Toast.LENGTH_SHORT).show()
       }else{
           saveToDatabase(text)
       }

    }

    private fun saveToDatabase(result: String) {
       val insertRowId = dbHelperI.insertQrResult(result)
       val qrResult = dbHelperI.getQRResult(insertRowId)
        resultDialog.show(qrResult)

    }


}