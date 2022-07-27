package kr.co.company.and16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

// < #. 커스텀 메뉴 작성 페이지2: 스타벅스 커스텀 메뉴 >

class StarbucksMenu_write : AppCompatActivity() {

    // 변수 선언
    lateinit var custom_public_radioButton : RadioButton //레시피 공개
    lateinit var custom_private_radioButton : RadioButton //레시피 비공개

    lateinit var custom_name_EditText : EditText

    lateinit var spinner : Spinner

    lateinit var custom_prise_EditText : EditText

    lateinit var custom_size1_radioButton : RadioButton
    lateinit var custom_size2_radioButton : RadioButton
    lateinit var custom_size3_radioButton : RadioButton

    lateinit var custom_shotnum_plus_button : Button
    lateinit var custom_shotnum_sub_button : Button
    lateinit var custom_shotnum_textView : TextView
    lateinit var shot_output_text : TextView

    lateinit var custom_vanillasyrupnum_plus_button : Button
    lateinit var custom_vanillasyrupnum_sub_button : Button
    lateinit var custom_vanillasyrupnum_textView : TextView
    lateinit var vanilla_output_text : TextView

    lateinit var custom_hazelnutsyrupnum_plus_button : Button
    lateinit var custom_hazelnutsyrupnum_sub_button : Button
    lateinit var custom_hazelnutsyrupnum_textView : TextView
    lateinit var hazelnut_output_text : TextView

    lateinit var custom_caramelsyrupnum_plus_button : Button
    lateinit var custom_caramelsyrupnum_sub_button : Button
    lateinit var custom_caramelsyrupnum_textView : TextView
    lateinit var caramel_output_text : TextView

    lateinit var custom_lattebase1_radioButton : RadioButton
    lateinit var custom_lattebase2_radioButton : RadioButton
    lateinit var custom_lattebase3_radioButton : RadioButton
    lateinit var custom_lattebase4_radioButton : RadioButton
    lateinit var custom_lattebase5_radioButton : RadioButton

    lateinit var custom_base_seekBar : SeekBar

    lateinit var custom_ice_seekBar : SeekBar

    lateinit var custom_whipping1_radioButton : RadioButton
    lateinit var custom_whipping2_radioButton : RadioButton
    lateinit var custom_whipping3_radioButton : RadioButton
    lateinit var custom_whipping_seekBar : SeekBar

    lateinit var title_roastnum_textView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_menu_write)

        //val spinner: Spinner = findViewById(R.id.spinner)

        custom_shotnum_plus_button = findViewById(R.id.custom_shotnum_plus_button)
        custom_shotnum_sub_button = findViewById(R.id.custom_shotnum_sub_button)
        custom_shotnum_textView = findViewById(R.id.custom_shotnum_textView)
        shot_output_text = findViewById<TextView>(R.id.custom_shotnum_textView)
        var custom_shotnum_textView = 0 // 수량은 0에서 가감

        custom_vanillasyrupnum_plus_button = findViewById(R.id.custom_vanillasyrupnum_plus_button)
        custom_vanillasyrupnum_sub_button = findViewById(R.id.custom_vanillasyrupnum_sub_button)
        custom_vanillasyrupnum_textView = findViewById(R.id.custom_vanillasyrupnum_textView)
        vanilla_output_text = findViewById<TextView>(R.id.custom_vanillasyrupnum_textView)
        var custom_vanillasyrupnum_textView = 0 // 횟수는 0에서 가감

        custom_hazelnutsyrupnum_plus_button = findViewById(R.id.custom_hazelnutsyrupnum_plus_button)
        custom_hazelnutsyrupnum_sub_button = findViewById(R.id.custom_hazelnutsyrupnum_sub_button)
        custom_hazelnutsyrupnum_textView = findViewById(R.id.custom_hazelnutsyrupnum_textView)
        hazelnut_output_text = findViewById<TextView>(R.id.custom_hazelnutsyrupnum_textView)
        var custom_hazelnutsyrupnum_textView = 0 // 횟수는 0에서 가감

        custom_caramelsyrupnum_plus_button = findViewById(R.id.custom_caramelsyrupnum_plus_button)
        custom_caramelsyrupnum_sub_button = findViewById(R.id.custom_caramelsyrupnum_sub_button)
        custom_caramelsyrupnum_textView = findViewById(R.id.custom_caramelsyrupnum_textView)
        caramel_output_text = findViewById<TextView>(R.id.custom_caramelsyrupnum_textView)
        var custom_caramelsyrupnum_textView = 0 // 횟수는 0에서 가감

        ArrayAdapter.createFromResource(
            this,
            R.array.starbucks_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // 샷 개수 감소 버튼
        custom_shotnum_sub_button.setOnClickListener {
            custom_shotnum_textView--
            shot_output_text.setText(custom_shotnum_textView.toString())
        }
        // 샷 개수 증가 버튼
        custom_shotnum_plus_button.setOnClickListener {
            custom_shotnum_textView++
            shot_output_text.setText(custom_shotnum_textView.toString())
        }

        // 바닐라시럽 횟수 감소 버튼
        custom_vanillasyrupnum_sub_button.setOnClickListener {
            custom_vanillasyrupnum_textView--
            vanilla_output_text.setText(custom_vanillasyrupnum_textView.toString())
        }
        // 바닐라시럽 횟수 증가 버튼
        custom_vanillasyrupnum_sub_button.setOnClickListener {
            custom_vanillasyrupnum_textView++
            vanilla_output_text.setText(custom_vanillasyrupnum_textView.toString())
        }

        // 헤이즐넛시럽 횟수 감소 버튼
        custom_hazelnutsyrupnum_sub_button.setOnClickListener {
            custom_hazelnutsyrupnum_textView--
            hazelnut_output_text.setText(custom_hazelnutsyrupnum_textView.toString())
        }
        // 헤이즐넛시럽 횟수 증가 버튼
        custom_hazelnutsyrupnum_plus_button.setOnClickListener {
            custom_hazelnutsyrupnum_textView++
            hazelnut_output_text.setText(custom_hazelnutsyrupnum_textView.toString())
        }

        // 카라멜시럽 횟수 감소 버튼
        custom_caramelsyrupnum_sub_button.setOnClickListener {
            custom_caramelsyrupnum_textView--
            caramel_output_text.setText(custom_caramelsyrupnum_textView.toString())
        }
        // 카라멜시럽 횟수 증가 버튼
        custom_caramelsyrupnum_plus_button.setOnClickListener {
            custom_caramelsyrupnum_textView++
            caramel_output_text.setText(custom_caramelsyrupnum_textView.toString())
        }
    }
}