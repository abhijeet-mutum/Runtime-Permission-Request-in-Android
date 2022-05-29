package com.abjt.runtimepermissionwithinheritence.base_fragment

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.abjt.runtimepermissionwithinheritence.helpers.Permissions

abstract class BaseParentFragment : Fragment() {

    private var permissionResults: BooleanArray = booleanArrayOf(false, false)

    /*
        activityResultLauncher needs to be registered before onCreate() either in activity or fragment
        So make the class Abstract and create an abstract fun. Since we need to check permission everywhere this method is good
     */
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.forEach { permission ->
            when (permission.key) {
                Permissions.READ_PERMISSION.permissionName -> {
                    permissionResults[0] = permission.value
                }
                Permissions.WRITE_PERMISSION.permissionName -> {
                    permissionResults[1] = permission.value
                }
            }
        }
        onPermissionResult(validateResult())
    }

    fun launchPermissionRequest() = activityResultLauncher.launch(arrayOf(Permissions.READ_PERMISSION.permissionName, Permissions.WRITE_PERMISSION.permissionName))

    private fun validateResult(): Boolean {
        permissionResults.forEach { permissionResult ->
            if (permissionResult.not()) return false
        }
        return true
    }

    protected abstract fun onPermissionResult(isPermissionGranted: Boolean)
}