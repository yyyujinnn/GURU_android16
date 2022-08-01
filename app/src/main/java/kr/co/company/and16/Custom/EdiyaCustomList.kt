package kr.co.company.and16

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ediya_custom_list.*
import kr.co.company.and16.Custom.CustomList
import kr.co.company.and16.Custom.GongchaCustomList
import kr.co.company.and16.Custom.StarbucksCustomList
import kr.co.company.and16.RecyclerView_ediya.MyModel
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerAdapter
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerViewInterface

class EdiyaCustomList : AppCompatActivity(), MyRecyclerViewInterface {

    // 액션바
    lateinit var actionBar : ActionBar

    val TAG: String = "로그"
    var modelList = ArrayList<MyModel>()
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var all_tab_imageButton: ImageButton         // 전체 탭 버튼
    lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
    lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼
    lateinit var gongcha_tab_imageButton: ImageButton     // 공차 탭 버튼



    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ediya_custom_list)

        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="이디야 커스텀메뉴"

        all_tab_imageButton = findViewById(R.id.all_tab_imageButton)
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)
        gongcha_tab_imageButton = findViewById(R.id.gongcha_tab_imageButton)

        dbManager = DBManager(this, "ediyaMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase


        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM ediyaMenuDB", null) // ediyaMenuDB의 모든 데이터

        var num: Int = 0
        while(cursor.moveToNext()) {

            // 현재 커서에 있는 값 변수로 받기
            var str_customMenuName = cursor.getString(cursor.getColumnIndex("customMenuName")).toString()
            var str_existingMenuName = cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            var str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            var str_tapiocaPearl = cursor.getString(cursor.getColumnIndex("tapiocaPearl")).toString()
            var str_toppingSauce = cursor.getString(cursor.getColumnIndex("toppingSauce")).toString()
            var str_topping = cursor.getString(cursor.getColumnIndex("topping")).toString()
            var str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            var str_espressoShotNumber = cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()
            var str_hazelnutsSyrupNumber = cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()
            var str_caramelSyrupNumber = cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()
            var str_vanillaSyrupNumber = cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()
            var str_irishSyrupNumber = cursor.getString(cursor.getColumnIndex("irishSyrupNumber")).toString()
            var str_cafeSyrupNumber = cursor.getString(cursor.getColumnIndex("cafeSyrupNumber")).toString()

            // db에 저장된 이미지
            var bytesImage = cursor.getBlob(cursor.getColumnIndex("customImage"))


            // 리사이클러뷰 관련 내용
            var myModel = MyModel(name = str_customMenuName, price = str_price, existmenu = str_existingMenuName, profileImage = bytesImage, msize = str_size, mtapiocapearl = str_tapiocaPearl, mtoppingsauce = str_toppingSauce, mtopping = str_topping,
                mespressoshotnumber = str_espressoShotNumber, mhazelnutssyrupnumber = str_hazelnutsSyrupNumber, mcaramelsyrupnumber = str_caramelSyrupNumber, mvanillasyrupnumber = str_vanillaSyrupNumber,
                mirisvhsyrupnumber = str_irishSyrupNumber, mcafesyrunnumber = str_cafeSyrupNumber)
            this.modelList.add(myModel)
            myRecyclerAdapter = MyRecyclerAdapter(this)
            myRecyclerAdapter.submitLiist(this.modelList)
            my_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@EdiyaCustomList, LinearLayoutManager.VERTICAL, false)
                adapter = myRecyclerAdapter
            }


        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        // 각 브랜드 로고 버튼을 누르면 각 브랜드의 커스텀 리스트를 보여줌
        starbucks_tab_imageButton.setOnClickListener{
            val intent = Intent(this, StarbucksCustomList::class.java)
            startActivity(intent)
        }
        all_tab_imageButton.setOnClickListener{
            val intent = Intent(this, CustomList::class.java)
            startActivity(intent)
        }
        gongcha_tab_imageButton.setOnClickListener{
            val intent = Intent(this, GongchaCustomList::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClicked(position: Int) {
        var name: String? = null
        var price: String? = null
        var msize: String? = null
        var mtapiocapearl: String? = null
        var mtoppingsauce: String? = null
        var mtopping: String? = null
        var mespressoshotnumber: String? = null
        var mhazelnutssyrupnumber: String? = null
        var mcaramelsyrupnumber: String? = null
        var mvanillasyrupnumber : String? = null
        var mirisvhsyrupnumber: String? = null
        var mcafesyrunnumber: String? = null

        val title: String = this.modelList[position].name ?: ""
        val mprice: String = this.modelList[position].price ?: ""
        val mSize: String = this.modelList[position].msize ?: ""
        val mtapiocalPearl: String = this.modelList[position].mtapiocapearl ?: ""
        val mtoppingSauce: String = this.modelList[position].mtoppingsauce ?: ""
        val mTopping: String = this.modelList[position].mtopping ?: ""
        val mespressoshotNumber: String = this.modelList[position].mespressoshotnumber ?: ""
        val mhazelnutssyrupNumber: String = this.modelList[position].mhazelnutssyrupnumber ?: ""
        val mcaramelsyrupNumber: String = this.modelList[position].mcaramelsyrupnumber ?: ""
        val mvanillasyrupNumber: String = this.modelList[position].mvanillasyrupnumber ?: ""
        val mirishsyrupNumber: String = this.modelList[position].mirisvhsyrupnumber ?: ""
        val mcafesyrupNumber: String = this.modelList[position].mcafesyrunnumber ?: ""


        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("가격 : $mprice 원\n" +
                    "사이즈 : $mSize\n" +
                    "에스프레소 샷 갯수 : $mespressoshotNumber \n" +
                    "펄 토핑 : $mtapiocalPearl \n" +
                    "헤이즐넛 시럽 : $mhazelnutssyrupNumber \n" +
                    "카라멜 시럽 : $mcaramelsyrupNumber \n" +
                    "바닐라 시럽 : $mvanillasyrupNumber \n" +
                    "아이리쉬 시럽 : $mirishsyrupNumber \n" +
                    "카페 시럽 : $mcafesyrupNumber \n" +
                    "토핑 소스 : $mtoppingSauce \n" +
                    "토핑 : $mtopping")
            .setPositiveButton("확인"){ dialog, id ->
                Log.d(TAG, "다이얼로그 확인 버튼 클릭")
            }
            .show()
    }


}

