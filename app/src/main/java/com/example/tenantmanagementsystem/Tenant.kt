package com.example.tenantmanagementsystem

import java.io.Serializable

// Serializable means a Tenant can be packed up and sent to another screen.
// Safe Args needs this is Practical 3

data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {
    fun summary(): String {
        return """
            Tenant: $name
            Phone: $phone
            Rent paid: Ksh $rent
        """.trimIndent()
    }
}
