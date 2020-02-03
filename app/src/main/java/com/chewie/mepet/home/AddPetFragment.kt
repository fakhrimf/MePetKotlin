package com.chewie.mepet.home

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

class AddPetFragment : Fragment() {
    private lateinit var encodedImage:String

    private val vm by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(AddPetVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNpValue()
        editSet(arguments)

        encodedImage = ""
        btnAddPet.setOnClickListener {
            if (checkEmpty() && tvMepet?.text != getString(R.string.edit_pet_data)) {
                val firstWeight = npBeratBadanUtama?.value.toString()
                val secondWeight = npBeratBadanSekunder?.value.toString()
                vm.insertData(et_petname?.text.toString(), encodedImage,cbx_pettype?.selectedItem.toString(), et_age?.text.toString().toInt(), ("$firstWeight.$secondWeight").toFloat())
                toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home)
            }
        }
        ivProfileAdd.setOnClickListener {
            pickImageFromGalerry()
        }
        edit_btn.setOnClickListener {
            pickImageFromGalerry()
        }
    }
    private fun pickImageFromGalerry() {
    //intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGalerry()
                }
                else{
                    Toast.makeText(context, "Gallerry access denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            ivProfileAdd.setImageURI(data?.data)

            val pickedImage: Uri? = data?.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            val cursor:Cursor? = pickedImage?.let {
                requireContext().contentResolver?.query(it,filePath,null,null,null)
            }
            cursor?.moveToFirst()
            val imagePath = cursor?.getString(cursor.getColumnIndex(filePath[0]))
            cursor?.close()

            val bitmap = BitmapFactory.decodeFile(imagePath)
            val resizedBitmap = BitmapUtility.getResizedImage(bitmap,500)

            encodedImage = BitmapUtility.getEncodedImage(resizedBitmap)

        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        val sf = fragmentManager?.beginTransaction()
        sf?.setCustomAnimations(R.anim.enter, R.anim.exit)?.replace(R.id.fragment, fragment)?.commit()
        sf?.addToBackStack(null)
        fab1?.hide()
        activity?.tvMepet?.text = title
        activity?.nav_view?.setCheckedItem(item)
    }

    private fun editSet(arguments: Bundle?) {
        val dbManager = MepetDatabaseHelper(context)
        val id = arguments?.getInt(ARGUMENTS_ID_KEY)
        if (id != null) {
            val detailProfile = dbManager.getPetById(id)
            detailProfile?.let {
                et_petname?.setText(it.petName)
                et_age?.setText(it.petAge.toString())
                cbx_pettype?.setSelection(getCbxIndex(it.petType))
                val beratFirst = it.petWeight.toString().split(".")[0].toInt()
                val beratKedua = it.petWeight.toString().split(".")[1].toInt()
                npBeratBadanUtama?.value = beratFirst
                npBeratBadanSekunder?.value = beratKedua
            }
            btnAddPet?.text = getString(R.string.update)
        }
    }

    private fun setNpValue() {
        npBeratBadanUtama.minValue = 1
        npBeratBadanUtama.maxValue = 18
        npBeratBadanSekunder.minValue = 0
        npBeratBadanSekunder.maxValue = 9
    }

    private fun getCbxIndex(type: String): Int {
        for (i in 0 until cbx_pettype.count) {
            if (cbx_pettype.getItemAtPosition(i) == type) {
                return i
            }
        }
        return 0
    }

    private fun checkEmpty(): Boolean {
        if (et_petname.text?.isEmpty() == true) {
            et_petname.error = getString(R.string.warning_name)
        }
        if (et_age.text?.isEmpty() == true) {
            et_age.error = getString(R.string.warning_age)
        } else {
            return true
        }
        return false
    }
}

