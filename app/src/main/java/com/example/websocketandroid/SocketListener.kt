package com.example.websocketandroid

import com.example.websocketandroid.model.BitCoin
import okhttp3.Response

interface SocketListener {
    fun onSuccess(bitCoin: BitCoin)
    fun onError(message: String)
}