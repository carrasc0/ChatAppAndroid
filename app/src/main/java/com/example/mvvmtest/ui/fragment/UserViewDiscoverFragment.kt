package com.example.mvvmtest.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.example.mvvmtest.R
import com.example.mvvmtest.interfaces.OnUserViewDiscoverFragmentActionListener
import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.util.DiscoverAction
import butterknife.OnClick
import kotlinx.android.synthetic.main.user_view_discover_fragment.*

class UserViewDiscoverFragment : Fragment(), View.OnClickListener {

    private var user: DiscoverUser? = null
    private var onActionListener: OnUserViewDiscoverFragmentActionListener? = null

    companion object {

        fun newInstance(user: DiscoverUser): UserViewDiscoverFragment {
            val fragment = UserViewDiscoverFragment()
            val args = Bundle()
            args.putParcelable("user", user)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments!!.getParcelable("user")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_view_discover_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        processUser()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is OnUserViewDiscoverFragmentActionListener) {
            onActionListener = parentFragment as OnUserViewDiscoverFragmentActionListener?
        } else {
            throw RuntimeException("$context must implement OnUserViewDiscoverFragmentActionListener")
        }
    }

    private fun processUser() {
        processImages()
        processText()
    }

    private fun initListeners() {
        btnILoveIt.setOnClickListener(this)
        btnDislike.setOnClickListener(this)
        btnlike.setOnClickListener(this)
    }

    private fun processImages() {
        //todo hacer logica para la cantidad de imagenes que bajaron
        //todo procesar zodiaco
        Glide.with(activity!!)
                .asBitmap()
                .load(R.drawable.leo)
                .into(zodiacDU)

        Glide.with(context!!)
                .asBitmap()
                .load(R.drawable.image1)
                .into(image1DU)

        Glide.with(context!!)
                .asBitmap()
                .load(R.drawable.image2)
                .into(image2DU)

        Glide.with(context!!)
                .asBitmap()
                .load(R.drawable.image3)
                .into(image3DU)
    }

    private fun processText() {
        nameDU.text = "Aproximadamente a ${user!!.distance} km"
        cityDU.text = user!!.city
        homeTownDU.text = user!!.hometown
        professionDU.text = user!!.profession
        jobDU.text = user!!.job
        heightDU.text = user!!.height
        drinkDU.text = "Sometimes"
        smokeDU.text = "Sometimes"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnILoveIt ->
                onActionListener!!.onUserAction(user!!.idUser, DiscoverAction.I_LOVE_IT)
            R.id.btnDislike ->
                onActionListener!!.onUserAction(user!!.idUser, DiscoverAction.DISLIKE)
            R.id.btnlike ->
                onActionListener!!.onUserAction(user!!.idUser, DiscoverAction.LIKE)
            else -> {
            }
        }
    }

}
