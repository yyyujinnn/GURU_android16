package kr.co.company.and16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Login : AppCompatActivity() {

    // xml 변수 선언
    lateinit var login_bg_imageView: ImageView        // 로그인 배경 물결 이미지
    lateinit var app_logo_imageView: ImageView        // 로고 이미지
    lateinit var ID_editText: EditText                // 아이디 입력창
    lateinit var id_icon_imageView: ImageView         // 아이디 아이콘 이미지
    lateinit var password_editText: EditText          // 비밀번호 입력창
    lateinit var password_icon_imageView: ImageView   // 비밀번호 아이콘 이미지
    lateinit var forget_password_textView: TextView   // 아이디/비번찾기 텍스트
    lateinit var signUp_textView: TextView            // 회원가입 설명 텍스트
    lateinit var google_login_button: Button          // 구글 로그인 버튼
    lateinit var LoginButton: Button //로그인 버튼
    lateinit var SignupButton: Button //회원가입 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginButton = findViewById(R.id.LoginButton)
        SignupButton = findViewById(R.id.SignupButton)

        // 로그인버튼 클릭 시 main 화면으로 이동
        LoginButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //회원가입 버튼 클릭 시 회원가입 화면으로 이동
        SignupButton.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}