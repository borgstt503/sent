
package com.sent.booster

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.content.pm.PackageManager
import rikka.shizuku.Shizuku

class MainActivity : Activity() {

    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 40, 32, 40)
            setBackgroundColor(Color.rgb(12, 14, 22))
        }

        val title = TextView(this).apply {
            text = "SENT"
            textSize = 38f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "GAMING OPTIMIZER"
            textSize = 14f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        status = TextView(this).apply {
            textSize = 14f
            setTextColor(Color.YELLOW)
            gravity = Gravity.CENTER
        }

        val connect = Button(this).apply {
            text = "VERIFICAR SHIZUKU"
            setOnClickListener {
                verificarShizuku()
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)
        layout.addView(connect)

        setContentView(layout)
        verificarShizuku()
    }

    private fun verificarShizuku() {
        try {
            if (!Shizuku.pingBinder()) {
                status.text = "SHIZUKU: DESCONNECTED"
                return
            }

            if (Shizuku.checkSelfPermission() ==
                PackageManager.PERMISSION_GRANTED) {

                status.text = "SHIZUKU: CONECTADO E AUTORIZADO"

            } else {
                status.text = "SHIZUKU: PERMISSÃO NECESSÁRIA"
                Shizuku.requestPermission(1001)
            }

        } catch (e: Exception) {
            status.text = "SHIZUKU: ERRO DE CONEXÃO"
        }
    }

    override fun onResume() {
        super.onResume()
        if (::status.isInitialized) {
            verificarShizuku()
        }
    }
}
