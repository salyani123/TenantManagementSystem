# Lab 6: Navigation Practical 2 (Lifecycles and Fragments)

**Name:** <your name>
**Reg No:** 192194
**Unit:** BBT 3.2 Mobile Application Development (Kotlin)

## What this project contains
- LoginActivity and RegisterActivity (moving with Intents)
- MainActivity with the Add Tenant form, Data Binding, and an implicit Intent for CALL TENANT
- Activity lifecycle logging in LoginActivity, RegisterActivity and MainActivity (tag `TENANT_LIFECYCLE`)
- State saving across rotation with onSaveInstanceState and a Bundle (tenant and save counter)
- Tenant (Serializable) and TenantRepository (object)
- DashboardFragment, AddTenantFragment, ViewTenantsFragment with full Fragment lifecycle logging
- Navigation Graph (nav_graph.xml) connecting the three Fragments, with Safe Args

## Note
The Fragments are not displayed yet. They are plugged into MainActivity with a NavHost in Practical 3,
so their lifecycle logs appear only after that.

## Logcat screenshots
*(Screenshots saved in the `screenshots/` folder)*

## Lab 7: Navigation Practical 3
- MainActivity hosts a NavHost, App Bar, options menu (⋮) and navigation drawer (☰)
- Safe Args passes a Tenant from AddTenantFragment to ViewTenantsFragment
- popUpTo fixes the back stack; slide animations on Dashboard actions
- CALL TENANT (implicit Intent) is on View Tenants
- Drawer header shows the logged-in email (from LoginActivity extra)
- Try it yourself: Fragment logs (task 1), 10-digit phone check (task 2), AboutFragment as 4th destination (task 3), Logout in the drawer (task 4)

![Dashboard with drawer](screenshots/lab7-drawer.png)
![View Tenants](screenshots/lab7-view-tenants.png)
![Fragment lifecycle logs](screenshots/lab7-fragment-logs.png)
