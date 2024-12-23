package com.medunnes.telemedicine.utils

const val baseIpAddress = "10.0.2.2:8000"
fun imageBaseUrl(): String = "http://$baseIpAddress/storage/userImage"