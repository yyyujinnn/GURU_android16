package kr.co.company.and16.MenuReg

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kr.co.company.and16.Custom.GongchaMenu_Detail
import kr.co.company.and16.DBManager
import kr.co.company.and16.R
import kr.co.company.and16.Start.Login
import java.io.ByteArrayOutputStream

// < #. 커스텀 메뉴 작성 페이지2: 공차 커스텀 메뉴 >

class GongchaMenu_write : AppCompatActivity() {

    lateinit var actionBar : ActionBar


    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    // 변수 선언
    lateinit var custom_public_radioButton : RadioButton //레시피 공개
    lateinit var custom_private_radioButton : RadioButton //레시피 비공개

    lateinit var custom_name_EditText : EditText

    lateinit var custom_basemenu_spinner : Spinner

    lateinit var custom_prise_EditText : EditText

    lateinit var sizeRadioGroup : RadioGroup
    lateinit var custom_size1_radioButton : RadioButton
    lateinit var custom_size2_radioButton : RadioButton

    lateinit var custom_shotnum_plus_button : Button
    lateinit var custom_shotnum_sub_button : Button
    lateinit var custom_shotnum_textView : TextView


    lateinit var custom_suger_seekBar : SeekBar

    lateinit var custom_ice_seekBar : SeekBar

    lateinit var custom_topping1_radioButton : CheckBox
    lateinit var custom_topping2_radioButton : CheckBox
    lateinit var custom_topping3_radioButton : CheckBox
    lateinit var custom_topping4_radioButton : CheckBox
    lateinit var custom_topping5_radioButton : CheckBox
    lateinit var custom_topping6_radioButton : CheckBox

    lateinit var login_button : Button

    lateinit var selectedImageUri: Uri

    // 사진 작업을 위한 값
    private val GET_GALLERY_IMAGE = 200
    private var ImageRegButton: ImageView? = null
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000



    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //val selectedImageUri: Uri = data.getData()!!
            selectedImageUri = data.getData()!!
            ImageRegButton!!.setImageURI(selectedImageUri)
        }
    }


    // 0으로 시럽 개수 초기화 for 증감버튼
    var espressoShotNumber = 0      // 에스프레소 샷 개수를 위한 변수

    var str_topping: String = ""      // 체크박스에서 반환된 값(토핑 )


    var listener = CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
        if(isChecked) {
            when(compoundButton.id) {
                // 토핑 체크
                R.id.custom_topping1_radioButton -> {
                    str_topping += " 타피오카 펄 "
                }
                R.id.custom_topping2_radioButton -> {
                    str_topping += "  화이트 펄  "
                }
                R.id.custom_topping3_radioButton -> {
                    str_topping += "  알로에  "
                }
                R.id.custom_topping4_radioButton -> {
                    str_topping += "  코코넛  "
                }
                R.id.custom_topping5_radioButton -> {
                    str_topping += "  밀크폼  "
                }
                R.id.custom_topping6_radioButton -> {
                    str_topping += " 치즈폼 "
                }


            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gongcha_menu_write)


        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="커스텀 메뉴 등록하기"


        custom_public_radioButton = findViewById(R.id.custom_public_radioButton)
        custom_private_radioButton = findViewById(R.id.custom_private_radioButton)
        custom_name_EditText = findViewById(R.id.custom_name_EditText)
        custom_basemenu_spinner = findViewById(R.id.custom_basemenu_spinner)
        custom_prise_EditText = findViewById(R.id.custom_prise_EditText)
        sizeRadioGroup = findViewById(R.id.sizeRadioGroup)
        custom_size1_radioButton = findViewById(R.id.custom_size1_radioButton)
        custom_size2_radioButton = findViewById(R.id.custom_size2_radioButton)
        custom_shotnum_plus_button = findViewById(R.id.custom_shotnum_plus_button)
        custom_shotnum_sub_button = findViewById(R.id.custom_shotnum_sub_button)
        custom_shotnum_textView = findViewById(R.id.custom_shotnum_textView)
        custom_suger_seekBar = findViewById(R.id.custom_suger_seekBar)
        custom_ice_seekBar = findViewById(R.id.custom_ice_seekBar)
        custom_topping1_radioButton = findViewById(R.id.custom_topping1_radioButton)
        custom_topping2_radioButton = findViewById(R.id.custom_topping2_radioButton)
        custom_topping3_radioButton = findViewById(R.id.custom_topping3_radioButton)
        custom_topping4_radioButton = findViewById(R.id.custom_topping4_radioButton)
        custom_topping5_radioButton = findViewById(R.id.custom_topping5_radioButton)
        custom_topping6_radioButton = findViewById(R.id.custom_topping6_radioButton)
        ImageRegButton = findViewById(R.id.ImageRegButton)

        login_button = findViewById(R.id.login_button)


        // 이미지 추가 버튼 클릭 이벤트
        ImageRegButton!!.setOnClickListener {
            //권한이 부여되었는지 확인
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //권한이 허용되지 않음
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    //이전에 거부한 적이 있으면 설명(경고)
                    var dlg = AlertDialog.Builder(this)
                    dlg.setTitle("권한이 필요한 이유")
                    dlg.setMessage("사진을 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다. ")
                    dlg.setPositiveButton("확인") { dialog, which ->
                        ActivityCompat.requestPermissions(
                            this@GongchaMenu_write,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERNAL_STORAGE
                        )
                    }
                    dlg.setNegativeButton("취소", null)
                    dlg.show()
                } else {
                    //처음 권한 요청
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
            } else {
                //권한이 이미 제대로 허용됨

                val intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                startActivityForResult(intent, GET_GALLERY_IMAGE)
            }
        }



        var str_existingMenuName: String = ""   //custom_basemenu_spinner 에서 반환된 값(기존메뉴 이름)


        // 스피너를 위한 리스트 배열로 만들기
        var gongchaExistingMenu =
            resources.getStringArray(R.array.gongcha_menu)
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gongchaExistingMenu)
        custom_basemenu_spinner.adapter = adapter

        // 스피너 동작을 감지하는 리스너 연결
        custom_basemenu_spinner.setSelection(0) // 시작 위치 지정
        custom_basemenu_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    // 선택된 메뉴
                    str_existingMenuName = custom_basemenu_spinner.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        // 감소(-) 버튼 클릭
        // 에스프레소 샷 갯수 감소 버튼
        custom_shotnum_sub_button.setOnClickListener {
            espressoShotNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(espressoShotNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                espressoShotNumber = 0
            }
            custom_shotnum_textView.text = espressoShotNumber.toString()
        }
        // 증가(+) 버튼 클릭
        // 에스프레소 샷 갯수 증가 버튼
        custom_shotnum_plus_button.setOnClickListener {
            espressoShotNumber++
            custom_shotnum_textView.text = espressoShotNumber.toString()
        }



        // 체크박스 클릭
        custom_topping1_radioButton.setOnCheckedChangeListener(listener)
        custom_topping2_radioButton.setOnCheckedChangeListener(listener)
        custom_topping3_radioButton.setOnCheckedChangeListener(listener)
        custom_topping4_radioButton.setOnCheckedChangeListener(listener)
        custom_topping5_radioButton.setOnCheckedChangeListener(listener)
        custom_topping6_radioButton.setOnCheckedChangeListener(listener)


        var str_ice: String = ""   // 시크바 - 얼음
        var str_sugar: String = ""   //시크바 - 농도


        //seekbar 변화 이벤트 - 농도
        custom_suger_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                //seekBar 값 변경되면 실행됨
                if(progress == 0)
                    str_sugar = "0%"
                else if(progress == 1)
                    str_sugar = "30%"
                else if(progress == 2)
                    str_sugar = "50%"
                else if(progress == 3)
                    str_sugar= "70%"
                else if(progress == 4)
                    str_sugar = "100%"


                // 진짜 상태 : "${progress}"

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        //seekbar 변화 이벤트 - 얼음
        custom_ice_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                //seekBar 값 변경되면 실행됨
                if(progress == 0)
                    str_ice = "없음"
                else if(progress == 1)
                    str_ice = "적게"
                else if(progress == 2)
                    str_ice= "보통"
                else if(progress == 3)
                    str_ice = "많이"

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }


        })


        dbManager = DBManager(this, "gongchaMenuDB", null, 1)

        // 커스텀메뉴 작성 완료 버튼 클릭
        login_button.setOnClickListener {


            var str_size: String = ""             // sizeRadioGroup(라디오)에서 반환된 값(사이즈 : 라지 or 점보)
            var str_espressoShotNumber: String = ""
            str_espressoShotNumber = espressoShotNumber.toString()

            // 사이즈 선택 라디오 버튼
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size1_radioButton)
                str_size = custom_size1_radioButton.text.toString() // 하디 사이즈
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size2_radioButton)
                str_size = custom_size2_radioButton.text.toString() // 점보 사이즈

            // uri를 bitmap으로
            var stream = contentResolver.openInputStream(selectedImageUri)
            var bitmap = BitmapFactory.decodeStream(stream)
            //bitmap을 byteArray로
            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
            var bytesImage = byteArrayOutputStream.toByteArray()



            var contentValues = ContentValues()
            // put(항목, 값)
            contentValues.put("customMenuName", custom_name_EditText.text.toString())
            contentValues.put("customImage", bytesImage)
            contentValues.put("existingMenuName", str_existingMenuName)
            contentValues.put("price", custom_prise_EditText.text.toString())
            contentValues.put("size", str_size)
            contentValues.put("espressoShotNumber", str_espressoShotNumber)
            contentValues.put("sugar", str_sugar)
            contentValues.put("ice", str_ice)
            contentValues.put("topping", str_topping)

            sqlitedb = dbManager.writableDatabase
            sqlitedb.insert("gongchaMenuDB", null, contentValues)

            sqlitedb.close()

            // 공차 세부 정보 화면으로 전환하기
            val intent = Intent(this, GongchaMenu_Detail::class.java)
            intent.putExtra("intent_gongcha_name", custom_name_EditText.text.toString())
            startActivity(intent)


        }

    }

}