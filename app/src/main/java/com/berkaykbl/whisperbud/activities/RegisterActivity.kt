package com.berkaykbl.whisperbud.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.berkaykbl.whisperbud.R
import com.berkaykbl.whisperbud.Utils
import com.berkaykbl.whisperbud.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usernameEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.usernameText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.usernameText.translationY = 0f
            }
        }

        binding.emailEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.emailText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.emailText.translationY = 0f
            }
        }
        binding.passwordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.passwordText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.passwordText.translationY = 0f
            }
        }

        binding.passwordAgainEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.passwordAgainText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.passwordAgainText.translationY = 0f
            }
        }

        binding.toLogin.setOnClickListener {
            Utils().changeActivity(this@RegisterActivity, LogInActivity::class.java, true)
        }

    }
}