package com.test.ibm_test.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.test.ibm_test.MainActivity
import com.test.ibm_test.R


class CreateNotificationFragment : Fragment() {

    lateinit var activityContext:MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext=context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view1=view.findViewById<AppCompatEditText>(R.id.notification_msg)
        val view2=view.findViewById<AppCompatEditText>(R.id.redirection_tab)
        val createNoti=view.findViewById<AppCompatButton>(R.id.create_notification)
        view1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEnableDisableEligibilty(view1, view2, createNoti)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        view2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEnableDisableEligibilty(view1, view2, createNoti)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        createNoti?.setOnClickListener {

            if(this::activityContext.isInitialized)
                activityContext.addNotification(view1.text.toString(),view2.text?.toString()?.trim()?.toInt()!!)
        }

    }




    fun checkEnableDisableEligibilty(
        view1: AppCompatEditText,
        view2: AppCompatEditText,
        view3: AppCompatButton
    )
    {
        if(checkButtonEnable(view1, view2))
            view3.visibility=View.VISIBLE
        else
            view3.visibility=View.GONE

    }



    fun checkButtonEnable(view1: AppCompatEditText, view2: AppCompatEditText):Boolean
    {
        if(view1.text?.toString()?.trim()?.isEmpty()!! && view2.text?.toString()?.trim()?.isEmpty()!!) {
            showToast(getString(R.string.both_blank))
            return false
        }
        else if(view1.text?.toString()?.trim()?.isEmpty()!! || view2.text?.toString()?.trim()?.isEmpty()!!) {
            showToast(getString(R.string.one_of_the_blank))
            return false
        }
        else if(!view2.text?.toString()?.trim()?.equals("2")!! && !view2.text?.toString()?.trim()?.equals("3"
            )!!) {
            showToast(getString(R.string.no_2_or_3))
            return false
        }
        return true
    }

    fun showToast(msg:String)
    {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
    }




}