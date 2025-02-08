package com.medunnes.telemedicine.ui.notification

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.medunnes.telemedicine.ui.main.MainActivity


class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FirebaseService", "New Token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FirebaseService", "From: ${remoteMessage.from}")


        // Jika notif berisi data
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FirebaseService", "Message Data Payload: ${remoteMessage.data}")
            val title = remoteMessage.data["title"] ?: "New Message"
            val body = remoteMessage.data["body"] ?: "You have received a new message"

            //Tampilkan Notif
            showNotification(title,body, null)
        }
    }

    private fun showNotification(title: String, body: String, data: Map<String, String>?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

    }
}
