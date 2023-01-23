package com.transfer.homework_61.ui.activity


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.transfer.homework_61.R
import com.transfer.homework_61.databinding.ActivitySecondBinding
import com.transfer.homework_61.utils.Constants.TEXT
import com.transfer.homework_61.utils.showToast

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private val binding by viewBinding(ActivitySecondBinding::bind)
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

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
                    binding.etSecond.setText(result.data?.getStringExtra(TEXT))
                }
            }
    }

    private fun insertText() {
        binding.etSecond.setText(intent.getStringExtra(TEXT))
    }

    private fun initClicker() {
        binding.btnSendBack.setOnClickListener {
            if (binding.etSecond.text.toString().isEmpty()) {
                showToast(getString(R.string.toastEmpty))
            } else sendText()
        }
    }

    private fun sendText() {
        Intent(this, MainActivity::class.java).apply {
            putExtra(TEXT, binding.etSecond.text.toString())
            resultLauncher.launch(this)
        }
    }
}