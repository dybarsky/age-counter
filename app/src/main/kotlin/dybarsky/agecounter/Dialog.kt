package dybarsky.agecounter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dybarsky.agecounter.databinding.AlertListBinding
import dybarsky.agecounter.databinding.ItemBinding
import java.text.DateFormatSymbols
import java.util.Locale

val days = (1..31).toList()
val months = DateFormatSymbols(Locale.ENGLISH).months.toList()
val years = (1921..2021).reversed().toList()

fun <V> Context.showDialog(values: List<V>, onSelect: (V) -> Unit) {
    var dialog: Dialog? = null
    val binding = AlertListBinding
        .inflate(LayoutInflater.from(this))
        .apply {
            items.layoutManager = layoutManager()
            items.adapter = adapter(values) {
                onSelect(it)
                dialog?.dismiss()
            }
        }

    dialog = MaterialAlertDialogBuilder(this)
        .setView(binding.root)
        .show()
}

private fun Context.layoutManager() = LinearLayoutManager(this).apply { orientation = VERTICAL }

private fun <V> adapter(values: List<V>, onSelect: (V) -> Unit) =
    object : RecyclerView.Adapter<BindingViewHolder<ItemBinding>>() {

        override fun getItemCount(): Int = values.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemBinding> {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemBinding.inflate(inflater, parent, false)
            return BindingViewHolder(binding)
        }

        override fun onBindViewHolder(holder: BindingViewHolder<ItemBinding>, position: Int) {
            val data = values[position]
            with(holder.binding) {
                item.text = data.toString()
                root.onClick(LONG_DELAY) { onSelect(data) }
            }
        }
    }

private class BindingViewHolder<V : ViewDataBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)
