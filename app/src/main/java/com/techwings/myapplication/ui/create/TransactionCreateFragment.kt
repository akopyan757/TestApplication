package com.techwings.myapplication.ui.create

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techwings.myapplication.data.repository.TransactionResult
import com.techwings.myapplication.databinding.FragmentTransactionCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionCreateFragment : Fragment() {

    private lateinit var viewModel: TransactionCreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TransactionCreateViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionCreateBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.btnSave.setOnClickListener { viewModel.onSaveClicked() }
        binding.etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.etBankCard.addTextChangedListener(BankCardTextWatcher())
        viewModel.result.observe(viewLifecycleOwner) { result ->
            when (result) {
                is TransactionResult.Success -> {
                    findNavController().popBackStack()
                    Toast.makeText(requireContext(), "Транзакция создана!", Toast.LENGTH_SHORT).show()
                }
                is TransactionResult.Error -> {

                }
            }
        }
        viewModel.enabled.observe(viewLifecycleOwner) { enabled ->
            binding.btnSave.isEnabled = enabled
        }
        return binding.root
    }
}