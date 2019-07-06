package com.example.administrator.myactivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG="HCX";
    private Button bt;
    private TextView textView;

    private MediaPlayer mediaPlayer;
    private int position;


    /**
     * 保持当前状态信息
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.d(TAG, "onSaveInstanceState: --MainActivity");
        super.onSaveInstanceState(outState);
        outState.putString("name","hcx");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        bt = findViewById(R.id.button1);
        bt.setOnClickListener(this);

        if (savedInstanceState!=null){
            textView.setText(savedInstanceState.getString("name"));
        }

        Context context;
        //后台播放音乐
        mediaPlayer=MediaPlayer.create(this,R.raw.love);
        mediaPlayer.start();

        Log.d(TAG, "onCreate: --MainActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: --MainActivity");
    }

    @Override
    protected void onResume() {
        //当音乐的位置不为零，即正在播放时，跳转到上次暂停的位置，继续播放音乐
        if (position!=0){
            Log.d(TAG, "onResume: --position"+position);
            mediaPlayer.seekTo(position);
            mediaPlayer.start();
        }
        super.onResume();
        Log.d(TAG, "onResume: --MainActivity");
    }

    @Override
    protected void onPause() {

        Log.d(TAG, "onPause: --MainActivity");
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            position=mediaPlayer.getCurrentPosition();
            Log.d(TAG, "onPause: position"+position);
        }
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: --MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: --MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: --MainActivity");
        if (mediaPlayer!=null){
            mediaPlayer.release(); //释放资源
            mediaPlayer=null; //释放后置空
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);

    }
}
