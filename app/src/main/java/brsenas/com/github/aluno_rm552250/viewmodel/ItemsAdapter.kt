package brsenas.com.github.aluno_rm552250.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import brsenas.com.github.aluno_rm552250.R
import brsenas.com.github.aluno_rm552250.model.ItemModel

class ItemsAdapter(private val onItemRemoved: (ItemModel) -> Unit) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemLocal = view.findViewById<TextView>(R.id.viewLocal)
        val itemTipoEvento = view.findViewById<TextView>(R.id.viewTipoEvento)
        val itemGrauImpacto = view.findViewById<TextView>(R.id.viewGrauImpacto)
        val itemData = view.findViewById<TextView>(R.id.viewData)
        val itemNrPessoasAfetadas = view.findViewById<TextView>(R.id.viewNrPessoasAfetadas)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(item: ItemModel) {
            itemLocal.text = item.local
            itemTipoEvento.text = item.tipo
            itemGrauImpacto.text = item.impacto
            itemData.text = item.data
            itemNrPessoasAfetadas.text = item.afetados.toString()

            button.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}