
package com.sent.booster

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button

class MainActivity : Activity() {

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
            textSize = 36f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "GAMING OPTIMIZER"
            textSize = 13f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(subtitle)

        val modes = listOf("LITE", "ULTRA LITE", "PADRÃO")

        modes.forEach { mode ->
            val button = Button(this).apply {
                text = mode
                textSize = 16f
                setOnClickListener {
                    subtitle.text = "Modo selecionado: $mode"
                }
            }

            layout.addView(
                button,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    65
                ).apply {
                    setMargins(0, 18, 0, 0)
                }
            )
        }

        val status = TextView(this).apply {
            text = "SHIZUKU: AGUARDANDO CONEXÃO"
            textSize = 12f
            setTextColor(Color.YELLOW)
            gravity = Gravity.CENTER
        }

        layout.addView(status)
        setContentView(layout)
    }
}
