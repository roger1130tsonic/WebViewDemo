package com.arctos.webviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.arctos.webviewdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainViewModel()

        viewModel.initWebViewController(binding.webview)
        viewModel.loadURL("file:///android_asset/demo.html")

        lifecycleScope.launch {
            viewModel.jsMessage.collect {
                if (it.isEmpty()) return@collect
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Native Alert")
                    .setPositiveButton("確定") { dialog, _ -> dialog.dismiss()}
                    .setMessage(it).show()
                viewModel.jsMessage.value = ""
            }
        }
    }
}