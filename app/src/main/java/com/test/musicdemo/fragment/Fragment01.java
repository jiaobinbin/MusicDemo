package com.test.musicdemo.fragment;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.test.musicdemo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Fragment01 extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    private AudioManager am; //音频管理器
    private RelativeLayout rlPlayerVoice; //音量控制面板
    int currentVoice;
    int maxVoice;
    ImageButton ibPlayerVoice; //显示音量控制面板
    SeekBar sbPlayerVoice;

    //音量面板显示和隐藏的动画
    private Animation showPlayerVoice;
    private Animation hiddenPlayerVoice;

    ListView listView;
    EditText edSearch;

    private static ViewGroup viewGroup = null;
    public static ArrayList<HashMap<String,String>> listdata = null;

    String mytitle = null;

    public static LayoutInflater minflater = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        minflater = inflater;
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment01,container,false);
        listView = (ListView) viewGroup.findViewById(R.id.lvList);
        listView.setOnItemClickListener(this);
        init(viewGroup);
        listdata = new ArrayList<HashMap<String, String>>();

        return viewGroup;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ibOpenVoice: voicePanelAnimation();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void init(ViewGroup group){
        ibPlayerVoice = (ImageButton) group.findViewById(R.id.ibOpenVoice);
        rlPlayerVoice = (RelativeLayout) group.findViewById(R.id.rlPlayVoice);
        sbPlayerVoice = (SeekBar) group.findViewById(R.id.sbPlayerVoice);
        ibPlayerVoice.setOnClickListener(this);
        sbPlayerVoice.setOnSeekBarChangeListener(new SeekBarChangerListener());
        Button btnPrevious = (Button) viewGroup.findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);
        Button btnPlay = (Button) viewGroup.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        Button btnNext = (Button) viewGroup.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        Button playList = (Button) viewGroup.findViewById(R.id.btnPlayerList);
        playList.setOnClickListener(this);

    }

    private class SeekBarChangerListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try{

            showPlayerVoice = AnimationUtils.loadAnimation(getActivity(),R.anim.push_up_in);
            hiddenPlayerVoice = AnimationUtils.loadAnimation(getActivity(),R.anim.push_up_out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void voicePanelAnimation(){
        try{
            if(rlPlayerVoice.getVisibility() == View.GONE){
                rlPlayerVoice.startAnimation(showPlayerVoice);
                rlPlayerVoice.setVisibility(View.VISIBLE);
            }else{
                rlPlayerVoice.startAnimation(hiddenPlayerVoice);
                rlPlayerVoice.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
