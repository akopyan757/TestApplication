package com.techwings.myapplication.ui.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techwings.myapplication.data.dto.Transaction
import com.techwings.myapplication.data.repository.TransactionRepository
import com.techwings.myapplication.data.repository.TransactionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TransactionCreateViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    val firstName = MutableLiveData("")
    val surname = MutableLiveData("")
    val phone = MutableLiveData("")
    val sum = MutableLiveData("")
    val bankCard = MutableLiveData("")

    private val _result = MutableLiveData<TransactionResult<Unit>>()
    val result: LiveData<TransactionResult<Unit>> = _result

    val enabled = MediatorLiveData(false).apply {
        addSource(phone) { value = isFieldsValid() }
        addSource(surname) { value = isFieldsValid() }
        addSource(firstName) { value = isFieldsValid() }
        addSource(sum) { value = isFieldsValid() }
        addSource(bankCard) { value = isFieldsValid() }
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            repository.createTransaction(
                Transaction(
                    uid = UUID.randomUUID().hashCode().toLong(),
                    lastName = surname.value,
                    firstName = firstName.value,
                    phone = phone.value?.filter { it in '0'..'9' },
                    sum = sum.value?.toInt() ?: 0,
                    bankCard = bankCard.value?.filter { it in '0'..'9' },
                    createdDate = Date().time,
                )
            ).collect { result ->
                _result.value = result
            }
        }
    }

    private fun isFieldsValid(): Boolean {
        return isNameValid().also { Log.i("Transiction", "isNameValid = $it") } &&
                isSumValid().also { Log.i("Transiction", "isSumValid = $it") }  &&
                isPhoneValid().also { Log.i("Transiction", "isPhoneValid = $it") }  &&
                isBankCardValid().also { Log.i("Transiction", "isBankCardValid = $it") }
    }

    private fun isNameValid(): Boolean {
        return !firstName.value.isNullOrEmpty() && !surname.value.isNullOrEmpty()
    }

    private fun isSumValid(): Boolean {
        return sum.value?.toIntOrNull()?.takeIf { it > 0 } != null
    }

    private fun isPhoneValid(): Boolean {
        return phone.value?.filter { it in '0'..'9' }?.length == 10
    }

    private fun isBankCardValid(): Boolean {
        return bankCard.value?.filter { it in '0'..'9' }?.length == 16
    }
}