package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        createNotificationChannel()

        binding.button.setOnClickListener { // 버튼을 누르면 알림이 표시됨
            showNotification()
        }
    }

    private val channelID = "normal"

    private fun createNotificationChannel() { // 알림을 만드는 함수
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID, "default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "description text of this channel."
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private var count = 1
    private fun counter() = count++

    private fun showNotification() { // 알림을 표시하는 함수
        /*
        with 사용시
        val builder = with (NotificationCompat.Builder(this, channelID)) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("Test")
        }
        NotificationManagerCompat.from(this).notify(1, builder.build())
         */
        val builder = NotificationCompat.Builder(this, channelID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Test")
                    .setContentTitle("Button Click: ${counter()}") // 버튼을 누른 횟수를 표시 (버튼을 누를 때마다 숫자가 1씩 증가)
        NotificationManagerCompat.from(this).notify(1, builder.build())
    }

}