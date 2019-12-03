package com.chewie.mepet.shop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.home.AddPetFragment
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ivToped?.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://tokopedia.com")))
        }
        ivBuka?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://bukalapak.com")))
        }
        ivShopi?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.com")))
        }
    }
}
