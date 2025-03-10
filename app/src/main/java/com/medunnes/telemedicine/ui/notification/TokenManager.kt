package com.medunnes.telemedicine.ui.notification

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class TokenManager(private val firestore: FirebaseFirestore) {

    fun sendTokenToServer(userId: String, fcmToken: String?) {
        if (fcmToken != null) {
            val deviceToken = hashMapOf(
                "fcmToken" to fcmToken,
                "timestamp" to FieldValue.serverTimestamp()
            )
            firestore.collection("fcmToken").document(userId)
                .set(deviceToken)
                .addOnSuccessListener {
                    Log.d("FCM", "token berhasil disimpan")
                }
                .addOnFailureListener { e ->
                    Log.e("FCM", "Gagal simpan di firestore")
                }
        } else {
                Log.e("FCM", "FCM token is null")
            }
        }
    }
