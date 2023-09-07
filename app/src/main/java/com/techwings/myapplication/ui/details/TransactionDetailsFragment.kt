package com.techwings.myapplication.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techwings.myapplication.databinding.FragmentTransactionDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailsFragment : Fragment() {

    private lateinit var viewModel: TransactionDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionDetailsBinding
            .inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        viewModel.transaction.observe(viewLifecycleOwner) { transaction ->
            binding.tvName.text = transaction.fullName
            binding.tvDate.text = transaction.formatCreatedDate
            binding.tvBankCard.text = buildString {
                val bank = transaction.bankCard.orEmpty()
                bank.indices.forEach { i ->
                    if (i % 4 == 0 && i > 0)
                        append(' ')
                    append(bank[i])
                }
            }
            binding.tvSum.text = transaction.formatSum
            transaction.phone?.iterator()?.let { chatIterator ->
                fun CharIterator.next(count: Int) = buildString { repeat(count) { append(next()) } }
                binding.tvPhoneNumber.text = with(chatIterator) {
                    "+7 (${next(3)}) ${next(3)}-${next(2)}-${next(2)}"
                }
            }
        }
        viewModel.getTransaction(arguments?.getLong("id") ?: -1)
        return binding.root
    }

}