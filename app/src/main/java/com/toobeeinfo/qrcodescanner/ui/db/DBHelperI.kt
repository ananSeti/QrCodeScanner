package com.toobeeinfo.qrcodescanner.ui.db

import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult

interface DBHelperI {
    fun  insertQrResult(result: String):Int
    fun getQRResult(id:Int):QrResult
}