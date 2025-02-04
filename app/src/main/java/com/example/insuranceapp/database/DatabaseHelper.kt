package com.example.insuranceapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(po: SQLiteDatabase?) {
        po?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT," +
                " Email TEXT, PhoneNumber TEXT, PolicyNumber TEXT, description TEXT )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS Userdata")
    }
    //insert
    fun insertdata(
        username: String,
        password: String,
        Email: String,
        PhoneNumber: String,
        PolicyNumber: String
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        values.put("Email", Email)
        values.put("PhoneNumber", PhoneNumber)
        values.put("PolicyNumber", PolicyNumber)
        val result = p0.insert("Userdata", null, values)
        if (result == 1.toLong()) {
            return false
        }
        return true
    }

    fun insertdata2(
        CarModel : String,
        Year: String,
        LicencePlate: String,
        DateInsured: String,
    ): Boolean {
        val p0 = this.writableDatabase
        val values = ContentValues()
        values.put("username", CarModel)
        values.put("Year", Year)
        values.put("LicencePlate", LicencePlate)
        values.put("DateInsured", DateInsured)
        val result = p0.insert("Userdata", null, values)
        if (result == 1.toLong()) {
            return false
        }
        return true
    }

    fun insertData3(db: SQLiteDatabase?, username: String, desc: String) {
        db?.execSQL("UPDATE Userdata SET description = '$desc' WHERE username = '$username'")
    }

    fun checkuserpass(username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val query = "select * from Userdata where username = '$username' and password='$password'"
        val cursor = p0.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun getText(): Cursor? {
        val db = this.readableDatabase
        val query = "SELECT username, Email, PolicyNumber, PhoneNumber FROM Userdata"
        return db.rawQuery(query, null)
    }
}
