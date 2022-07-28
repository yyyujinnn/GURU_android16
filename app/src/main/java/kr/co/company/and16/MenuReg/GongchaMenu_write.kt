package kr.co.company.and16.MenuReg

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kr.co.company.and16.R
import kr.co.company.and16.Start.Login

// < #. 커스텀 메뉴 작성 페이지2: 공차 커스텀 메뉴 >

class GongchaMenu_write : AppCompatActivity() {

    // 변수 선언
    lateinit var custom_public_radioButton : RadioButton //레시피 공개
    lateinit var custom_private_radioButton : RadioButton //레시피 비공개

    lateinit var custom_name_EditText : EditText

    lateinit var custom_basemenu_spinner : Spinner

    lateinit var custom_prise_EditText : EditText

    lateinit var custom_size1_radioButton : RadioButton
    lateinit var custom_size2_radioButton : RadioButton

    lateinit var custom_shotnum_plus_button : Button
    lateinit var custom_shotnum_sub_button : Button
    lateinit var custom_shotnum_textView : TextView
    lateinit var output_text : TextView

    lateinit var custom_suger_seekBar : SeekBar

    lateinit var custom_ice_seekBar : SeekBar

    lateinit var custom_topping1_radioButton : CheckBox
    lateinit var custom_topping2_radioButton : CheckBox
    lateinit var custom_topping3_radioButton : CheckBox
    lateinit var custom_topping4_radioButton : CheckBox
    lateinit var custom_topping5_radioButton : CheckBox
    lateinit var custom_topping6_radioButton : CheckBox

    private val GET_GALLERY_IMAGE = 200
    private var ImageRegButton: ImageView? = null
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gongcha_menu_write)

        custom_basemenu_spinner = findViewById(R.id.custom_basemenu_spinner)

        custom_shotnum_plus_button = findViewById(R.id.custom_shotnum_plus_button)
        custom_shotnum_sub_button = findViewById(R.id.custom_shotnum_sub_button)
        custom_shotnum_textView = findViewById(R.id.custom_shotnum_textView)
        output_text = findViewById<TextView>(R.id.custom_shotnum_textView)
        var custom_shotnum_textView = 0 // 샷 개수는 0에서 가감

        ImageRegButton = findViewById(R.id.ImageRegButton)

        ImageRegButton!!.setOnClickListener{
            //권한이 부여되었는지 확인
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //권한이 허용되지 않음
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    //이전에 거부한 적이 있으면 설명(경고)
                    var dlg = AlertDialog.Builder(this)
                    dlg.setTitle("권한이 필요한 이유")
                    dlg.setMessage("사진을 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다. ")
                    dlg.setPositiveButton("확인") {dialog, which -> ActivityCompat.requestPermissions(this@GongchaMenu_write,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_READ_EXTERNAL_STORAGE)}
                    dlg.setNegativeButton("취소", null)
                    dlg.show()
                } else {
                    //처음 권한 요청
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                }
            } else {
                //권한이 이미 제대로 허용됨

                val intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                startActivityForResult(intent, GET_GALLERY_IMAGE)
            }
        }


        // 베이스 메뉴 선택 - 스피너
        ArrayAdapter.createFromResource(
            this,
            R.array.gongcha_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            custom_basemenu_spinner.adapter = adapter
        }

        // 샷 개수 감소 버튼
        custom_shotnum_sub_button.setOnClickListener {
            custom_shotnum_textView--
            output_text.setText(custom_shotnum_textView.toString())
        }
        // 샷 개수 증가 버튼
        custom_shotnum_plus_button.setOnClickListener {
            custom_shotnum_textView++
            output_text.setText(custom_shotnum_textView.toString())
        }
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            val selectedImageUri: Uri = data.getData()!!
            ImageRegButton!!.setImageURI(selectedImageUri)
        }
    }

}