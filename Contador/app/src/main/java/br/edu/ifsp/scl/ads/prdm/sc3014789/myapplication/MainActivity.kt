package br.edu.ifsp.scl.ads.prdm.sc3014789.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    private lateinit var cliqueBt: Button
    private lateinit var contadorTv: TextView
    private val inicialEt: EditText by lazy {
        findViewById(R.id.inicial_et)
    }
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contadorTv = findViewById(R.id.contador_tv)
        cliqueBt = findViewById(R.id.clique_bt)


        cliqueBt.setOnClickListener {
            contadorTv.text = (++counter).toString()
        }

        inicialEt.addTextChangedListener{ p0 -> counter = p0.toString().toInt() }
    }
}