package com.utad.pmdmud03

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "USER_PROFILE")

class RegistroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        val etUserR = findViewById<EditText>(R.id.etUserR)
        val etPasswordR = findViewById<EditText>(R.id.etPasswordR)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnCreateAcc = findViewById<Button>(R.id.btnCreateAcc)


        btnCreateAcc.setOnClickListener {
            val userName = etUserR.text.toString().trim()
            val password = etPasswordR.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                runOnUiThread {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            } else if (password != confirmPassword) {
                runOnUiThread {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Save data and move to MainActivity
                lifecycleScope.launch(Dispatchers.IO) {
                    saveValues(etUserR.text.toString(),
                        etPasswordR.text.toString(),
                        etConfirmPassword.text.toString())
                    runOnUiThread {
                        val intent = Intent(this@RegistroActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Cierra RegistroActivity
                    }
                }
            }


        }


    }

    private suspend fun saveValues(userName: String, password: String, confirmPassword: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("USER_NAME")] = userName
            preferences[stringPreferencesKey("PASSWORD")] = password
            preferences[stringPreferencesKey("CONFIRM_PASSWORD")] = confirmPassword


        }
    }
}