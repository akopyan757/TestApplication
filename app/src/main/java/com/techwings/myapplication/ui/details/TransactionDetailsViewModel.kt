package com.techwings.myapplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techwings.myapplication.data.dto.Transaction
import com.techwings.myapplication.data.repository.TransactionRepository
import com.techwings.myapplication.data.repository.TransactionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _transaction = MutableLiveData<Transaction>()

    val transaction: LiveData<Transaction>
        get() = _transaction

    fun getTransaction(id: Long) {
        viewModelScope.launch {
            repository.getTransactionById(id).collect { result ->
                when (result) {
                    is TransactionResult.Success -> {
                         _transaction.value = result.data ?: Transaction()
                    }
                    is TransactionResult.Error -> {
                        result.throwable.printStackTrace()
                    }
                }
            }
        }
    }
}