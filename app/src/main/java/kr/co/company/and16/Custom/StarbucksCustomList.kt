package kr.co.company.and16.Custom

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_custom_list.*
import kr.co.company.and16.DBManager
import kr.co.company.and16.EdiyaCustomList
import kr.co.company.and16.R
import kr.co.company.and16.RecyclerView_ediya.MyModel
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerAdapter
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerViewInterface

class StarbucksCustomList : AppCompatActivity(), MyRecyclerViewInterface {

    val TAG: String = "로그"
    var modelList = ArrayList<MyModel>()
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var all_tab_imageButton: ImageButton         // 전체 탭 버튼
    lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
    lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼
    lateinit var gongcha_tab_imageButton: ImageButton     // 공차 탭 버튼


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_custom_list)

        all_tab_imageButton = findViewById(R.id.all_tab_imageButton)
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)
        gongcha_tab_imageButton = findViewById(R.id.gongcha_tab_imageButton)

        dbManager = DBManager(this, "starbucksMenuDB", null, 1)
        sqlitedb = dbManager.readableDatabase


        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM starbucksMenuDB", null) // starbucksMenuDB 모든 데이터


        var num: Int = 0
        while (cursor.moveToNext()) {

            // 현재 커서에 있는 값 변수로 받기
            var str_customMenuName =
                cursor.getString(cursor.getColumnIndex("customMenuName")).toString()
            var str_existingMenuName =
                cursor.getString(cursor.getColumnIndex("existingMenuName")).toString()
            var str_price = cursor.getString(cursor.getColumnIndex("price")).toString()
            var str_size = cursor.getString(cursor.getColumnIndex("size")).toString()
            var str_espressoShotNumber =
                cursor.getString(cursor.getColumnIndex("espressoShotNumber")).toString()


            var str_vanillaSyrupNumber =
                cursor.getString(cursor.getColumnIndex("vanillaSyrupNumber")).toString()
            var str_hazelnutsSyrupNumber =
                cursor.getString(cursor.getColumnIndex("hazelnutsSyrupNumber")).toString()
            var str_caramelSyrupNumber =
                cursor.getString(cursor.getColumnIndex("caramelSyrupNumber")).toString()
            var str_lattebase = cursor.getString(cursor.getColumnIndex("lattebase")).toString()
            var str_base = cursor.getString(cursor.getColumnIndex("base")).toString()
            var str_ice = cursor.getString(cursor.getColumnIndex("ice")).toString()
            var str_whipping = cursor.getString(cursor.getColumnIndex("whipping")).toString()
            var str_drizzle = cursor.getString(cursor.getColumnIndex("drizzle")).toString()
            var str_roast = cursor.getString(cursor.getColumnIndex("roastNumber")).toString()

            // 리사이클러뷰 관련 내용
            var myModel = MyModel(
                name = str_customMenuName,
                price = str_price,
                existmenu = str_existingMenuName,
                msize = str_size,
                mlattebase = str_lattebase,
                mbase = str_base,
                mice = str_ice,
                mwhipping = str_whipping,
                mespressoshotnumber = str_espressoShotNumber,
                mhazelnutssyrupnumber = str_hazelnutsSyrupNumber,
                mcaramelsyrupnumber = str_caramelSyrupNumber,
                mvanillasyrupnumber = str_vanillaSyrupNumber,
                mdrizzle = str_drizzle,
                mroast = str_roast
            )
            this.modelList.add(myModel)
            myRecyclerAdapter = MyRecyclerAdapter(this)
            myRecyclerAdapter.submitLiist(this.modelList)
            my_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@StarbucksCustomList, LinearLayoutManager.VERTICAL, false)
                adapter = myRecyclerAdapter
            }

        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        // 각 브랜드 로고 버튼을 누르면 각 브랜드의 커스텀 리스트를 보여줌
        all_tab_imageButton.setOnClickListener{
            val intent = Intent(this, CustomList::class.java)
            startActivity(intent)
        }
        ediya_tab_imageButton.setOnClickListener{
            val intent = Intent(this, EdiyaCustomList::class.java)
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
        var mlattebase: String? = null
        var mbase: String? = null
        var mice: String? = null
        var mespressoshotnumber: String? = null
        var mhazelnutssyrupnumber: String? = null
        var mcaramelsyrupnumber: String? = null
        var mvanillasyrupnumber: String? = null
        var mwhipping: String? = null
        var mdrizzle: String? = null
        var mroast: String? = null

        val title: String = this.modelList[position].name ?: ""
        val mprice: String = this.modelList[position].price ?: ""
        val mSize: String = this.modelList[position].msize ?: ""
        val mlatteBase: String = this.modelList[position].mlattebase ?: ""
        val mBase: String = this.modelList[position].mbase ?: ""
        val mIce: String = this.modelList[position].mice ?: ""
        val mespressoshotNumber: String = this.modelList[position].mespressoshotnumber ?: ""
        val mhazelnutssyrupNumber: String = this.modelList[position].mhazelnutssyrupnumber ?: ""
        val mcaramelsyrupNumber: String = this.modelList[position].mcaramelsyrupnumber ?: ""
        val mvanillasyrupNumber: String = this.modelList[position].mvanillasyrupnumber ?: ""
        val mWhipping: String = this.modelList[position].mwhipping ?: ""
        val mDrizzle: String = this.modelList[position].mdrizzle ?: ""
        val mRoast: String = this.modelList[position].mroast ?: ""


        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(
                "가격 : $mprice 원\n" +
                        "사이즈 : $mSize\n" +
                        "에스프레소 샷 갯수 : $mespressoshotNumber \n" +
                        "라떼 베이스 : $mlatteBase \n" +
                        "물/티 베이스 : $mBase \n" +
                        "헤이즐넛 시럽 : $mhazelnutssyrupNumber \n" +
                        "카라멜 시럽 : $mcaramelsyrupNumber \n" +
                        "바닐라 시럽 : $mvanillasyrupNumber \n" +
                        "얼음 : $mIce \n" +
                        "휘핑크림 : $mWhipping \n" +
                        "드리즐 : $mDrizzle \n" +
                        "로스트 프라푸치노 : $mRoast"
            )
            .setPositiveButton("확인") { dialog, id ->
                Log.d(TAG, "다이얼로그 확인 버튼 클릭")
            }
            .show()
    }
}
