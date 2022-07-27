package kr.co.company.and16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

// < #. 커스텀 메뉴 작성 페이지1:브랜드 선택 >

class Write_SelectBrand : AppCompatActivity() {

    // xml 변수 선언
    lateinit var explain_textView: TextView           // 설명 텍스트

    lateinit var starbucks_radioButton: RadioButton   // 스타벅스 라디오 버튼
    lateinit var ediya_radioButton: RadioButton       // 이디야 라디오 버튼
    lateinit var gongcha_radioButton: RadioButton     // 공차 라디오 버튼

    lateinit var write_next_button: Button            // 커스텀 메뉴 작성하기 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_select_brand)
    }
}