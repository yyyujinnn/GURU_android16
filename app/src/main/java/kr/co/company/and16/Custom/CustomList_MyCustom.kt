package kr.co.company.and16.Custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import kr.co.company.and16.R

// < #. 커스텀 메뉴 리스트 페이지:비공개 리스트 >

class CustomList_MyCustom : AppCompatActivity() {

    // 액션바
    lateinit var actionBar : ActionBar

    // xml 변수 선언
    lateinit var customlist_tab1_Button: Button    // 상단 탭 버튼1: 공개된 커스텀
    lateinit var customlist_tab2_Button: Button   // 상단 탭 버튼2: 나만의 커스텀

    lateinit var divide_line_imageView: ImageView   // 구분선 이미지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_mycustom)

        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="나만의 커스텀메뉴"

        // 공개된 커스텀 / 나만의 커스텀 창 분리
        customlist_tab1_Button.setOnClickListener{
            val intent = Intent(this, CustomList::class.java)
            startActivity(intent)
        }
        customlist_tab2_Button.setOnClickListener{
            val intent = Intent(this, CustomList_MyCustom::class.java)
            startActivity(intent)
        }
    }
}