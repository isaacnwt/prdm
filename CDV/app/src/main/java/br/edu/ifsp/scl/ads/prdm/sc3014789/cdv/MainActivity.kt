package br.edu.ifsp.scl.ads.prdm.sc3014789.cdv

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import br.edu.ifsp.scl.ads.prdm.sc3014789.cdv.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.prdm.sc3014789.cdv.databinding.TileNameBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.addNameBt.setOnClickListener {
            TileNameBinding.inflate(layoutInflater).let { tileNameBinding ->
                amb.moreNamesLl.addView(tileNameBinding.root)
            }
        }

        Log.v(getString(R.string.app_name), "onCreate: Iniciando cdv COMPLETO")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val nameList = mutableListOf<String>()
        amb.moreNamesLl.children.forEach {
            (it as EditText).text.toString().let { name ->
                nameList.add(name)
            }
        }
        outState.putStringArray("NAMES", nameList.toTypedArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getStringArray("NAMES")?.forEach { name ->
            TileNameBinding.inflate(layoutInflater).let { tileNameBinding ->
                tileNameBinding.root.setText(name)
                amb.moreNamesLl.addView(tileNameBinding.root)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "onStart: Iniciando cdv VISÍVEL")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "onResume: Iniciando cdv FOREGROUND")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "onPause: Finalizando cdv FOREGROUND")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "onStop: Finalizando cdv VISÍVEL")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "onDestroy: Finalizando cdv COMPLETO")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "onRestart: preparando a execução do onRestart")
    }
}