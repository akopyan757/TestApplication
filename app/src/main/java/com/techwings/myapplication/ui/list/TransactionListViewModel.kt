package com.techwings.myapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techwings.myapplication.data.dto.Transaction
import com.techwings.myapplication.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _listTransactions = MutableLiveData<List<Transaction>>(emptyList())

    val listTransactions: LiveData<List<Transaction>>
        get() = _listTransactions

    init {
        viewModelScope.launch {
            repository.getAllTransactions().collect { transactions ->
                _listTransactions.value = transactions.sortedByDescending { it.createdDate }
            }
        }
    }
}