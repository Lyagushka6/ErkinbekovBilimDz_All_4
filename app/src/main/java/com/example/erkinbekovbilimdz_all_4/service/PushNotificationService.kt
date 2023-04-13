package com.example.erkinbekovbilimdz_all_4.service
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.example.erkinbekovbilimdz_all_4.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {


    @SuppressLint("NewApi", "MissingPermission")
    override fun onMessageReceived(message: RemoteMessage) {
        val channel = NotificationChannel (ID_CHANNEL,
        "Notification",
        NotificationManager.IMPORTANCE_HIGH)

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, ID_CHANNEL)
        notification.setSmallIcon(R.drawable.ic_google)
            notification.setContentTitle(message.notification?.title)
            notification.setContentText(message.notification?.body)
            notification.setAutoCancel(true)
            NotificationManagerCompat.from(this).notify(1, notification.build())

        super.onMessageReceived(message)
    }
    companion object{
        val ID_CHANNEL = "id_channel"
    }
}