package com.berkaykbl.whisperbud.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.berkaykbl.whisperbud.R
import com.berkaykbl.whisperbud.Utils
import com.berkaykbl.whisperbud.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity: AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        binding.usernameEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.usernameText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.usernameText.translationY = 0f
            }
        }
        binding.passwordEdit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.passwordText.translationY = resources.getDimension(R.dimen.auth_subtitle_translation)
            } else {
                binding.passwordText.translationY = 0f
            }
        }

        binding.toRegister.setOnClickListener {
            Utils().changeActivity(this@LogInActivity, RegisterActivity::class.java, true)
        }

        binding.loginButton.setOnClickListener {

            val usernameV = binding.usernameEdit
            val passwordV = binding.passwordEdit


            val username = usernameV.text.toString()
            val password = passwordV.text.toString()
            var anyEmpty = false
            usernameV.isActivated = false
            passwordV.isActivated = false
            if (username.isEmpty()) {
                usernameV.isActivated = true
                anyEmpty = true
            }
            if (password.isEmpty()) {
                passwordV.isActivated = true
                anyEmpty = true
            }

            if (anyEmpty) {
                auth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.usernameEdit.setText("")
                        binding.passwordEdit.setText("")
                        Utils().changeActivity(this@LogInActivity, MainActivity::class.java, false)
                    } else {
                        it.exception!!.localizedMessage?.let { it1 ->
                            binding.errorMessage.text = it1
                            binding.errorMessage.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                    }
                }
            }
        }
    }
}