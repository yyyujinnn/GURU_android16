package kr.co.company.and16.MenuReg

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kr.co.company.and16.Custom.StarbucksMenu_Detail
import kr.co.company.and16.DBManager
import kr.co.company.and16.R
import org.w3c.dom.Text
import java.io.ByteArrayOutputStream

// < #. 커스텀 메뉴 작성 페이지2: 스타벅스 커스텀 메뉴 >

class StarbucksMenu_write : AppCompatActivity() {

    lateinit var actionBar : ActionBar

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var sizeRadioGroup : RadioGroup
    lateinit var custom_size1_radioButton : RadioButton
    lateinit var custom_size2_radioButton : RadioButton
    lateinit var custom_size3_radioButton : RadioButton

    lateinit var custom_prise_EditText : EditText
    lateinit var custom_basemenu_spinner : Spinner
    lateinit var custom_name_EditText : EditText

    lateinit var custom_shotnum_sub_button : Button
    lateinit var custom_shotnum_plus_button : Button
    lateinit var custom_shotnum_textView : TextView

    lateinit var custom_vanillasyrupnum_sub_button : Button
    lateinit var custom_vanillasyrupnum_plus_button : Button
    lateinit var custom_vanillasyrupnum_textView : TextView

    lateinit var custom_hazelnutsyrupnum_sub_button : Button
    lateinit var custom_hazelnutsyrupnum_plus_button : Button
    lateinit var custom_hazelnutsyrupnum_textView : TextView

    lateinit var custom_caramelsyrupnum_sub_button : Button
    lateinit var custom_caramelsyrupnum_plus_button : Button
    lateinit var custom_caramelsyrupnum_textView : TextView

    lateinit var custom_lattebase_spinner : Spinner

    lateinit var custom_ice_seekBar : SeekBar
    lateinit var custom_base_seekBar : SeekBar
    lateinit var custom_whipping_seekBar : SeekBar
    lateinit var custom_drizzle_seekBar : SeekBar

    lateinit var custom_drizzle_radioGroup : RadioGroup
    lateinit var custom_drizzle1_radioButton : RadioButton
    lateinit var custom_drizzle2_radioButton : RadioButton
    lateinit var custom_drizzle3_radioButton : RadioButton

    lateinit var custom_whipping_radioGroup : RadioGroup
    lateinit var custom_whipping1_radioButton : RadioButton
    lateinit var custom_whipping2_radioButton : RadioButton
    lateinit var custom_whipping3_radioButton : RadioButton

    lateinit var custom_roastnum_sub_button : Button
    lateinit var custom_roastnum_plus_button : Button
    lateinit var custom_roastnum_textView : TextView

    lateinit var login_button : Button

    // 애뮬레이터에서 선택된 이미지 uri를 담을 변수
    lateinit var selectedImageUri: Uri

    // 사진 작업을 위한 값
    private val GET_GALLERY_IMAGE = 200
    private var ImageRegButton: ImageView? = null
    private val REQUEST_READ_EXTERNAL_STORAGE = 1000

    // 0으로 시럽 개수 초기화 for 증감버튼
    var espressoShotNumber = 0      // 에스프레소 샷 개수를 위한 변수

    var hazelnutsSyrupNumber = 0    // 헤이즐넛 시럽을 위한 변수
    var caramelSyrupNumber = 0      //  카라멜 시럽 개수를 위한 변수
    var vanillaSyrupNumber = 0      // 바닐라 시럽 개수를 위한 변수
    var roastNumber = 0        // 아이리쉬 시럽 개수를 위한 변수

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData()!!
            ImageRegButton!!.setImageURI(selectedImageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starbucks_menu_write)

        // 액션바 제목 변경
        actionBar = supportActionBar!!
        actionBar.title ="커스텀 메뉴 등록하기"

        sizeRadioGroup = findViewById(R.id.sizeRadioGroup)
        custom_size1_radioButton = findViewById(R.id.custom_size1_radioButton)
        custom_size2_radioButton = findViewById(R.id.custom_size2_radioButton)
        custom_size3_radioButton = findViewById(R.id.custom_size3_radioButton)

        custom_prise_EditText = findViewById(R.id.custom_prise_EditText)
        custom_basemenu_spinner = findViewById(R.id.custom_basemenu_spinner)
        custom_name_EditText = findViewById(R.id.custom_name_EditText)

        custom_shotnum_sub_button = findViewById(R.id.custom_shotnum_sub_button)
        custom_shotnum_plus_button = findViewById(R.id.custom_shotnum_plus_button)
        custom_shotnum_textView = findViewById(R.id.custom_shotnum_textView)

        custom_vanillasyrupnum_sub_button = findViewById(R.id.custom_vanillasyrupnum_sub_button)
        custom_vanillasyrupnum_plus_button = findViewById(R.id.custom_vanillasyrupnum_plus_button)
        custom_vanillasyrupnum_textView = findViewById(R.id.custom_vanillasyrupnum_textView)

        custom_hazelnutsyrupnum_sub_button = findViewById(R.id.custom_hazelnutsyrupnum_sub_button)
        custom_hazelnutsyrupnum_plus_button = findViewById(R.id.custom_hazelnutsyrupnum_plus_button)
        custom_hazelnutsyrupnum_textView = findViewById(R.id.custom_hazelnutsyrupnum_textView)

        custom_caramelsyrupnum_sub_button = findViewById(R.id.custom_caramelsyrupnum_sub_button)
        custom_caramelsyrupnum_plus_button = findViewById(R.id.custom_caramelsyrupnum_plus_button)
        custom_caramelsyrupnum_textView = findViewById(R.id.custom_caramelsyrupnum_textView)

        custom_lattebase_spinner = findViewById(R.id.custom_lattebase_spinner)

        custom_ice_seekBar = findViewById(R.id.custom_ice_seekBar)
        custom_base_seekBar = findViewById(R.id.custom_base_seekBar)

        custom_whipping_seekBar = findViewById(R.id.custom_whipping_seekBar)
        custom_drizzle_seekBar = findViewById(R.id.custom_drizzle_seekBar)

        custom_drizzle_radioGroup = findViewById(R.id.custom_drizzle_radioGroup)
        custom_drizzle1_radioButton = findViewById(R.id.custom_drizzle1_radioButton)
        custom_drizzle2_radioButton = findViewById(R.id.custom_drizzle2_radioButton)
        custom_drizzle3_radioButton = findViewById(R.id.custom_drizzle3_radioButton)

        custom_whipping_radioGroup = findViewById(R.id.custom_whipping_radioGroup)
        custom_whipping1_radioButton = findViewById(R.id.custom_whipping1_radioButton)
        custom_whipping2_radioButton = findViewById(R.id.custom_whipping2_radioButton)
        custom_whipping3_radioButton = findViewById(R.id.custom_whipping3_radioButton)

        custom_roastnum_sub_button = findViewById(R.id.custom_roastnum_sub_button)
        custom_roastnum_plus_button = findViewById(R.id.custom_roastnum_plus_button)
        custom_roastnum_textView = findViewById(R.id.custom_roastnum_textView)

        login_button = findViewById(R.id.login_button)

        ImageRegButton = findViewById(R.id.ImageRegButton)

        // 이미지 추가 버튼 클릭 이벤트
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
                    dlg.setPositiveButton("확인") {dialog, which -> ActivityCompat.requestPermissions(this@StarbucksMenu_write,
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

        var str_existingMenuName: String = ""   //custom_basemenu_spinner 에서 반환된 값 담을 변수(기존메뉴 이름)

        // 스피너를 위한 리스트 배열로 만들기(1) - 기본메뉴
        var starbucksExistingMenu =
            resources.getStringArray(R.array.starbucks_menu)
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, starbucksExistingMenu)
        custom_basemenu_spinner.adapter = adapter

        // 스피너 동작을 감지하는 리스너 연결
        custom_basemenu_spinner.setSelection(0) // 시작 위치 지정
        custom_basemenu_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    // 선택된 메뉴 저장하기
                    str_existingMenuName = custom_basemenu_spinner.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        var str_lattebase: String = ""   //custom_lattebase_spinner 에서 반환된 값 담을 변수(라떼베이스 이름)

        // 스피너를 위한 리스트 배열로 만들기(2) - 라떼 베이스
        var lattebaseMenu =
            resources.getStringArray(R.array.Lattebase)
        var adapterLattee =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lattebaseMenu)
        custom_lattebase_spinner.adapter = adapterLattee

        // 스피너 동작을 감지하는 리스너 연결
        custom_lattebase_spinner.setSelection(0) // 시작 위치 지정
        custom_lattebase_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    // 선택된 메뉴 저장하기
                    str_lattebase = custom_lattebase_spinner.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        var str_base: String = ""   // 시크바 - 베이스(물,티)
        var str_ice: String = ""   //시크바 - 얼음

        //seekbar 변화 이벤트 - 베이스(물,티)
        custom_base_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                //seekBar 값 변경되면 실행됨
                if(progress == 0)
                    str_base = "적게"
                else if(progress == 1)
                    str_base = "보통"
                else if(progress == 2)
                    str_base = "많이"
                // 진짜 상태 : "${progress}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
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

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
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

        //  헤이즐넛 시럽 갯수 감소 버튼
        custom_hazelnutsyrupnum_sub_button.setOnClickListener {
            hazelnutsSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(hazelnutsSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                hazelnutsSyrupNumber = 0
            }
            custom_hazelnutsyrupnum_textView.text = hazelnutsSyrupNumber.toString()
        }
        //  카라멜 시럽 갯수 감소 버튼
        custom_caramelsyrupnum_sub_button.setOnClickListener {
            caramelSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(caramelSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                caramelSyrupNumber = 0
            }
            custom_caramelsyrupnum_textView.text = caramelSyrupNumber.toString()
        }
        //  바닐라 시럽 갯수 감소 버튼
        custom_vanillasyrupnum_sub_button.setOnClickListener {
            vanillaSyrupNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(vanillaSyrupNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                vanillaSyrupNumber = 0
            }
            custom_vanillasyrupnum_textView.text = vanillaSyrupNumber.toString()
        }

        // 로스트 갯수 감소 버튼
        custom_roastnum_sub_button.setOnClickListener {
            roastNumber--
            // 음수이면 에러 메시지와 0으로 초기화
            if(roastNumber < 0) {
                Toast.makeText(this, "0 미만으로 설정하실 수 없습니다.", Toast.LENGTH_LONG).show()
                roastNumber = 0
            }
            custom_roastnum_textView.text = roastNumber.toString()
        }

        // 증가(+) 버튼 클릭
        // 에스프레소 샷 갯수 증가 버튼
        custom_shotnum_plus_button.setOnClickListener {
            espressoShotNumber++
            custom_shotnum_textView.text = espressoShotNumber.toString()
        }

        //  헤이즐넛 시럽 갯수 증가 버튼
        custom_hazelnutsyrupnum_plus_button.setOnClickListener {
            hazelnutsSyrupNumber++
            custom_hazelnutsyrupnum_textView.text = hazelnutsSyrupNumber.toString()
        }
        //  카라멜 시럽 갯수 증가 버튼
        custom_caramelsyrupnum_plus_button.setOnClickListener {
            caramelSyrupNumber++
            custom_caramelsyrupnum_textView.text = caramelSyrupNumber.toString()
        }
        //  바닐라 시럽 갯수 증가 버튼
        custom_vanillasyrupnum_plus_button.setOnClickListener {
            vanillaSyrupNumber++
            custom_vanillasyrupnum_textView.text = vanillaSyrupNumber.toString()
        }

        //  로스트 갯수 증가 버튼
        custom_roastnum_plus_button.setOnClickListener {
            roastNumber++
            custom_roastnum_textView.text = roastNumber.toString()
        }

        var str_whipping: String = ""   // 시크바 - 휘핑
        var str_drizzle: String = ""   //시크바 - 드리즐

        //seekbar 변화 이벤트 - 휘핑
        custom_whipping_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 라디오 버튼 선택
                when(custom_whipping_radioGroup.checkedRadioButtonId) {
                    // 휘핑X
                    R.id.custom_whipping1_radioButton ->{
                        custom_whipping_seekBar.progress = 0 // 없음으로 설정
                        str_whipping = custom_whipping1_radioButton.text.toString()
                    }

                    // 일반 휘핑
                    R.id.custom_whipping2_radioButton -> {
                        str_whipping = custom_whipping2_radioButton.text.toString()
                        if(progress == 0) {
                            custom_whipping_seekBar.progress++   //없음이 선택되지 못하도록
                        }
                        else if(progress == 1) {
                            str_whipping += ", 적게"
                        }else if(progress == 2) {
                            str_whipping += ", 보통"
                        }else if(progress == 3) {
                            str_whipping += ", 많이"
                        }
                    }
                    // 에스프레소 휘핑
                    R.id.custom_whipping3_radioButton ->{
                        str_whipping = custom_whipping3_radioButton.text.toString()
                        if(progress == 0) {
                            custom_whipping_seekBar.progress++   //없음이 선택되지 못하도록
                        }
                        else if(progress == 1) {
                            str_whipping += ", 적게"
                        }else if(progress == 2) {
                            str_whipping += ", 보통"
                        }else if(progress == 3) {
                            str_whipping += ", 많이"
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })
        //seekbar 변화 이벤트 - 드리즐
        custom_drizzle_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 라디오 버튼 선택
                when(custom_drizzle_radioGroup.checkedRadioButtonId) {
                    // 드리즐X
                    R.id.custom_drizzle1_radioButton ->{
                        custom_drizzle_seekBar.progress = 0 // 없음으로 설정(다른 progress가 선택되지 못하도록)
                        str_drizzle = custom_drizzle1_radioButton.text.toString()
                    }

                    // 카라멜 드리즐
                    R.id.custom_drizzle2_radioButton -> {
                        str_drizzle = custom_drizzle2_radioButton.text.toString()
                        if(progress == 0) {
                            custom_drizzle_seekBar.progress++   //없음이 선택되지 못하도록 progress 가 1부터 시작
                        }
                        else if(progress == 1) {
                            str_drizzle += ", 적게"
                        }else if(progress == 2) {
                            str_drizzle += ", 보통"
                        }else if(progress == 3) {
                            str_drizzle += ", 많이"
                        }
                    }
                    // 초콜릿 드리즐
                    R.id.custom_drizzle3_radioButton ->{
                        str_drizzle = custom_drizzle3_radioButton.text.toString()
                        if(progress == 0) {
                            custom_drizzle_seekBar.progress++   //없음이 선택되지 못하도록
                        }
                        else if(progress == 1) {
                            str_drizzle += ", 적게"
                        }else if(progress == 2) {
                            str_drizzle += ", 보통"
                        }else if(progress == 3) {
                            str_drizzle += ", 많이"
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })

        dbManager = DBManager(this, "starbucksMenuDB", null, 1)

        // 커스텀메뉴 작성 완료 버튼 클릭
        login_button.setOnClickListener {

            var str_size: String =
                ""             // sizeRadioGroup(라디오)에서 반환된 값 담을 변수 (사이즈 : 톨 or 그란데 or 벤티)

            var str_espressoShotNumber: String = ""

            var str_hazelnutsSyrupNumber: String = ""
            var str_caramelSyrupNumber: String = ""
            var str_vanillaSyrupNumber: String = ""

            var str_roastNumber: String = ""

            // 버튼 이벤트로 받은 int 값을 str 형태로 변경(for db 필드 유형 일치)
            str_espressoShotNumber = espressoShotNumber.toString()

            str_hazelnutsSyrupNumber = hazelnutsSyrupNumber.toString()
            str_caramelSyrupNumber = caramelSyrupNumber.toString()
            str_vanillaSyrupNumber = vanillaSyrupNumber.toString()

            str_roastNumber = roastNumber.toString()

            // 사이즈 선택 라디오 버튼
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size1_radioButton)
                str_size = custom_size1_radioButton.text.toString() // 톨 사이즈
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size2_radioButton)
                str_size = custom_size2_radioButton.text.toString() // 그란데 사이즈
            if (sizeRadioGroup.checkedRadioButtonId == R.id.custom_size3_radioButton)
                str_size = custom_size3_radioButton.text.toString() // 벤티 사이즈

            // 사용자로부터 받은 사진 uri를 db의 blob 로 변환
            // uri를 bitmap으로
            var stream = contentResolver.openInputStream(selectedImageUri)
            var bitmap = BitmapFactory.decodeStream(stream)
            //bitmap을 byteArray로
            var byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
            var bytesImage = byteArrayOutputStream.toByteArray()

            // db 필드에 입력받은 값 넣기
            var contentValues = ContentValues()
            // put(항목, 값)
            contentValues.put("customMenuName", custom_name_EditText.text.toString()) // 커스텀 메뉴 이름
            contentValues.put("customImage", bytesImage)    // 커스텀 메뉴 이미지
            contentValues.put("existingMenuName", str_existingMenuName) // 기존 메뉴
            contentValues.put("price", custom_prise_EditText.text.toString())   // 커스텀 메뉴 가격
            contentValues.put("size", str_size) // 사이즈
            contentValues.put("espressoShotNumber", str_espressoShotNumber) //에스프레소 샷 개수
            contentValues.put("hazelnutsSyrupNumber", str_hazelnutsSyrupNumber)    // 헤이즐넛 시럽
            contentValues.put("caramelSyrupNumber", str_caramelSyrupNumber)// 카라멜 시럽
            contentValues.put("vanillaSyrupNumber", str_vanillaSyrupNumber)// 바닐라 시럽
            contentValues.put("lattebase", str_lattebase)// 라뗴 베이스
            contentValues.put("base", str_base)//  베이스(물,티)
            contentValues.put("ice", str_ice) // 얼음
            contentValues.put("whipping", str_whipping) // 휘핑
            contentValues.put("drizzle", str_drizzle) // 드리즐
            contentValues.put("roastNumber", str_roastNumber) //로스트 개수

            // db에 데이터 넣기
            sqlitedb = dbManager.writableDatabase
            sqlitedb.insert("starbucksMenuDB", null, contentValues)

            sqlitedb.close()

            // 스타벅스 세부 정보 화면으로 전환하기
            val intent = Intent(this, StarbucksMenu_Detail::class.java)
            intent.putExtra("intent_starbucks_name", custom_name_EditText.text.toString())
            startActivity(intent)
        }
    }
}