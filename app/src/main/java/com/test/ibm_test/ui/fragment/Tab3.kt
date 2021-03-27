package com.test.ibm_test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.ibm_test.R
import com.test.ibm_test.databinding.FragmentNotificationsBinding
import com.test.ibm_test.viewmodel.TabTypeViewModel

class Tab3 : Fragment() {

    private lateinit var notificationsViewModel: TabTypeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(TabTypeViewModel::class.java)
        val fragmentDashboardBinding= DataBindingUtil.inflate<FragmentNotificationsBinding>(inflater,R.layout.fragment_notifications,container, false)
        notificationsViewModel._text.apply {
            value="Tab 3 Open"
        }
        fragmentDashboardBinding.data=notificationsViewModel
        fragmentDashboardBinding.executePendingBindings()

        return fragmentDashboardBinding.root
    }
}