package kr.co.company.and16.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kr.co.company.and16.R

class StarbucksRank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // xml 변수 선언
        lateinit var all_tab_imageButton: ImageButton         // 전체 탭 버튼
        lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
        lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼
        lateinit var gongcha_tab_imageButton: ImageButton     // 공차 탭 버튼
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_rank)

        // xml 변수 연결
        all_tab_imageButton = findViewById(R.id.all_tab_imageButton)               // 전체 탭 버튼
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)   // 스타벅스 탭 버튼
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)           // 이디야 탭 버튼
        gongcha_tab_imageButton = findViewById(R.id.gongcha_tab_imageButton)       // 공차 탭 버튼

        all_tab_imageButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        ediya_tab_imageButton.setOnClickListener {
            val intent = Intent(this, EdiyaRank::class.java)
            startActivity(intent)
        }

        gongcha_tab_imageButton.setOnClickListener {
            val intent = Intent(this, GongchaRank::class.java)
            startActivity(intent)
        }
    }
}