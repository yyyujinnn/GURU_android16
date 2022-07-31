package kr.co.company.and16.Custom

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kr.co.company.and16.DBManager
import kr.co.company.and16.R

class GongchaCustomList : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gongcha_custom_list)


        dbManager = DBManager(this, "gongchaMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase


        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM gongchaMenuDB", null) // gongchaMenuDB 모든 데이터

        var num: Int = 0
        while(cursor.moveToNext()) {

            // 현재 커서에 있는 값 변수로 받기
            var str_customMenuName = cursor.getString(cursor.getColumnIndex("customMenuName")).toString()
            var str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            var str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            var str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            var str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()
            var str_sugar = cursor.getString(cursor.getColumnIndex("sugar")).toString()
            var str_ice = cursor.getString(cursor.getColumnIndex("ice")).toString()
            var str_topping = cursor.getString(cursor.getColumnIndex("topping")).toString()




            //textView 가 들어있는 layout
            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num    // 다른 레이아웃과 구분하기 위함(id: 0, 1, 2, ....)



        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()


    }
}