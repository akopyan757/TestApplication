package com.techwings.myapplication.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val uid: Long = UUID.randomUUID().node(),
    @ColumnInfo (name = "last_name") val lastName: String? = null,
    @ColumnInfo (name = "first_name") val firstName: String? = null,
    @ColumnInfo (name = "created_date") val createdDate: Long? = null,
    @ColumnInfo (name = "bank_card") val bankCard: String? = null,
    @ColumnInfo (name = "phone") val phone: String? = null,
    @ColumnInfo (name = "sum") val sum: Int? = null,
) {
    val fullName: String
        get() = (lastName.orEmpty() + " " + firstName.orEmpty()).trim()

    val formatCreatedDate: String
        get() = createdDate?.let { DATE_FORMAT.format(it) }.orEmpty()

    val formatSum: String
        get() = sum?.let { "$it ₽" }.orEmpty()

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("dd.MM.yyy HH:mm:ss", Locale.ENGLISH)
    }
}