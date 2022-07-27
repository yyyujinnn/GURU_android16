package kr.co.company.and16

import android.content.Intent
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

        explain_textView = findViewById(R.id.explain_textView)
        starbucks_radioButton = findViewById(R.id.starbucks_radioButton)
        ediya_radioButton = findViewById(R.id.ediya_radioButton)
        gongcha_radioButton = findViewById(R.id.gongcha_radioButton)

        write_next_button = findViewById(R.id.write_next_button)

        // 스타벅스 라디오버튼 선택 후 작성하기 버튼 클릭하면 스타벅스 커스텀 메뉴 작성 페이지로 이동
        starbucks_radioButton.setOnClickListener {
            write_next_button.setOnClickListener {
                val intent = Intent(this, StarbucksMenu_write::class.java)
                startActivity(intent)
            }
        }
        // 이디야 라디오버튼 선택 후 작성하기 버튼 클릭하면 이디야 커스텀 메뉴 작성 페이지로 이동
        ediya_radioButton.setOnClickListener {
            write_next_button.setOnClickListener {
                val intent = Intent(this, EdiyaMenu_write::class.java)
                startActivity(intent)
            }
        }

        // 공차 라디오버튼 선택 후 작성하기 버튼 클릭하면 공차 커스텀 메뉴 작성 페이지로 이동
        gongcha_radioButton.setOnClickListener {
            write_next_button.setOnClickListener {
                val intent = Intent(this, GongchaMenu_write::class.java)
                startActivity(intent)
            }
        }
    }
}