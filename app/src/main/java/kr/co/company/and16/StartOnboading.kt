package kr.co.company.and16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class StartOnboading : AppCompatActivity() {

    // xml 변수 선언
    lateinit var onboarding_bg_imageView:ImageView   // 온보딩 배경 물결 이미지
    lateinit var onboarding_logo_imageView: ImageView   // 로고 이미지
    lateinit var appExplain_textView: TextView   // 어플 설명 텍스트
    lateinit var start_button: Button   // 앱 시작 버튼

    lateinit var StartButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_onboading)

        // * 변수 다시 연결해주세요 *
        //StartButton = findViewById(R.id.StartButton)

        StartButton.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}