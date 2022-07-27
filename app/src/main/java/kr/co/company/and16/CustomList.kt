package kr.co.company.and16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

// < #. 커스텀 메뉴 리스트 페이지:공개 리스트 >

class CustomList : AppCompatActivity() {
    // xml 변수 선언
    lateinit var customlist_tab1_Button:Button    // 상단 탭 버튼1: 공개된 커스텀
    lateinit var customlist_tab2_Button: Button   // 상단 탭 버튼2: 나만의 커스텀

    lateinit var all_tab_imageButton: ImageButton         // 전체 탭 버튼
    lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
    lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼
    lateinit var gongcha_tab_imageButton: ImageButton     // 공차 탭 버튼

    lateinit var divide_line_imageView: ImageView   // 구분선 이미지

    lateinit var customlist_card: CardView                       // 카드뷰: 커스텀 메뉴 리스트
    lateinit var customlist_item_imageView: ImageView            // 카드뷰 제품 이미지
    lateinit var customlist_item_name_textView: TextView         // 카드뷰 제품 이름
    lateinit var customlist_item_brand_textView: TextView        // 카드뷰 제품 브랜드명
    lateinit var customlist_item_prise_textView: TextView        //카드뷰 제품 가격
    lateinit var customlist_item_mn_textView: TextView           // 카드뷰 제품 좋아요 숫자
    lateinit var customlist_item_like_imageButton: ImageButton   // 카드뷰 제품 찜 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list)

        all_tab_imageButton = findViewById(R.id.all_tab_imageButton)
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)
        gongcha_tab_imageButton = findViewById(R.id.gongcha_tab_imageButton)

        customlist_tab1_Button = findViewById(R.id.customlist_tab1_Button)
        customlist_tab2_Button = findViewById(R.id.customlist_tab2_Button)

        // 공개된 커스텀 / 나만의 커스텀 창 분리
        customlist_tab1_Button.setOnClickListener{
            val intent = Intent(this, CustomList::class.java)
            startActivity(intent)
        }
        customlist_tab2_Button.setOnClickListener{
            val intent = Intent(this, CustomList_MyCustom::class.java)
            startActivity(intent)
        }

        // 각 브랜드 로고 버튼을 누르면 각 브랜드의 인기 랭크를 보여줌
        starbucks_tab_imageButton.setOnClickListener{
            val intent = Intent(this, StarbucksRank::class.java)
            startActivity(intent)
        }
        ediya_tab_imageButton.setOnClickListener{
            val intent = Intent(this, EdiyaRank::class.java)
            startActivity(intent)
        }
        gongcha_tab_imageButton.setOnClickListener{
            val intent = Intent(this, GongchaRank::class.java)
            startActivity(intent)
        }
    }
}