package com.ayamgorengenak.capfits

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModelProvider
import com.ayamgorengenak.capfits.backend.ApiConfig
import com.ayamgorengenak.capfits.backend.UserRequest
import com.ayamgorengenak.capfits.backend.UserResponse
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ayamgorengenak.capfits.databinding.ActivityLoginBinding
import com.ayamgorengenak.capfits.ui.LoginViewModel
import com.ayamgorengenak.capfits.ui.UserPreference
import com.ayamgorengenak.capfits.ui.ViewModelFactory
import retrofit2.Call
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()

        binding.buttonDaftar.setOnClickListener {
            Intent(this@LoginActivity, DaftarActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]
    }

    private fun setupAction() {
        binding.buttonLogin.setOnClickListener {
            showLoading(true)
            login()
        }
    }

    private fun login() {
        loginViewModel.login()
        val request = UserRequest()
        request.email = binding.inputEmail.text.toString().trim()
        request.password = binding.inputPassword.text.toString().trim()
        val client = ApiConfig.getApiService()
        client.login(request).enqueue(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user == null) {
                        Log.e("nama", user?.loginResult?.nama.toString())
                        Log.e("token", user?.loginResult?.token.toString())
                    }

                    Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
//                    if (user != null) {
//                        loginViewModel.saveUser(user.loginResult?.token!!)
//                    }
                    success()

                } else {
                    val user = response.body()
                    if (user != null) {
                        Log.e(ContentValues.TAG, "onFailure : ${response.errorBody()}")
                    }
                    Toast.makeText(
                        this@LoginActivity,
                        "Email or password incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                    showLoading(false)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Intent(this@LoginActivity, HomeActivity::class.java).also {
//                    startActivity(it)
//                    finish()
//                }
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun success() {
        Intent(this@LoginActivity, HomeActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}