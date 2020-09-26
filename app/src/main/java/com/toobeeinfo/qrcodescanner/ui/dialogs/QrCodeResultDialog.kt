package com.toobeeinfo.qrcodescanner.ui.dialogs

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.DBHelper
import com.toobeeinfo.qrcodescanner.ui.db.DBHelperI
import com.toobeeinfo.qrcodescanner.ui.db.database.QrResultDataBase
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import com.toobeeinfo.qrcodescanner.ui.utils.toFormattedDisplay
import kotlinx.android.synthetic.main.layout_qr_result_show.*

class QrCodeResultDialog(var context: Context) {
    private lateinit var dialog:Dialog
    private var qrResult:QrResult? = null
    private  lateinit var dbHelperI : DBHelperI
    private var onDissmissListener :OnDissmissListener? = null

    init{
        init()
        initDialog()
    }

    private fun init() {
       // TODO("Not yet implemented")
        dbHelperI = DBHelper(QrResultDataBase.getAppDatabase(context)!!)
    }

    private fun initDialog() {
       // TODO 1.("Not yet implemented")
       dialog = Dialog(context)
       dialog.setContentView(R.layout.layout_qr_result_show)
       dialog.setCancelable(false)
       onClick()
                    }
    fun setOnDissmissListener(dissmissListener: OnDissmissListener){
        this.onDissmissListener = dissmissListener
    }
    fun show(qrResult: QrResult){
       // val now:Date = Date()
        this.qrResult = qrResult
        dialog.scannedDate.text =  qrResult?.time?.toFormattedDisplay()
        dialog.scannedText.text = qrResult.result
        dialog.favouriteIcon.isSelected = qrResult.favourite
        dialog.show()
    }
    private fun onClick() {
       // TODO 2.("Not yet implemented")
        dialog.favouriteIcon.setOnClickListener{
         if(it.isSelected){
             removeFromFavourite()
         }else{
             addToFavourite()
         }

        }
        dialog.shareResult.setOnClickListener{
         shareResult()
        }
        dialog.copyResult.setOnClickListener{
         copyResultToClipBoard()
        }
        dialog.cancelDialog.setOnClickListener{
            onDissmissListener?.onDismiss()
            dialog.dismiss()

        }
    }

    private fun addToFavourite() {
       // TODO("Not yet implemented")
        dialog.favouriteIcon.isSelected =true
        dbHelperI.addToFavourite(qrResult?.id!!)
    }

    private fun removeFromFavourite() {
       // TODO("Not yet implemented")
        dialog.favouriteIcon.isSelected = false
        dbHelperI.removeFromFavourite(qrResult?.id!!)
    }

    private fun shareResult() {
        //TODO 4("Not yet implemented")
        val txtIntent = Intent(Intent.ACTION_SEND)
        txtIntent.type ="text/plain"
        txtIntent.putExtra(Intent.EXTRA_TEXT,dialog.scannedText.text.toString())
        context.startActivity(Intent.createChooser(txtIntent, "Share QR Result"))
    }

    private fun copyResultToClipBoard() {
        //TODO 3("Not yet implemented")
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("QrCodeScannedResult",dialog.scannedText.text)
        clipboard.text = clip.getItemAt(0).text.toString()
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()

    }
   interface OnDissmissListener{
       fun onDismiss()
   }
}