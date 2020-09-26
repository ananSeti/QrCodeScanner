package com.toobeeinfo.qrcodescanner.ui.db

import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult

interface DBHelperI {
    fun  insertQrResult(result: String):Int
    fun getQRResult(id:Int):QrResult
    fun addToFavourite(id:Int): Int
    fun removeFromFavourite(id: Int): Int
    // display for list
    fun getAllQRScannedResult(): List<QrResult>
    fun getAllFavouriteQRScannedResult(): List<QrResult>
    fun deleteAllQRScannedResult()
    fun deleteAllFavouriteQRScannedResult()
    fun deleteQrResult(id: Int): Int


}