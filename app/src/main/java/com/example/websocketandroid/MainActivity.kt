package com.example.websocketandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.websocketandroid.databinding.ActivityMainBinding
import com.example.websocketandroid.model.BitCoin
import okhttp3.*
import okio.ByteString

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var socketManager: SocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        socketManager = SocketManager()

        setupUI()

    }

    private fun setupUI() {
        binding.connectBtn.setOnClickListener {
            socketManager.connectToSocket()
        }

        socketManager.socketListener(object : SocketListener {
            override fun onSuccess(bitCoin: BitCoin) {
                runOnUiThread {
                    if (bitCoin.event == "bts:subscription_succeeded" ){
                        binding.connectBtn.text = "Successfully Connected, Wait a moment"
                    } else {
                        binding.btcBtn.text = "1 BTC"
                        Log.d("@@@","${bitCoin.data.price_str}" )
                        binding.usdBtn.text = "${bitCoin.data.price_str}"
                    }
                }
            }

            override fun onError(message: String) {
                TODO("Not yet implemented")
            }

        })
    }
}