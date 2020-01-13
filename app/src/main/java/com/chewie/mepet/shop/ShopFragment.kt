package com.chewie.mepet.shop

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chewie.mepet.R
import com.chewie.mepet.utils.LINK_BUKALAPAK
import com.chewie.mepet.utils.LINK_SHOPEE
import com.chewie.mepet.utils.LINK_TOKOPEDIA
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ivToped?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_TOKOPEDIA)))
        }
        ivBuka?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_BUKALAPAK)))
        }
        ivShopi?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(LINK_SHOPEE)))
        }
    }
}
