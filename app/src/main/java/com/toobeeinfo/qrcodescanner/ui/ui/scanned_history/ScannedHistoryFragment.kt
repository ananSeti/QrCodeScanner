package com.toobeeinfo.qrcodescanner.ui.ui.scanned_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.toobeeinfo.qrcodescanner.R
import com.toobeeinfo.qrcodescanner.ui.db.DBHelper
import com.toobeeinfo.qrcodescanner.ui.db.DBHelperI
import com.toobeeinfo.qrcodescanner.ui.db.database.QrResultDataBase
import com.toobeeinfo.qrcodescanner.ui.db.entities.QrResult
import com.toobeeinfo.qrcodescanner.ui.ui.adapter.ScannedResultListAdapter
import com.toobeeinfo.qrcodescanner.ui.utils.gone
import com.toobeeinfo.qrcodescanner.ui.utils.visible
import kotlinx.android.synthetic.main.fragment_scanned_history.view.*
import kotlinx.android.synthetic.main.layout_header_history.view.*


class ScannedHistoryFragment : Fragment() {
  enum class ResultListType {
      ALL_RESULT,FAVOURITE_RESULT
  }
  companion object {
      private const val ARGUMENT_RESULT_LIST_TYPE ="ArgumentResultType"
      fun newInstance(screenType:ResultListType) : ScannedHistoryFragment{
          val bundle = Bundle()
          bundle.putSerializable(ARGUMENT_RESULT_LIST_TYPE,screenType)
          val fragment = ScannedHistoryFragment()
          fragment.arguments = bundle
          return fragment
      }
  }
    private  var resultListType:ResultListType? = null
    private lateinit var mView: View
    private lateinit var dbHelperI: DBHelperI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleArguments()
    }

    private fun handleArguments() {
       // TODO("Not yet implemented")
    resultListType = arguments?.getSerializable(ARGUMENT_RESULT_LIST_TYPE) as ResultListType
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_scanned_history, container, false)
        init()
        showListOfResults()
        setSwipeRefreshLayout()
        onClicks()
        return mView
    }

    private fun onClicks() {
       // TODO("Not yet implemented")
        mView.removeAll.setOnClickListener{
            showRemoveAllScannedResultDialog()
        }
    }

    private fun showRemoveAllScannedResultDialog() {
       // TODO("Not yet implemented")
        AlertDialog.Builder(context!!, R.style.CustomAlertDialog).setTitle(getString(R.string.clear_all))
            .setMessage(getString(R.string.clear_all_result))
            .setPositiveButton(getString(R.string.clear)) { _, _ ->
                clearAllRecords()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }.show()
    }

    private fun clearAllRecords() {
       // TODO("Not yet implemented")
        when(resultListType){
            ResultListType.FAVOURITE_RESULT-> dbHelperI.deleteAllQRScannedResult()
            ResultListType.ALL_RESULT->dbHelperI.deleteAllFavouriteQRScannedResult()
        }
        mView.scannedHistoryRecyclerView.adapter?.notifyDataSetChanged()
        showAllResults()
    }

    private fun setSwipeRefreshLayout() {
      //  TODO("Not yet implemented")
      mView.swipeRefresh.setOnRefreshListener {
          mView.swipeRefresh.isRefreshing =false
          showListOfResults()
      }
    }

    private fun showListOfResults() {
      //  TODO("Not yet implemented")
        when(resultListType){
            ResultListType.ALL_RESULT ->{
                showAllResults()
            }
            ResultListType.FAVOURITE_RESULT->{
                showFavouriteResults()
            }
        }
    }

    private fun showFavouriteResults() {
       // TODO("Not yet implemented")
        var listOfResult = dbHelperI.getAllFavouriteQRScannedResult()
        showResults(listOfResult)
        mView.layoutHeader.tvHeaderText.text = "รพ.มะเร็งเลือกไว้แล้ว"
    }

    private fun showResults(listOfResult: List<QrResult>) {
       // TODO("Not yet implemented")
        if(listOfResult.isEmpty()){
            showEmptyState()
        }else{
            initRecycleView(listOfResult)
        }
    }

    private fun initRecycleView(listOfResult: List<QrResult>) {
       // TODO("Not yet implemented")
      mView.scannedHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
      mView.scannedHistoryRecyclerView.adapter =
          ScannedResultListAdapter(dbHelperI,context!!,listOfResult.toMutableList())
      showRecycleView()
    }

    private fun showEmptyState() {
        //TODO("Not yet implemented")
        mView.layoutHeader.removeAll.gone()
        mView.scannedHistoryRecyclerView.gone()
        mView.noResultFound.visible()
    }
   private fun showRecycleView(){
       mView.layoutHeader.removeAll.visible()
       mView.scannedHistoryRecyclerView.visible()
       mView.noResultFound.gone()
   }
    private fun showAllResults() {
       // TODO("Not yet implemented")
        val listOfAllResult = dbHelperI.getAllQRScannedResult()
        showResults(listOfAllResult)
        mView.layoutHeader.tvHeaderText.text = getString(R.string.recent_scanned)
    }

    private fun init() {
      //  TODO("Not yet implemented")
        dbHelperI =DBHelper(QrResultDataBase.getAppDatabase(context!!)!!)

    }


}