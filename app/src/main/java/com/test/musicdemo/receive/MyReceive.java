package com.test.musicdemo.receive;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.test.musicdemo.R;
import com.test.musicdemo.activity.Main_activity;
import com.test.musicdemo.service.MyService;
import com.test.musicdemo.util.MyConst;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MyReceive extends BroadcastReceiver{

    public static String ACTION_DATACOME = "HEHEHEH";
    public static String ACTION_SEEKBARCHANGE_BYUSER = "DDDDHEHEHEH";
    public static String ACTION_PLAYTIME_CHANGED = "DDDDHEHEHJJJEH";
    public static String EXETER_PLAYTIME_CHANGED_CONTEXT = "JJEH";


    public  MyReceive(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(ACTION_DATACOME.equals(action)) {
            Toast.makeText(context, "数据准备完成!!", Toast.LENGTH_SHORT).show();
        }else if(ACTION_PLAYTIME_CHANGED.equals(action)){
            String title = intent.getStringExtra(MyConst.MUSIC_TITLE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MyService.mapp_context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("简音乐正在播放")
                    .setContentText("" + title);
            Intent resultIntent = new Intent(MyService.mapp_context, Main_activity.class);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MyService.mapp_context);
            taskStackBuilder.addParentStack(Main_activity.class);
            taskStackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

            NotificationManager nm = (NotificationManager) MyService.mapp_context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(Main_activity.NOTIFY_1,mBuilder.build());
        }else if(ACTION_SEEKBARCHANGE_BYUSER.equals(action)){

        }
    }
}
