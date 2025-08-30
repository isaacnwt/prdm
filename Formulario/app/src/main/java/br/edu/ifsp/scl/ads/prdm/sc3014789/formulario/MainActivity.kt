package br.edu.ifsp.scl.ads.prdm.sc3014789.formulario

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3014789.formulario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.estadoCivilSp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    activityMainBinding.conjugeLl.visibility = View.VISIBLE
                } else {
                    activityMainBinding.conjugeLl.visibility = View.GONE
                    activityMainBinding.nomeConjugeEt.setText("")
                    activityMainBinding.sobrenomeConjugeEt.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }
}