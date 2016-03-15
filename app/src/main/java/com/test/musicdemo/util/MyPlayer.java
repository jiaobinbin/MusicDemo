package com.test.musicdemo.util;

        import android.media.MediaPlayer;

        import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MyPlayer {
    private MediaPlayer mediaPlayer;
    private String mplayfilename = "";
    public MyPlayer(){
        mediaPlayer = new MediaPlayer();
    }

    public void Play(String path){

        mplayfilename = path;
        try{

            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Play(){

        try{
            mediaPlayer.start();
        }catch (Exception e){
            try{
                mediaPlayer.reset();
                mediaPlayer.setDataSource(mplayfilename);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    public void Pause(){
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getPlayerTime(){
        int i = 10000;
        try{
            i = mediaPlayer.getCurrentPosition();

        }catch (Exception e){

            //e.printStackTrace();
            initPlayer(mplayfilename);
            i = mediaPlayer.getCurrentPosition();
        }
        return i;
    }
    public void initPlayer(String path){
        try{
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean isplaying(){
        boolean b = false;
        try{
            b = mediaPlayer.isPlaying();

        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }

    public void myseekto(int ms){
        try{
            mediaPlayer.seekTo(ms);

        }catch(Exception e){
            //e.printStackTrace();
            initPlayer(mplayfilename);
            mediaPlayer.seekTo(ms);
        }
    }
    public void release(){

        mediaPlayer.release();
    }
    public int GetPlayerTime(){
        int i = 10000;
        try{
            i = mediaPlayer.getCurrentPosition();
        }catch (Exception e){
          //  e.printStackTrace();
            initPlayer(mplayfilename);
            i = mediaPlayer.getCurrentPosition();
        }
        return  i;
    }
}
