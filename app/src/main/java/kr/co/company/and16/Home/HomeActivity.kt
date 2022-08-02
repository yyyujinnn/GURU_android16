package kr.co.company.and16.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kr.co.company.and16.Custom.CustomList
import kr.co.company.and16.MenuReg.EdiyaMenu_write
import kr.co.company.and16.MenuReg.GongchaMenu_write
import kr.co.company.and16.MenuReg.StarbucksMenu_write
import kr.co.company.and16.MenuReg.Write_SelectBrand
import kr.co.company.and16.R
import kr.co.company.and16.Zzim.MyList

class HomeActivity : AppCompatActivity() {

    // 액션바
    lateinit var actionBar : ActionBar

    //옵션 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home,menu)
        return true
    }

    // 옵션 메뉴 선택 시 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            //커스텀 메뉴 클릭 시 -> 커스텀 메뉴 화면으로 전환
            R.id.action_customList -> {
                val intent = Intent(this, CustomList::class.java)
                startActivity(intent)
                return true
            }
            // 나의 찜 클릭 시 -> 나의 찜 화면으로 전환
            R.id.action_myList -> {
                val intent = Intent(this, MyList::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="전체 커스텀메뉴 랭킹"

        // xml 변수 선언
        lateinit var starbucks_tab_imageButton: ImageButton   // 스타벅스 탭 버튼
        lateinit var ediya_tab_imageButton: ImageButton       // 이디야 탭 버튼
        lateinit var gongcha_tab_imageButton: ImageButton     // 공차 탭 버튼

        lateinit var RegButton: FloatingActionButton // 메뉴등록 플로팅버튼



        // xml 변수 연결
        starbucks_tab_imageButton = findViewById(R.id.starbucks_tab_imageButton)   // 스타벅스 탭 버튼
        ediya_tab_imageButton = findViewById(R.id.ediya_tab_imageButton)           // 이디야 탭 버튼
        gongcha_tab_imageButton = findViewById(R.id.gongcha_tab_imageButton)       // 공차 탭 버튼


        RegButton = findViewById(R.id.RegButton) // 메뉴등록 플로팅 버튼

        starbucks_tab_imageButton.setOnClickListener {
            val intent = Intent(this, StarbucksRank::class.java)
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

        RegButton.setOnClickListener {
            val intent = Intent(this,Write_SelectBrand::class.java)
            startActivity(intent)
        }
    }
}