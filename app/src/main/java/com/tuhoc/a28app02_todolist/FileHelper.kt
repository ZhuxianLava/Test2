package com.tuhoc.a28app02_todolist

import android.content.Context
import java.io.*

class FileHelper {
    val FILENAME = "listinfo.dat"
    //ghi tệp
    fun wirteData(item: ArrayList<String>, context: Context){
        val fos: FileOutputStream = context.openFileOutput(FILENAME,Context.MODE_PRIVATE)
        val oas = ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }

    //đọc tệp
    fun readData(context: Context): ArrayList<String>{
        val itemList: ArrayList<String> = try {
            val fis: FileInputStream = context.openFileInput(FILENAME)
            val ois  = ObjectInputStream(fis)
            ois.readObject() as ArrayList<String>
        }catch (e: FileNotFoundException){
            ArrayList()
        }

        return itemList
    }
}