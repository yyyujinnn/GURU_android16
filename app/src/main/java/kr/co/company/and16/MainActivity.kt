package kr.co.company.and16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // xml 변수 선언
    lateinit var ranking_first_card:CardView                // 카드뷰1: 랭킹1
    lateinit var first_item_imageView: ImageView            // 랭킹1위 제품 이미지
    lateinit var first_item_explain_textView: TextView      // 카드뷰2 설명 텍스트
    lateinit var first_item_name_textView: TextView         // 카드뷰2 제품 이름
    lateinit var first_item_brand_textView: TextView        // 카드뷰2 제품 브랜드명
    lateinit var first_item_prise_textView: TextView        //카드뷰2 제품 가격
    lateinit var first_item_mn_textView: TextView           // 카드뷰2 제품 좋아요 숫자
    lateinit var first_item_like_imageButton: ImageButton   // 카드뷰2 찜 버튼

    lateinit var ranking_list_card:CardView                // 카드뷰2: 랭킹리스트
    lateinit var list_item_imageView: ImageView            // 카드뷰 2 제품 이미지
    lateinit var list_item_name_textView: TextView         // 카드뷰2 제품 이름
    lateinit var list_item_brand_textView: TextView        // 카드뷰2 제품 브랜드명
    lateinit var list_item_prise_textView: TextView        //카드뷰2 제품 가격
    lateinit var list_item_mn_textView: TextView           // 카드뷰2 제품 좋아요 숫자
    lateinit var list_item_like_imageButton: ImageButton   // 카드뷰2 찜 버튼




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}