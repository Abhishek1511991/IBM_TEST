package com.test.ibm_test


import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    lateinit var  navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun setSpecificfragment(type: Int)
    {
        if(type==2)
            navView.selectedItemId=R.id.navigation_dashboard
        else if(type==3)
            navView.selectedItemId=R.id.navigation_notifications
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val type=intent?.getIntExtra("type",0)
        navView.selectedItemId=R.id.navigation_home
        setSpecificfragment(type!!)

    }

    var backPressed=false;
    override fun onBackPressed() {
        if(navView.selectedItemId==R.id.navigation_home && !backPressed)
        {
            backPressed=true;
            Toast.makeText(this,"Press back to exit",Toast.LENGTH_LONG).show()
        }
        else
            super.onBackPressed()

    }






    @SuppressLint("WrongConstant")
    public fun addNotification(msg: String,type:Int) {
        val notificationManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "Test"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "My Notifications",
                NotificationManager.IMPORTANCE_MAX
            )
            // Configure the notification channel.
            notificationChannel.description = msg
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("type",type)
        val pi = PendingIntent.getActivity(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK)
        notificationBuilder.setContentIntent(pi)


        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setTicker("Test Notification")
            .setPriority(Notification.PRIORITY_MAX)
            .setContentText(msg)
        notificationManager.notify(1, notificationBuilder.build())

    }
}