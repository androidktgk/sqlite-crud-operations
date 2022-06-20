package com.govind8061.simplesimple

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create database
        val helper=MyHelper(this)
        val database=helper.writableDatabase


        //update and delete
        val values=ContentValues()
        values.put("Price",221)
        database.update("Products",values,"_id = ?", arrayOf("1"))
        database.delete("Products","_id = ?", arrayOf("2"))


        //read data
        val cursor=database.rawQuery("SELECT name,price FROM Products WHERE name = ?", arrayOf("Jam"))
        if (cursor!=null){
            cursor.moveToFirst()
        }
        val builderString=StringBuilder()

        do {
            val name=cursor.getString(0)
            val price= cursor.getDouble(1)
            builderString.append("NAME: "+name+"\nPRICE :"+price)
        }while (cursor.moveToNext())


        //show data
        val tvShowData=findViewById<TextView>(R.id.tvShowData)
        tvShowData.text=builderString
    }

}