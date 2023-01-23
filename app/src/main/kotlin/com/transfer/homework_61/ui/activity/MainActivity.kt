package com.transfer.homework_61.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.transfer.homework_61.R
import com.transfer.homework_61.databinding.ActivityMainBinding
import com.transfer.homework_61.utils.Constants.TEXT
import com.transfer.homework_61.utils.showToast

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textManipulation()
    }

    private fun textManipulation() {
        saveText()
        insertText()
        initClicker()
    }

    private fun saveText() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.etMain.setText(result.data?.getStringExtra(TEXT))
                }
            }
    }

    private fun insertText() {
        binding.etMain.setText(intent.getStringExtra(TEXT))
    }

    private fun initClicker() {
        binding.btnSendGo.setOnClickListener {
            if (binding.etMain.text.toString().isEmpty()) {
                showToast(getString(R.string.toastEmpty))
            } else sendText()
        }
    }

    private fun sendText() {
        Intent(this, SecondActivity::class.java).apply {
            putExtra(TEXT, binding.etMain.text.toString())
            resultLauncher.launch(this)
        }
    }


}