package com.medunnes.telemedicine

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.medunnes.telemedicine.databinding.ActivityResetPasswordBinding
import com.medunnes.telemedicine.ui.auth.login.LoginActivity

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnForgotPassword.setOnClickListener {
            val email = binding.tieUserEmail.text.toString()
            val tieUserEmail = binding.tieUserEmail

            if (email.isEmpty()) {
                tieUserEmail.error = "Email tidak boleh kosong"
                tieUserEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tieUserEmail.error = "Masukkan Email yang valid"
                tieUserEmail.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Reset Password URL Berhasil Dikirim", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    tieUserEmail.error = "${it.exception?.message}"
                    tieUserEmail.requestFocus()
                    return@addOnCompleteListener

                }
            }
        }

    }
}
