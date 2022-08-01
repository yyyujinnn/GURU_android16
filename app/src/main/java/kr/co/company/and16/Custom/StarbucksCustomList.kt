package kr.co.company.and16.Custom

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.company.and16.DBManager
import kr.co.company.and16.R

class StarbucksCustomList : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_custom_list)


        dbManager = DBManager(this, "starbucksMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM starbucksMenuDB", null) // starbucksMenuDB 모든 데이터

        var num: Int = 0
        while(cursor.moveToNext()) {

            // 현재 커서에 있는 값 변수로 받기
            var str_customMenuName = cursor.getString(cursor.getColumnIndex("customMenuName")).toString()
            var str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            var str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            var str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            var str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()


            var str_vanillaSyrupNumber = cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()
            var str_hazelnutsSyrupNumber = cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()
            var str_caramelSyrupNumber = cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()
            var str_lattebase = cursor.getString(cursor.getColumnIndex("lattebase")).toString()
            var str_base= cursor.getString(cursor.getColumnIndex("base")).toString()
            var str_ice = cursor.getString(cursor.getColumnIndex("ice")).toString()
            var str_whipping = cursor.getString(cursor.getColumnIndex("whipping")).toString()
            var str_drizzle= cursor.getString(cursor.getColumnIndex("drizzle")).toString()
            var str_roast = cursor.getString(cursor.getColumnIndex("roastNumber")).toString()


        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

    }
}