package com.chewie.mepet.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import com.chewie.mepet.utils.ARGUMENTS_ID_KEY
// TODO: 03/12/2019 Create a proper viewmodel, a proper viewmodel shouldn't messing up with views

class HomeVM(application: Application) : AndroidViewModel(application) {
    fun newAddPetInstance(id: Int): AddPetFragment {
        val args = Bundle()
        args.putInt(ARGUMENTS_ID_KEY, id)
        val addpet = AddPetFragment()
        addpet.arguments = args
        return addpet
    }
}