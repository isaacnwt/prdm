package br.edu.ifsp.scl.ads.prdm.sc3014789.menu

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.edu.ifsp.scl.ads.prdm.sc3014789.menu.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // guarda o item selecionado do menu
    private var opcaoSelecionada: Int = R.id.view_mi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        // Configura toolbar
        setSupportActionBar(amb.toolbar)

        // Configura drawer toggle (hambúrguer)
        val toggle = ActionBarDrawerToggle(
            this,
            amb.drawerLayout,
            amb.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        amb.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Listener para o NavigationView
        amb.navigationView.setNavigationItemSelectedListener(this)

        // Botão de ação executa a funcionalidade escolhida no menu
        amb.actionBtn.setOnClickListener {
            executarAcao(opcaoSelecionada)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Atualiza a opção selecionada
        opcaoSelecionada = item.itemId
        amb.drawerLayout.closeDrawers()
        return true
    }

    private fun executarAcao(itemId: Int) {
        when (itemId) {
            // Abre site digitado
            R.id.view_mi -> {
                val url = Uri.parse(amb.parametroTv.text.toString())
                val navegadorIntent = Intent(Intent.ACTION_VIEW, url)
                startActivity(navegadorIntent)
            }

            // Abre discador (não precisa de permissão)
            R.id.dial_mi -> {
                val numero = amb.parametroTv.text.toString()
//                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$numero"))
//                startActivity(dialIntent)


                // 🔒 Se o professor quiser ACTION_CALL direto:
                val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$numero"))
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    startActivity(callIntent)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        1
                    )
                }

            }

            // Abre contatos
            R.id.pick_mi -> {
                val pickIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                startActivity(pickIntent)
            }

            // Abre chooser para escolher navegador
            R.id.chooser_mi -> {
                val url = Uri.parse(amb.parametroTv.text.toString())
                val navegadorIntent = Intent(Intent.ACTION_VIEW, url)
                val chooser = Intent.createChooser(navegadorIntent, "Abrir com...")
                startActivity(chooser)
            }
        }
    }
}
