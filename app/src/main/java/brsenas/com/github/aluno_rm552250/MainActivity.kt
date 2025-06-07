package brsenas.com.github.aluno_rm552250

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import brsenas.com.github.aluno_rm552250.model.ItemModel
import brsenas.com.github.aluno_rm552250.viewmodel.ItemsAdapter
import brsenas.com.github.aluno_rm552250.viewmodel.ItemsViewModel
import brsenas.com.github.aluno_rm552250.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ItemsViewModel

    lateinit var itemLocal: EditText
    lateinit var itemTipoEvento: EditText
    lateinit var itemGrauImpacto: EditText
    lateinit var itemData: EditText
    lateinit var itemNrPessoasAfetadas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "LogX - Registro de Eventos Extremos"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        itemLocal = findViewById(R.id.itemLocal)
        itemTipoEvento = findViewById(R.id.itemTipoEvento)
        itemGrauImpacto = findViewById(R.id.itemGrauImpacto)
        itemData = findViewById(R.id.itemData)
        itemNrPessoasAfetadas = findViewById(R.id.itemNrPessoasAfetadas)


        button.setOnClickListener {
            if (isAnyFieldEmpty(itemLocal, itemTipoEvento, itemGrauImpacto, itemData, itemNrPessoasAfetadas))
                return@setOnClickListener

            if (itemNrPessoasAfetadas.text.toString().toInt() <= 0) {
                itemNrPessoasAfetadas.error = "Informe valor maior que zero"
                return@setOnClickListener
            }

            val item = ItemModel(
                local = itemLocal.text.toString(),
                tipo = itemTipoEvento.text.toString(),
                impacto = itemGrauImpacto.text.toString(),
                data = itemData.text.toString(),
                afetados = itemNrPessoasAfetadas.text.toString().toInt()
            )

            viewModel.addItem(item)
            clearFields()
            hideKeyboard(currentFocus ?: View(this), this)
        }


        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }

    private fun isAnyFieldEmpty(vararg fields: EditText): Boolean {
        var isAnyFieldEmpty = false
        for (field in fields) {
            if (field.text.isEmpty()) {
                field.error = "Preencha um valor"
                isAnyFieldEmpty = true
            } else {
                field.error = null
            }
        }
        return isAnyFieldEmpty
    }

    private fun clearFields() {
        itemLocal.text.clear()
        itemTipoEvento.text.clear()
        itemGrauImpacto.text.clear()
        itemData.text.clear()
        itemNrPessoasAfetadas.text.clear()
    }

    fun hideKeyboard(view: View, context: Context) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

