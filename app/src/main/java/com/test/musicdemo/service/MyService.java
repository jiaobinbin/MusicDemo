package com.test.musicdemo.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.test.musicdemo.R;
import com.test.musicdemo.activity.Main_activity;
import com.test.musicdemo.receive.MyReceive;
import com.test.musicdemo.util.MyPlayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MyService extends Service{


    public static LocalBroadcastManager mlocal = null;
    MyReceive mr;
    public static Context mapp_context = null;
    public static MyPlayer mp = null;
    public static Thread mdatathread = null;
    public Thread mseekbarthread = null;
    public static ArrayList<HashMap<String,String>> mslistdata = new ArrayList<HashMap<String, String>>();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 通知的使用
         */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("简音乐启动").setContentText("未播放音乐");
        Intent resultIntent = new Intent(this, Main_activity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(Main_activity.class);
        taskStackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        startForeground(Main_activity.NOTIFY_1, mBuilder.build());

        mlocal = LocalBroadcastManager.getInstance(getApplicationContext());
        mr = new MyReceive();
        IntentFilter i = new IntentFilter();
        i.addAction(MyReceive.ACTION_DATACOME);
        i.addAction(MyReceive.ACTION_PLAYTIME_CHANGED);
        i.addAction(MyReceive.ACTION_SEEKBARCHANGE_BYUSER);
        mlocal.registerReceiver(mr, i);
        mapp_context = getApplicationContext();
        mp = new MyPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        getinitdatathread(getApplicationContext());
        getinitseekthread(getApplicationContext());

        if(null != mslistdata && mslistdata.size() > 0){
            Log.i("AAAAAAA",""+ mslistdata.get(0));
        }
        String action = intent.getAction();
        if(A)

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    static public Thread getinitdatathread(Context con){

        if(mdatathread == null){
            mdatathread = new mydataThread(con);
        }
        return mdatathread;
    }

     public Thread getinitseekthread(Context con){
        if(mseekbarthread == null){
            mseekbarthread = new myseekbarThread(con);
        }
        return mseekbarthread;
    }

    static public class mydataThread extends Thread{
        private Context mcont = null;
        public mydataThread(Context con){
            mcont = con;
            start();
        }

        @Override
        public void run() {
            super.run();
            try{
                if(mslistdata == null){
                    mslistdata = new ArrayList<HashMap<String,String>>();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    static public class myseekbarThread extends Thread{
        private Context mcon;
        public static Boolean isrun = true;
        public myseekbarThread(Context con){
            mcon = con;
            start();
        }
        @Override
        public void run() {
            super.run();
            while(true){
                try{
                    if(mp.isplaying() == true){
                        Message msg = Main_activity.mHandler.obtainMessage();
                        msg.arg1 = mp.GetPlayerTime();
                        msg.sendToTarget();
                    }
                    sleep(1000);

                }catch (Exception e){
                   // e.printStackTrace();
                    System.out.println("线程报错:myseekthread"+ e.getMessage());
                }

            }
        }
    }
}
