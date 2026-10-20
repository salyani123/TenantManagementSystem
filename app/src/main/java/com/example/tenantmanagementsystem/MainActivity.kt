package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastTenant: Tenant? = null
    private var saveCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Write a message to Logcat every time onCreate() runs
        // "TENANT_LIFECYCLE" is a tag: a label we search for in Logcat.
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // ----RESTORE STATE----
        // savedInstanceState is the Bundle we filled in onSaveInstanceState()
        // It is null the FIRST time the activity opens (nothing saved yet)
        if (savedInstanceState != null) {
            // Read each value back using the SAME keys we saved with
            val name = savedInstanceState.getString("tenant_name")
            val phone = savedInstanceState.getString("tenant_phone")
            val rent = savedInstanceState.getString("tenant_rent")

            // Only rebuild the tenant if all three were saved
            if (name != null && phone != null && rent != null) {
                val tenant = Tenant(name, phone, rent)

                // Put the tenant back in BOTH places it lived before rotating:
                lastTenant = tenant // so CALL TENANT works again
                binding.tenant = tenant // so the summary appears again (Data Binding)
            }

            // Restore number of times save was tapped
            saveCount = savedInstanceState.getInt("save_count")

            // Display number of times save was tapped as a Toast
            Toast.makeText(this, "Save tapped $saveCount times", Toast.LENGTH_SHORT).show()

        }

        val loggedInEmail = intent.getStringExtra("EMAIL")

        if (loggedInEmail != null) {
            Toast.makeText(this, "Logged in as $loggedInEmail", Toast.LENGTH_SHORT).show()
        }

        var saveNumber = 0

        binding.saveButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString()

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
                return@setOnClickListener
            }

            val phone = binding.phoneEditText.text.toString()

            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
                return@setOnClickListener
            }

            val rent = binding.rentEditText.text.toString()

            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)

            binding.tenant = tenant

            lastTenant = tenant

            saveCount++

        }

        binding.callButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}"))

            startActivity(intent)
        }

        binding.shareButton.setOnClickListener {

            val tenant = lastTenant

            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"

            intent.putExtra(Intent.EXTRA_TEXT, tenant.summary())

            startActivity(Intent.createChooser(intent, "Share Tenant"))

        }
    }

    // ----SAVE STATE----
    // Android calls this juste before destroying the Activity
    // (for example, when the phone rotates).

    // outState is the Bundle that Android keeps for us
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("TENANT_LIFECYCLE", "MainActivity: onSaveInstanceState")

        // Copy into val so Kotlin knows it cant change after the check
        val tenant = lastTenant

        if (tenant != null) {
            // Store each field under its own KEY
            // onCreate() reads them back with the SAME keys.
            outState.putString("tenant_name", tenant.name)
            outState.putString("tenant_phone", tenant.phone)
            outState.putString("tenant_rent", tenant.rent)
        }

        outState.putInt("save_count", saveCount)
    }

    // ---- Lifecycle methods ----
    // Android calls each of these automatically. We only add a log.
    // line so we can watch the order in Logcat.

    // The screen is now VISIBLE
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    // The screen is in FRONT and the user can interact with it
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    // The screen is LEAVING the front (e.g. home was pressed)
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    // The screen is NO LONGER VISIBLE
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    // The user came BACK to a stopped screen
    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }
    // The screen is being DESTROYED
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }



}
