package com.techwings.myapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techwings.myapplication.R
import com.techwings.myapplication.databinding.FragmentTransactionListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionListFragment : Fragment() {

    private lateinit var viewModel: TransactionListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_create)
        }
        val adapter = TransactionsAdapter()
        adapter.setOnItemClickListener { transaction ->  
            findNavController().navigate(
                R.id.action_details, bundleOf("id" to (transaction.uid))
            )
        }
        binding.rvList.adapter = adapter
        viewModel.listTransactions.observe(viewLifecycleOwner) { transactions ->
            adapter.items = transactions
        }
        return binding.root
    }
}