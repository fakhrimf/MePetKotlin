package com.chewie.mepet.home

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chewie.mepet.R
import com.chewie.mepet.db.MepetDatabaseHelper
import com.chewie.mepet.model.PetDetailProfile
import com.chewie.mepet.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_add_pet.*
import kotlinx.android.synthetic.main.fragment_home.*

class AddPetFragment : Fragment() {
    private lateinit var encodedImage: String
    private lateinit var pet: PetDetailProfile
    private lateinit var sharedPreference: SharedPreference

    private val vm by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(AddPetVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        encodedImage = ""
        sharedPreference = SharedPreference(requireContext())
        setNpValue()
        dialogOther()
        editSet(arguments)
        btnAddPet.setOnClickListener {
            if (checkEmpty()) {
                if (btnAddPet.text.equals(getString(R.string.add))){
                    val firstWeight = npBeratBadanUtama?.value.toString()
                    val secondWeight = npBeratBadanSekunder?.value.toString()
                    vm.insertData(et_petname?.text.toString(), encodedImage, cbx_pettype?.selectedItem.toString(), et_age?.text.toString().toInt(), ("$firstWeight.$secondWeight").toFloat())
                    toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home)
                }else{
                    val firstWeight = npBeratBadanUtama?.value.toString()
                    val secondWeight = npBeratBadanSekunder?.value.toString()
                    val petDetailProfile = PetDetailProfile(
                        sharedPreference.getId(),
                        encodedImage,
                        et_petname?.text.toString(),
                        cbx_pettype?.selectedItem.toString(),
                        et_age?.text.toString().toInt(),
                        ("$firstWeight.$secondWeight").toFloat()
                    )
                    vm.editData(petDetailProfile)
                    toFragment(HomeFragment(), getString(R.string.home), R.id.nav_home)
                }
            }
        }
        ivProfileAdd.setOnClickListener {
            pickImageFromGallery()
        }
        edit_btn.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
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
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(context, "Gallerry access denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            ivProfileAdd.setImageURI(data?.data)

            val pickedImage: Uri? = data?.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = pickedImage?.let {
                requireContext().contentResolver?.query(it, filePath, null, null, null)
            }
            cursor?.moveToFirst()
            val imagePath = cursor?.getString(cursor.getColumnIndex(filePath[0]))
            cursor?.close()

            val bitmap = BitmapFactory.decodeFile(imagePath)
            val resizedBitmap = BitmapUtility.getResizedImage(bitmap, 500)

            encodedImage = BitmapUtility.getEncodedImage(resizedBitmap)
        }
    }

    private fun toFragment(fragment: Fragment, title: String, item: Int) {
        @Suppress("DEPRECATION") val sf = fragmentManager?.beginTransaction()
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
                pet = it
                et_petname?.setText(it.petName)
                if (!it.petImage.isNullOrBlank()){
                    ivProfileAdd.setImageBitmap(BitmapUtility.getDecodedImage("${it.petImage}"))
                    val bitmap = BitmapUtility.getDecodedImage("${it.petImage}")
                    encodedImage = BitmapUtility.getEncodedImage(bitmap)
                }else{
                    ivProfile.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_cat))
                }
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

    fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Other Category")

        val view = layoutInflater.inflate(R.layout.inputtext_menu, null)

        val categoryEditText = view.findViewById(R.id.categoryEditText) as EditText

        builder.setView(view);

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val newCategory = categoryEditText.text
            var isValid = true
            if (newCategory.isBlank()) {
                categoryEditText.error = ("Tolong diisi")
                isValid = false
            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }
        builder.show();

    }

//    sulthon <3 nuy

    private fun dialogOther() {
        cbx_pettype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selected = p0?.getItemAtPosition(p2).toString()
                if (selected == "Other") {
                    showDialog()
                }
            }

        }

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

