package kr.co.company.and16.Zzim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import kr.co.company.and16.R

// < #. 찜 목록 리스트 페이지 >

class MyList : AppCompatActivity() {

    // xml 변수 선언
    lateinit var customlist_card: CardView                       // 카드뷰: 커스텀 메뉴 리스트
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