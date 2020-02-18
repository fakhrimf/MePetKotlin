package com.chewie.mepet.references.tips_references

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesTipsModel
import com.chewie.mepet.utils.TIPS_INTENT_KEY

class ReferencesTipsDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_references_tips_detail)
        beginTransaction()
    }

    private fun beginTransaction(){
        val getIntent = intent.getParcelableExtra<ReferencesTipsModel>(TIPS_INTENT_KEY)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = ReferencesTipsDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable(TIPS_INTENT_KEY,getIntent)
        fragment.arguments = bundle
        transaction.add(R.id.tipsDetailFragment,fragment)
        transaction.commit()
    }
}