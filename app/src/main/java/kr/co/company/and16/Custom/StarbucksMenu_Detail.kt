package kr.co.company.and16.Custom

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import kr.co.company.and16.DBManager
import kr.co.company.and16.R

// < #. 커스텀 메뉴 상세 페이지: 스타벅스 >

class StarbucksMenu_Detail : AppCompatActivity() {

    // 액션바
    lateinit var actionBar : ActionBar

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var custom_image : ImageView // 이미지
    lateinit var custom_prise : TextView
    lateinit var custom_basemenu : TextView
    lateinit var custom_name : TextView
    lateinit var custom_size : TextView
    lateinit var custom_shotnum : TextView
    lateinit var custom_vanillasyrup: TextView
    lateinit var custom_hazelnutsyrup : TextView
    lateinit var custom_caramelsyrup : TextView
    lateinit var custom_lattebase : TextView
    lateinit var custom_base : TextView
    lateinit var custom_ice : TextView
    lateinit var custom_roastnum : TextView
    lateinit var custom_whipping : TextView
    lateinit var custom_drizzle : TextView

    lateinit var login_button : Button


    // db에서 조회 정보 받아서 textview에 전달하기 전 중간 역할 변수
    lateinit var str_customMenuName : String
    lateinit var str_image : String
    lateinit var str_existingMenuName : String
    lateinit var str_price : String
    lateinit var str_size : String
    lateinit var str_espressoShotNumber : String
    lateinit var str_hazelnutsSyrupNumber : String
    lateinit var str_caramelSyrupNumber : String
    lateinit var str_vanillaSyrupNumber : String
    lateinit var str_lattebase : String
    lateinit var str_base :String
    lateinit var str_ice : String
    lateinit var str_whipping : String
    lateinit var str_drizzle : String
    lateinit var str_roastNumber : String

    lateinit var bytesImage : ByteArray


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_menu_detail)

        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="커스텀메뉴 명칭"


        custom_image = findViewById(R.id.custom_image)
        custom_prise = findViewById(R.id.custom_prise)
        custom_basemenu = findViewById(R.id.custom_basemenu)
        custom_name = findViewById(R.id.custom_name)
        custom_size = findViewById(R.id.custom_size)
        custom_shotnum = findViewById(R.id.custom_shotnum)
        custom_vanillasyrup = findViewById(R.id.custom_vanillasyrup_shotnum)
        custom_hazelnutsyrup = findViewById(R.id.custom_hazelnutsyrup_shotnum)
        custom_caramelsyrup= findViewById(R.id.custom_caramelsyrup_shotnum)
        custom_lattebase = findViewById(R.id.custom_lattebase)
        custom_base = findViewById(R.id.custom_base)
        custom_ice = findViewById(R.id.custom_ice)
        custom_roastnum = findViewById(R.id.custom_roastnum)
        custom_whipping = findViewById(R.id.custom_whipping)
        custom_drizzle = findViewById(R.id.custom_drizzle)
        login_button = findViewById(R.id.login_button)

        // 스타벅스 리스트 목록으로 화면 전환
        login_button.setOnClickListener{
            val intent = Intent(this, StarbucksCustomList::class.java)
            startActivity(intent)
        }
        // StarbucksMenu_write에서 전달한 intent 받기
        val intent = intent
        str_customMenuName = intent.getStringExtra("intent_starbucks_name").toString()

        // db 객체 받기
        dbManager = DBManager(this, "starbucksMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        // 결과 얻기
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM starbucksMenuDB WHERE customMenuName = '"+ str_customMenuName +"';", null)


        if(cursor.moveToNext()) {

            // 컬럼값으로 인덱스값 알아오기
            bytesImage = cursor.getBlob(cursor.getColumnIndex("customImage"))
            str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            str_size = cursor.getString(cursor.getColumnIndex("size")).toString()

            str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()
            str_hazelnutsSyrupNumber = cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()
            str_caramelSyrupNumber = cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()
            str_vanillaSyrupNumber = cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()

            str_lattebase = cursor.getString(cursor.getColumnIndex("lattebase")).toString()
            str_base = cursor.getString(cursor.getColumnIndex("base")).toString()
            str_ice = cursor.getString(cursor.getColumnIndex("ice")).toString()
            str_whipping = cursor.getString(cursor.getColumnIndex("whipping")).toString()
            str_drizzle = cursor.getString(cursor.getColumnIndex("drizzle")).toString()
            str_roastNumber = cursor.getString(cursor.getColumnIndex("roastNumber")).toString()

        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()

        // textView에 값 넣기
        custom_name.text = str_customMenuName
        custom_basemenu.text = str_existingMenuName
        custom_prise.text = str_price
        custom_size.text = str_size
        custom_shotnum.text = str_espressoShotNumber

        custom_hazelnutsyrup.text = str_hazelnutsSyrupNumber
        custom_caramelsyrup.text = str_caramelSyrupNumber
        custom_vanillasyrup.text = str_vanillaSyrupNumber

        custom_lattebase.text = str_lattebase
        custom_base.text =str_base
        custom_ice.text = str_ice
        custom_whipping.text = str_whipping
        custom_drizzle.text = str_drizzle
        custom_roastnum.text = str_roastNumber + "\n" // 줄바꿈


        var bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.size)
        custom_image.setImageBitmap(bitmapImage)
    }
}