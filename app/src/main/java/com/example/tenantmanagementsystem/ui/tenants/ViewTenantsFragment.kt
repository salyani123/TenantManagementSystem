package com.example.tenantmanagementsystem.ui.tenants

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding
import android.net.Uri
import com.example.tenantmanagementsystem.data.repository.TenantRepository

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null

    private val binding get() = _binding!!

    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newTenant = args.newTenant
        binding.tenant = newTenant
        if (newTenant == null) {
            binding.newTenantGroup.visibility = View.GONE
        }

        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                startActivity(intent)
            }
        }

        val tenants = TenantRepository.tenants

        if (tenants.isEmpty()) {
            binding.allTenantsTextView.text = "No tenants yet. Add one from the Dashboard."
        } else {
            binding.allTenantsTextView.text = tenants.joinToString("\n\n") { it.summary() }
        }

    }

    // Visible
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onStart")
    }

    // In front, user can interact
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onResume")
    }

    // Leaving the front
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onPause")
    }

    // No longer visible
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onStop")
    }

    // The VIEW is destroyed. Clear the binding so the old view can be freed
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onDestroyView")
        _binding = null
    }

    // The Fragment OBJECT is destroyed
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", " ViewTenantsFragment: onDestroy")
    }

}