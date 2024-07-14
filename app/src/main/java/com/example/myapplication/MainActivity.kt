package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG :String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        setContentView(R.layout.activity_main)

        val login_button = findViewById<Button>(R.id.login_button)
        val join_button = findViewById<Button>(R.id.join_button)
        val username = findViewById<TextView>(R.id.username)
        val email_area = findViewById<TextView>(R.id.email_area)
        val passwordarea = findViewById<TextView>(R.id.passwordarea)

        auth = Firebase.auth

        join_button.setOnClickListener{
            val uName = username.getText().toString()
            val uEmail = email_area.getText().toString()
            val uPwd = passwordarea.getText().toString()

            auth.createUserWithEmailAndPassword(uEmail, uPwd) //계정 생성
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG,"성공")
                    } else {
                        Log.d(TAG,"실패")
                    }
                }
        }

        login_button.setOnClickListener{
            val intent = Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}