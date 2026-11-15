package com.example.tenantmanagementsystem.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tenantmanagementsystem.Tenant
import com.example.tenantmanagementsystem.TenantRepository

class TenantViewModel : ViewModel() {
    private val _tenants = MutableLiveData<List<Tenant>>(TenantRepository.tenants.toList())
    val tenants: LiveData<List<Tenant>> = _tenants

    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) "No tenants yet. Add one from the Dashboard."
        else list.joinToString("\n\n") { it.summary() }
    }

    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()
        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) return null

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)
        TenantRepository.addTenant(tenant)
        _tenants.value = TenantRepository.tenants.toList()
        return tenant
    }

    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}
