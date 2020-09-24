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

    private fun findResultType(result: String): String {
       return "TEXT"
    }
}