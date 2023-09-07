package com.techwings.myapplication.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techwings.myapplication.data.dto.Transaction
import com.techwings.myapplication.databinding.ItemTransactionBinding

class TransactionsAdapter: RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    var items: List<Transaction> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClick: ((Transaction) -> Unit)? = null

    fun setOnItemClickListener(onClick: ((Transaction) -> Unit)) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding, onClick)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class TransactionViewHolder(
        private val binding: ItemTransactionBinding,
        private val onClick: ((Transaction) -> Unit)? = null,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.tvItemDate.text = transaction.formatCreatedDate
            binding.tvItemName.text = transaction.fullName
            binding.tvItemPrice.text = transaction.formatSum
            binding.root.setOnClickListener { onClick?.invoke(transaction) }
        }
    }
}