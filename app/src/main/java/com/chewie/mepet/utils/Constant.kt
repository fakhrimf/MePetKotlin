package com.chewie.mepet.utils

const val ID_PROFILE = "ID_PROFILE"
const val JAM_PROFILE = "JAM_PROFILE"
const val JAM_PAGI_KEY = "jamPagi"
const val JAM_SIANG_KEY = "jamSiang"
const val JAM_MALAM_KEY = "jamMalam"
const val PROFILE_ID_KEY = "profile_id"
const val PCID = "primary_notification_channel"
const val NOTIFICATION_CHANNEL_NAME = "Reminder Notification"
const val CHANNEL_DESC = "Reminds you to feed your Pet"
const val FRAGMENT_INTENT_KEY = "fragment"
const val ARGUMENTS_ID_KEY = "id"

const val DB_NAME = "db_mepet"
const val DB_VER = 1

const val DETAIL_PROFILE_TABLE = "detail_profile"
const val PROFILE_TABLE = "profile"

const val PET_INTENT_KEY = "pet_data"
const val TIPS_INTENT_KEY = "tips_data"

//Tabel Detail Profile
const val ID_DETAIL_PROFILE = "idDetailProfile"
const val PET_IMAGE = "gambar_hewan"
const val PET_NAME = "nama_hewan"
const val PET_TYPE = "jenis_hewan"
const val PET_AGE = "umur_hewan"
const val PET_WEIGHT = "berat_hewan"

const val FK_ID_DETAIL_PROFILE = "idDetailProfile"
const val JAM_PAGI = "jamPagi"
const val JAM_SIANG = "jamSiang"
const val JAM_MALAM = "jamMalam"

//FIREBASE NAME
const val REF_NAME_TIPS = "tips"
const val REF_NAME_PET = "pet"
const val REF_NAME_NAME = "name"

//Reminder
const val VALUE_PAGI = "pagi"
const val VALUE_SIANG = "siang"
const val VALUE_MALAM = "malam"

//Shop
const val LINK_TOKOPEDIA = "https://tokopedia.com"
const val LINK_BUKALAPAK = "https://bukalapak.com"
const val LINK_SHOPEE = "https://shopee.com"

const val IMAGE_PICK_CODE = 42069
const val PERMISSION_CODE = 696969

//Intro
const val FIRST_RUN_KEY = "first_run_key"

//References Adapter
enum class Adapter {
    REFERENCES_PET,REFERENCES_TIPS
}