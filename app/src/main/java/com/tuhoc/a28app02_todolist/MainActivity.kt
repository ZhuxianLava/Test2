package com.tuhoc.a28app02_todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList = fileHelper.readData(this)
        var arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,
        android.R.id.text1,
        itemList)
        lvCongViec.adapter = arrayAdapter

        btnAdd.setOnClickListener {
            var itemName = edtInput.text.toString()
            itemList.add(itemName)
            edtInput.setText("")
            fileHelper.wirteData(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }
        
        //xoá item công việc sau khi hoàn thành
        lvCongViec.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Bạn đã hoàn thành công việc, nhấn yes để xoá ! ")
            alert.setCancelable(true)
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                //ghi danh sách mới vào tệp trên máy khách hàng
                fileHelper.wirteData(itemList,applicationContext)
            })
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            alert.create()
            alert.show()
        }
    }
}