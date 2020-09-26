package com.toobeeinfo.qrcodescanner.ui.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.DBHelperI
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import com.toobeeinfo.qrcodescanner.ui.dialogs.QrCodeResultDialog
import com.toobeeinfo.qrcodescanner.ui.utils.gone
import com.toobeeinfo.qrcodescanner.ui.utils.toFormattedDisplay
import com.toobeeinfo.qrcodescanner.ui.utils.visible
import kotlinx.android.synthetic.main.layout_single_item_qr_result.view.*

class ScannedResultListAdapter(
    var dbHelperI: DBHelperI,
    var context:Context,
    private var listOfScannedResult:MutableList<QrResult>
):
    RecyclerView.Adapter<ScannedResultListAdapter.ScannedResultListViewHolder>(){

   private var resultDialog:QrCodeResultDialog =
       QrCodeResultDialog(context)

    inner class ScannedResultListViewHolder(var view:View): RecyclerView.ViewHolder(view){
        fun bind(qrResult: QrResult,position: Int){
          view.result.text = qrResult.result
          view.tvTime.text = qrResult.time.toFormattedDisplay()
          setFavourite(qrResult.favourite)
          onClicks(qrResult,position)

        }

        private fun onClicks(qrResult: QrResult,position: Int) {
           // TODO("Not yet implemented")
            view.setOnClickListener{
                resultDialog.show(qrResult)
            }
            view.setOnLongClickListener {
                showDeleteDialog(qrResult, position)
                return@setOnLongClickListener true
            }

        }

        private fun showDeleteDialog(qrResult: QrResult, position: Int) {
            androidx.appcompat.app.AlertDialog.Builder(context, R.style.CustomAlertDialog).setTitle(context.getString(R.string.delete))
                .setMessage(context.getString(R.string.want_to_delete))
                .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                    deleteThisRecord(qrResult, position)
                }
                .setNegativeButton(context.getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }.show()
        }
        private fun deleteThisRecord(qrResult: QrResult, position: Int) {
            dbHelperI.deleteQrResult(qrResult.id!!)
            listOfScannedResult.removeAt(position)
            notifyItemRemoved(position)
        }

        private fun setFavourite(favourite: Boolean) {
           // TODO("Not yet implemented")
         if(favourite){
           // view.favouriteIcon.visibility = View.VISIBLE
             view.favouriteIcon.visible()
         }else{
          //view.favouriteIcon.visibility = View.GONE
             view.favouriteIcon.gone()
         }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScannedResultListAdapter.ScannedResultListViewHolder {
      // TODO("Not yet implemented")
        return ScannedResultListViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_single_item_qr_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScannedResultListAdapter.ScannedResultListViewHolder, position: Int) {
       // TODO("Not yet implemented")
     holder.bind(listOfScannedResult[position],position)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return listOfScannedResult.size
    }

}