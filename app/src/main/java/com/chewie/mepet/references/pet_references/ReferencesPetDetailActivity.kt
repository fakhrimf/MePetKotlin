package com.chewie.mepet.references.pet_references

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chewie.mepet.R
import com.chewie.mepet.model.ReferencesPetModel
import com.chewie.mepet.utils.PET_INTENT_KEY

class ReferencesPetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_references_pet_detail)
        dataTransaction()
    }

    private fun dataTransaction(){
        val getIntent = getIntent().getParcelableExtra<ReferencesPetModel>(PET_INTENT_KEY)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = ReferencesPetDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable(PET_INTENT_KEY,getIntent)
        fragment.arguments = bundle
        transaction.add(R.id.petDetailFragment,fragment)
        transaction.commit()
    }
}
