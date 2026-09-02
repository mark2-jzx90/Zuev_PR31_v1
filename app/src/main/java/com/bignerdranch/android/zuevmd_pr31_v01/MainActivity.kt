package com.bignerdranch.android.zuevmd_pr31_v01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val loginInput = etLogin.text.toString().trim()
            val passwordInput = etPassword.text.toString().trim()


            if(loginInput.isEmpty() || passwordInput.isEmpty()){ //проврка на пустые поля
                AlertDialog.Builder(this)
                    .setMessage("Введите логин и пароль")
                    .setPositiveButton("OK",null)
                    .show()
                return@setOnClickListener
            }

            val savedLogin = sharedPref.getString("saved_login", null)
            val savedPassword = sharedPref.getString("saved_passsword", null)

            if(savedLogin != null && savedPassword != null){
                if(loginInput == "ects2023" && passwordInput == "ects2023"){
                    val intent = Intent(this, CalculatorActivity::class.java)
                    startActivity(intent)
                } else {
                    AlertDialog.Builder(this)
                        .setMessage("Неверный логин или пароль")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
            else{
                sharedPref.edit()
                    .putString("saved_login",loginInput)
                    .putString("saved_password",passwordInput)
                    .apply()

                val intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
            }
        }
    }
}