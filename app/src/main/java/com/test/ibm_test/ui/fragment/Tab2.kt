package com.test.ibm_test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.ibm_test.R
import com.test.ibm_test.databinding.FragmentDashboardBinding
import com.test.ibm_test.viewmodel.TabTypeViewModel

class Tab2 : Fragment() {

    private lateinit var dashboardViewModel: TabTypeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(TabTypeViewModel::class.java)

       val fragmentDashboardBinding= DataBindingUtil.inflate<FragmentDashboardBinding>(inflater,R.layout.fragment_dashboard,container, false)

        dashboardViewModel._text.apply {
            value="Tab 2 Open"
        }

        fragmentDashboardBinding.data=dashboardViewModel
        fragmentDashboardBinding.executePendingBindings()
        return fragmentDashboardBinding.root
    }
}