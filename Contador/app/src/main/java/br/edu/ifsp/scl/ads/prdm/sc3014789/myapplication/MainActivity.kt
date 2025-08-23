package br.edu.ifsp.scl.ads.prdm.sc3014789.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.edu.ifsp.scl.ads.prdm.sc3014789.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(amb) {
            setContentView(amb.root)

            cliqueBt.setOnClickListener {
                contadorTv.text = (++counter).toString()

                // mostrar pop up na tela
                Toast.makeText(this@MainActivity, "vc clicou no " + contadorTv.text, Toast.LENGTH_SHORT).show()
            }

            inicialEt.addTextChangedListener{ p0 -> counter = p0.toString().toInt() }
        }

    }
}