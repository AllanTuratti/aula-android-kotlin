package br.com.fernandosousa.lmsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat

object NotificationUtil {

    internal val CHANNEL_ID = "1"

    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val appName = context.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_DEFAULT)
            c.lightColor = Color.BLUE
            c.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            manager.createNotificationChannel(c)
        }
    }

    fun create(contexto: Context, id: Int, intent: Intent, titulo: String, texto: String) {
        val manager = contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Intent para disparar broadcast
        val p = PendingIntent.getActivity(contexto, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Cria notificação
        val builder = NotificationCompat.Builder(contexto, CHANNEL_ID)
                .setContentIntent(p)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)

        // disparar notificacao
        val n = builder.build()
        manager.notify(id, n)
    }
}