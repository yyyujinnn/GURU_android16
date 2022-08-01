package kr.co.company.and16.Start

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.co.company.and16.Home.HomeActivity
import kr.co.company.and16.R

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = "SignUpActivity"

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

    var googleSignInClient : GoogleSignInClient? = null
    var GOOGLE_LOGIN_CODE = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Firebase Auth 초기화
        auth = Firebase.auth

        SignupButton = findViewById(R.id.SignupButton)

        // 로그인
        LoginButton = findViewById(R.id.LoginButton)
        LoginButton.setOnClickListener{
            signUp()
        }

        // 구글 로그인
        google_login_button = findViewById(R.id.google_login_button)
        google_login_button.setOnClickListener{
            googleLogin()
        }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("476872125178-er3sp9tg59ckruhvjq40r3k9kovifvlo.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        //회원가입 버튼 클릭 시 회원가입 화면으로 이동
        SignupButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startForResult.launch(signInIntent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result : ActivityResult ->
            if(result.resultCode == RESULT_OK){
                val intent : Intent = result.data!!
                val task : Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(intent)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    Log.w(ContentValues.TAG, "Google sign in failed",e)
                }
            }
        }

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount){
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener{
                    task ->
                if(task.isSuccessful) {
                    // 구글 로그인 성공하면 main 화면으로
                    moveMainPage(task.result?.user)
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun moveMainPage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
    }

    private fun signUp() {
        ID_editText = findViewById(R.id.ID_editText)
        password_editText = findViewById(R.id.password_editText)

        val email = ID_editText.text.toString()
        val password = password_editText.text.toString()

        // 이메일이나 패스워드나 패스워드 확인 입력 안했을 때
        if(email.length > 0 && password.length > 0 ) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        if (task.exception != null) {
                            // 비밀번호 또는 아이디가 잘못 입력됨
                            Toast.makeText(this, "비밀번호 또는 아이디가 잘못 입력되었습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
        }else {
            Toast.makeText(this, "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}