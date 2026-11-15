package com.example.tenantmanagementsystem.ui.tenants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel

class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null

    private val binding get() = _binding!!
    private val viewModel: TenantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val tenant = viewModel.addTenant(
                binding.tenantNameEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.rentEditText.text.toString()
            )
            if (tenant == null) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val action = AddTenantFragmentDirections
                .actionAddTenantFragmentToViewTenantsFragment(tenant)

            // Ask the NavController to follow the action
            findNavController().navigate(action)

        }

    }

    // Visible
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onStart")
    }

    // In front, user can interact
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onResume")
    }

    // Leaving the front
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onPause")
    }

    // No longer visible
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onStop")
    }

    // The VIEW is destroyed. Clear the binding so the old view can be freed
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onDestroyView")
        _binding = null
    }

    // The Fragment OBJECT is destroyed
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", " AddTenantFragment: onDestroy")
    }

}