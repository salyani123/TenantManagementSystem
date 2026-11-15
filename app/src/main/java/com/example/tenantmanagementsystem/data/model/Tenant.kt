package com.example.tenantmanagementsystem.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

// Serializable means a Tenant can be packed up and sent to another screen.
// Safe Args needs this is Practical 3

@Entity(tableName = "tenants")
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable {
    fun summary(): String {
        return """
            Tenant: $name
            Phone: $phone
            Rent paid: Ksh $rent
        """.trimIndent()
    }
}
