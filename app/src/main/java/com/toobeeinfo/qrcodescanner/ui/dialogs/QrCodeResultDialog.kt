package com.toobeeinfo.qrcodescanner.ui.dialogs

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import com.toobeeinfo.qrcodescanner.ui.utils.toFormattedDisplay
import kotlinx.android.synthetic.main.layout_qr_result_show.*

class QrCodeResultDialog(var context: Context) {
    private lateinit var dialog:Dialog
    private var qrResult:QrResult? = null
    init{
        initDialog()
    }

    private fun initDialog() {
       // TODO 1.("Not yet implemented")
       dialog = Dialog(context)
       dialog.setContentView(R.layout.layout_qr_result_show)
       dialog.setCancelable(false)
       onClick()
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

        }
        dialog.shareResult.setOnClickListener{
         shareResult()
        }
        dialog.copyResult.setOnClickListener{
         copyResultToClipBoard()
        }
        dialog.cancelDialog.setOnClickListener{
         dialog.dismiss()

        }
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

}