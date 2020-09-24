package com.toobeeinfo.qrcodescanner.ui.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.database.QrResultDataBase
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setviewPagerAdapter()
        setBottomNavigation()
        setViewPagerListerner()
        //TODO  add database
        val qrResult = QrResult(result = "Dummy Text",resultType = "TEXT",favourite = false,
        time = Calendar.getInstance() )
        QrResultDataBase.getAppDatabase(this)?.getQrDao()?.insertQrResult(qrResult)
    }

    private fun setViewPagerListerner() {
       // TODO 3.("Not yet implemented")
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
              //  TODO("Not yet implemented")
            }

            override fun onPageSelected(position: Int) {
              //  TODO("Not yet implemented")
               /* when(position){
                    0->bottomNavigationView.selectedItemId = R.id.scanMenuId
                    1->bottomNavigationView.selectedItemId = R.id.recentScannedMenuId
                    2->bottomNavigationView.selectedItemId = R.id.favouritesMenuId
                    else -> bottomNavigationView.selectedItemId = R.id.scanMenuId
                }*/
               bottomNavigationView.selectedItemId = when(position){
                    0-> R.id.scanMenuId
                    1-> R.id.recentScannedMenuId
                    2-> R.id.favouritesMenuId
                    else ->  R.id.scanMenuId
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
               // TODO("Not yet implemented")
            }
        })
    }

    private fun setBottomNavigation() {
        //TODO 2.("Not yet implemented")
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.scanMenuId -> {
                    viewPager.currentItem = 0
                }
                R.id.recentScannedMenuId -> {
                    viewPager.currentItem = 1

                }
                R.id.favouritesMenuId -> {
                    viewPager.currentItem = 2
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setviewPagerAdapter() {
       // TODO 1.("Not yet implemented")
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }
}