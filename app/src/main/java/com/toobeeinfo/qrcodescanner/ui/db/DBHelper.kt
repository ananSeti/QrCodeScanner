package com.toobeeinfo.qrcodescanner.ui.db

import com.toobeeinfo.qrcodescanner.ui.db.database.QrResultDataBase
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import java.util.*

class DBHelper(var qrResultDataBase: QrResultDataBase) :DBHelperI {

    override fun insertQrResult(result: String): Int {
        //TODO 1 ("Not yet implemented")
        val time = Calendar.getInstance()
        val resultType = findResultType(result)
        val qrResult = QrResult(result = result,resultType = resultType,time =  time,favourite = false )
        return qrResultDataBase.getQrDao().insertQrResult(qrResult).toInt()
    }

    override fun getQRResult(id: Int): QrResult {
       // TODO 2("Not yet implemented")
        return qrResultDataBase.getQrDao().getQrResult(id)
    }

    override fun addToFavourite(id: Int): Int {
       // TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().addToFavourite(id)
    }

    override fun removeFromFavourite(id: Int): Int {
       // TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().removeFromFavourite(id)
    }

    override fun getAllQRScannedResult(): List<QrResult> {
        //TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().getAllScannedResult()
    }

    override fun getAllFavouriteQRScannedResult(): List<QrResult> {
       // TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().getAllFavouriteResult()
    }
    override fun deleteQrResult(id: Int): Int {
        return qrResultDataBase.getQrDao().deleteQrResult(id)
    }
    override fun deleteAllQRScannedResult() {
        //TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().deleteAllScannedResult()
    }

    override fun deleteAllFavouriteQRScannedResult() {
        //TODO("Not yet implemented")
        return qrResultDataBase.getQrDao().deleteAllFavouriteResult()
    }

    private fun findResultType(result: String): String {
       return "TEXT"
    }
}