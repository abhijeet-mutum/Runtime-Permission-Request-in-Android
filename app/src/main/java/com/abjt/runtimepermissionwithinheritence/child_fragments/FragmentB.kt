package com.abjt.runtimepermissionwithinheritence.child_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abjt.runtimepermissionwithinheritence.R
import com.abjt.runtimepermissionwithinheritence.base_fragment.BaseParentFragment
import com.abjt.runtimepermissionwithinheritence.helpers.setOnClick
import com.abjt.runtimepermissionwithinheritence.helpers.showToast
import com.google.android.material.button.MaterialButton

class FragmentB : BaseParentFragment() {

    private lateinit var mbRequestPermission: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        attachClickListeners()
    }

    private fun initViews(view: View) {
        mbRequestPermission = view.findViewById(R.id.mb_request_permission)
    }

    private fun attachClickListeners() {
        mbRequestPermission.setOnClick {
            if (isPermissionGranted()) {
                showToast(requireActivity(), "READ WRITE PERMISSION GRANTED")
            } else {
                launchPermissionRequest()
            }
        }
    }

    /*
    On PermissionResult Will be triggered only after validating the request permission.
    This fun will return either true or false.
    True -> only when all the permissions are granted
    False -> if any one the permission is denied

    onPermissionResult implement your logic here (like navigate to another activity or sending an Intent)
 */
    override fun onPermissionResult(isPermissionGranted: Boolean) {
        if (isPermissionGranted) {
            showToast(requireActivity(), "READ WRITE PERMISSION GRANTED")
        } else {
            showToast(requireActivity(), "READ WRITE PERMISSION DENIED")
        }
    }

    companion object {
        fun getInstance() = FragmentB()
    }
}