package kr.co.company.and16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

// < #. 커스텀 메뉴 작성 페이지2: 이디야 커스텀 메뉴 >

class EdiyaMenu_write : AppCompatActivity() {

    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ediya_menu_write)

        spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.ediya_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }
}