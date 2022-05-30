package com.abjt.runtimepermissionwithinheritence.helpers

import android.Manifest

const val FRAGMENT_A_TAG = "fragment_A_tag"
const val FRAGMENT_B_TAG = "fragment_B_tag"

enum class Permissions(val permissionName: String){
    READ_PERMISSION(Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_PERMISSION(Manifest.permission.WRITE_EXTERNAL_STORAGE)
}

fun readWritePermissionRequest() = arrayOf(Permissions.READ_PERMISSION.permissionName, Permissions.WRITE_PERMISSION.permissionName)