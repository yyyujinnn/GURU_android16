package kr.co.company.and16

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MenuReg : AppCompatActivity() {

    // 변수 선언
    //lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    //lateinit var spinner: spinner
    lateinit var btnRegist: Button
    lateinit var countdown : Button
    lateinit var countup : Button
    lateinit var count : TextView
    lateinit var output_text : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_reg)

        countdown = findViewById(R.id.countdown)
        countup = findViewById(R.id.countup)
        output_text = findViewById<TextView>(R.id.count)
        var count = 0; // 수량은 0에서 시작

        // 수량 감소 버튼
        countdown.setOnClickListener{
            count--
            output_text.setText(count.toString())
        }
        // 수량 증가 버튼
        countup.setOnClickListener{
            count++
            output_text.setText(count.toString())
        }

    }

}
