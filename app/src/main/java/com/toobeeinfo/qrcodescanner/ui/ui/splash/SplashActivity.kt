package com.toobeeinfo.qrcodescanner.ui.ui.splash

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.ui.mainactivity.MainActivity

class SplashActivity : AppCompatActivity() {
      companion object{
          private const val  CAMERA_PERMISSION_REQUEST_CODE=123
      }
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            checkForPermission()
        },2000)

    }

    private fun checkForPermission() {
        //TODO 1.("Not yet implemented")
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) ==PackageManager.PERMISSION_GRANTED){
            goToMainActivity()
        }else{
            requestThePermission()
        }
    }

    private fun requestThePermission() {
        //TODO 3("Not yet implemented")
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
          CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                goToMainActivity()
            }else if(isUserPermanetlyDenied()){
                showGoToAppSettingDialog()
            } else
                requestThePermission()
        }
    }

    private fun showGoToAppSettingDialog() {
        //TODO 5.("Not yet implemented")
        AlertDialog.Builder(this)
            .setTitle(" Grant Permission !!")
            .setMessage("we need camera permission to scan QR codes. Go to App Setting and manage permission")
            .setPositiveButton("Grant"){dialog,which->
                gotoAppSetting()
            }
            .setNegativeButton("Cancel"){dialog,which ->
                Toast.makeText(this,"we need permission for Start this Application",Toast.LENGTH_SHORT).show()
                finish()
            }.show()
    }

    private fun gotoAppSetting() {
        //TODO 6("Not yet implemented")
        var intent =Intent(
            ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package",packageName,null))
         intent.addCategory(Intent.CATEGORY_DEFAULT)
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         startActivity(intent)
    }

    private fun isUserPermanetlyDenied(): Boolean {
          return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
              shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA).not()
          } else {
              //TODO 4("VERSION.SDK_INT < M")
              return false
          }
    }

    private fun goToMainActivity() {
        //TODO 2.("Not yet implemented")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        checkForPermission()
    }
}