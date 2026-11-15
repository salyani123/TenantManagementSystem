# Tenant Management System
An Android app for landlords to record tenants, see total rent, and call tenants.
## Download
Latest APK: see **Releases** on the right of this page.
## Features
- Register and log in
- Dashboard with number of tenants and total rent
- Add tenants and view all tenants (saved with Room)
- Call a tenant, options menu and navigation drawer
## Architecture
ui/ Activities, Fragments and TenantViewModel (UI layer)
data/ Tenant, TenantDao, TenantDatabase, TenantRepository (data layer)
Screens → TenantViewModel (LiveData) → TenantRepository → Room
## Team
| Stage | Member          | Admission No. | Pull request                                                                                                           |
|---|-----------------|---------------|------------------------------------------------------------------------------------------------------------------------|
| 1 Lead | Yusuf Salyani   | 147935        | [Starting Point](https://github.com/salyani123/TenantManagementSystem/commit/e048a9b6022f2104c41b1b9cc212f2549f3bfbff) |
| 2 ViewModel | Eugene Mirimba  | 190524        | [Stage 2: TenantViewModel - #2](https://github.com/salyani123/TenantManagementSystem/pull/2)                           |
| 3 Room | Aaron Museo     | 165689        | [Stage 3: Room database - #1](https://github.com/salyani123/TenantManagementSystem/pull/1)                             |
| 4 Screens | Japheth Njuguna | 192194        | [Stage 4: Screens use the ViewModel - #3](https://github.com/salyani123/TenantManagementSystem/pull/3)                 |
| 5 Data integration | Mark Ndungu     | 164948        | [Stage 5: Save tenants in Room - #4](https://github.com/salyani123/TenantManagementSystem/pull/4)                        |
| 6 Release | Yusuf Salyani   | 147935        | (link)                                                                                                                 |