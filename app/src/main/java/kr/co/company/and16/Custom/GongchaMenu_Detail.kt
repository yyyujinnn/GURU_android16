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
import kr.co.company.and16.DBManager
import kr.co.company.and16.R

// < #. 커스텀 메뉴 상세 페이지: 공차 >

class GongchaMenu_Detail : AppCompatActivity() {


    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var custom_name : TextView
    lateinit var custom_image : ImageView // 이미지
    lateinit var custom_basemenu : TextView
    lateinit var custom_prise : TextView
    lateinit var custom_size : TextView
    lateinit var custom_shotnum : TextView
    lateinit var custom_sugar : TextView
    lateinit var custom_ice : TextView
    lateinit var custom_toppings : TextView

    lateinit var login_button : Button

    // db에서 조회 정보 받아서 textview에 전달하기 전 중간 역할 변수
    lateinit var str_customMenuName : String
    lateinit var str_image : String // 이미지
    lateinit var str_existingMenuName : String
    lateinit var str_price : String
    lateinit var str_size : String
    lateinit var str_espressoShotNumber : String
    lateinit var str_sugar : String
    lateinit var str_ice : String
    lateinit var str_topping : String


    lateinit var bytesImage : ByteArray

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gongcha_menu_detail)


        custom_name = findViewById(R.id.custom_name)
        custom_basemenu = findViewById(R.id.custom_basemenu)
        custom_prise = findViewById(R.id.custom_prise)
        custom_size = findViewById(R.id.custom_size)
        custom_shotnum = findViewById(R.id.custom_shotnum)
        custom_image = findViewById(R.id.first_item_imageView) //이미지
        custom_sugar = findViewById(R.id.custom_sugar)
        custom_ice = findViewById(R.id.custom_ice)
        custom_toppings = findViewById(R.id.custom_toppings)


        login_button = findViewById(R.id.login_button)


        // 리스트 목록으로 화면 전환
        login_button.setOnClickListener{
            val intent = Intent(this, GongchaCustomList::class.java)
            startActivity(intent)
        }

        // MainActivity에서 전달한 intent 받기
        val intent = intent
        str_customMenuName = intent.getStringExtra("intent_gongcha_name").toString()

        // db 객체 받기
        dbManager = DBManager(this, "gongchaMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        // 결과 얻기
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM gongchaMenuDB WHERE customMenuName = '"+ str_customMenuName +"';", null)


        if(cursor.moveToNext()) {

            // 컬럼값으로 인덱스값 알아오기
            bytesImage = cursor.getBlob(cursor.getColumnIndex("customImage"))
            str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()
            str_sugar = cursor.getString(cursor.getColumnIndex("sugar")).toString()
            str_ice = cursor.getString(cursor.getColumnIndex("ice")).toString()
            str_topping = cursor.getString(cursor.getColumnIndex("topping")).toString()

        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()

        // textView에 값 넣기
        custom_name.text = str_customMenuName

        var bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.size)
        custom_image.setImageBitmap(bitmapImage)

        custom_basemenu.text = str_existingMenuName
        custom_prise.text = str_price
        custom_size.text = str_size
        custom_shotnum.text = str_espressoShotNumber
        custom_sugar.text = str_sugar
        custom_ice.text = str_ice
        custom_toppings.text = str_topping + "\n" // 줄바꿈



    }
}