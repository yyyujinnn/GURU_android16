package kr.co.company.and16.Zzim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import kr.co.company.and16.Custom.CustomList
import kr.co.company.and16.Home.HomeActivity
import kr.co.company.and16.R

// < #. 찜 목록 리스트 페이지 >

class MyList : AppCompatActivity() {

    //옵션 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_my_list,menu)
        return true
    }

    // 옵션 메뉴 선택 시 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            //홈 클릭 시 -> 홈 화면으로 전환
            R.id.action_home -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                return true
            }
            //커스텀 메뉴 클릭 시 -> 커스텀 메뉴 화면으로 전환
            R.id.action_customList -> {
                val intent = Intent(this, CustomList::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // xml 변수 선언
    //lateinit var customlist_card: CardView                       // 카드뷰: 커스텀 메뉴 리스트
    lateinit var customlist_item_imageView: ImageView            // 카드뷰 제품 이미지
    lateinit var customlist_item_name_textView: TextView         // 카드뷰 제품 이름
    lateinit var customlist_item_brand_textView: TextView        // 카드뷰 제품 브랜드명
    lateinit var customlist_item_prise_textView: TextView        //카드뷰 제품 가격
    lateinit var customlist_item_mn_textView: TextView           // 카드뷰 제품 좋아요 숫자
    lateinit var customlist_item_like_imageButton: ImageButton   // 카드뷰 제품 찜 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)
    }
}