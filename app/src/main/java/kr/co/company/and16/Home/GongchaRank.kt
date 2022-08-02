package kr.co.company.and16.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import kr.co.company.and16.R

class GongchaRank : AppCompatActivity() {

    lateinit var actionBar : ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="공차 커스텀메뉴 랭킹"

        // xml 변수 선언
        lateinit var all_tab_imageButton: ImageButton         // 전체 탭 버튼
        lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
        lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gongcha_rank)

        // xml 변수 연결
        all_tab_imageButton = findViewById(R.id.all_tab_imageButton)               // 전체 탭 버튼
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)   // 스타벅스 탭 버튼
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)           // 이디야 탭 버튼


        all_tab_imageButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        starbucks_tab_imageButton.setOnClickListener {
            val intent = Intent(this, StarbucksRank::class.java)
            startActivity(intent)
        }

        ediya_tab_imageButton.setOnClickListener {
            val intent = Intent(this, EdiyaRank::class.java)
            startActivity(intent)
        }
    }
}