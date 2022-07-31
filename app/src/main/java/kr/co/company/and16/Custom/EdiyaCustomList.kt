package kr.co.company.and16

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ediya_custom_list.*
import kr.co.company.and16.RecyclerView_ediya.MyModel
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerAdapter
import kr.co.company.and16.RecyclerView_ediya.MyRecyclerViewInterface

class EdiyaCustomList : AppCompatActivity(), MyRecyclerViewInterface {

    val TAG: String = "로그"
    var modelList = ArrayList<MyModel>()
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase
    

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ediya_custom_list)

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

            //textView 가 들어있는 layout
            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num    // 다른 레이아웃과 구분하기 위함(id: 0, 1, 2, ....)

            // 리사이클러뷰 관련 내용
            var myModel = MyModel(name = str_customMenuName, price = str_price, existmenu = str_existingMenuName, msize = str_size, mtapiocapearl = str_tapiocaPearl, mtoppingsauce = str_toppingSauce, mtopping = str_topping,
                mespressoshotnumber = str_espressoShotNumber, mhazelnutssyrupnumber = str_hazelnutsSyrupNumber, mcaramelsyrupnumber = str_caramelSyrupNumber, mvanillasyrupnumber = str_vanillaSyrupNumber,
                mirisvhsyrupnumber = str_irishSyrupNumber, mcafesyrunnumber = str_cafeSyrupNumber)
            this.modelList.add(myModel)
            myRecyclerAdapter = MyRecyclerAdapter(this)
            myRecyclerAdapter.submitLiist(this.modelList)
            my_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@EdiyaCustomList, LinearLayoutManager.VERTICAL, false)
                adapter = myRecyclerAdapter
            }


            // textView 속성 설정 - 커스텀 메뉴 명칭
            //  var tvCustomMenuName : TextView = TextView(this)
            //  tvCustomMenuName.text = str_customMenuName
            //  tvCustomMenuName.textSize = 30f
//            list_item_name_textView.setText(str_customMenuName)
            //tvCustomMenuName.textStyle="bold"
            //   layout_item.addView(tvCustomMenuName)// 레이아웃에 추가
            //list_item_name_textView.setText(str_customMenuName)

            // textView 속성 설정 - 베이스(기존) 메뉴 명칭
            // var tvExistingMenuName : TextView = TextView(this)
            //tvExistingMenuName.text = str_existingMenuName
            //tvExistingMenuName.textSize = 30f
            // layout_item.addView(tvExistingMenuName)// 레이아웃에 추가

            // textView 속성 설정 - 가격
            //var tvPrice : TextView = TextView(this)
            //tvPrice.text = str_price
            //tvPrice.textSize = 30f
            //layout_item.addView(tvPrice)// 레이아웃에 추가
            //       list_item_prise_textView.setText(str_price)



            // 레이아웃 클릭 시
//            layout_item.setOnClickListener {
            // 상세 정보로 이동
//                val intent = Intent(this,EdiyaCustomMenuShowActivity::class.java)
//                intent.putExtra("intent_name", str_customMenuName)
//                startActivity(intent)
//            }


            // while문에서 생성한 내용들 layout에 넣기
//            layout.addView(layout_item)
//            num++
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

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

