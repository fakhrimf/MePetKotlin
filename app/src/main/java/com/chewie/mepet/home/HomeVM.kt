package com.chewie.mepet.home

import android.os.Bundle
import com.chewie.mepet.reminder.ReminderFragment

class HomeVM {
    fun newAddPetInstance(id: Int): AddPetFragment {
        val args = Bundle()
        args.putInt("id", id)
        val addpet = AddPetFragment()
        addpet.arguments = args
        return addpet
    }
}