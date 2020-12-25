package com.example.weixinapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class VideoActivity extends Activity implements View.OnClickListener{
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.page_news);

        videoView = findViewById(R.id.video_view);

        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button replay = findViewById(R.id.replay);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(VideoActivity.this,Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions (VideoActivity.this,new String[]{
                    Manifest. permission. WRITE_EXTERNAL_STORAGE },1);
        }else{
            initVideoPath();//初始化MediaPlayer
            }
        }
    private void initVideoPath(){
        File file = new File(Environment.getExternalStorageDirectory(),"Music/movie.mp4");
        videoView.setVideoPath(file.getPath());//指定视频文件的路径
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,
                                           int[] grantResults) { switch (requestCode) {
        case 1:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.
                    PERMISSION_GRANTED){
                initVideoPath();
            } else {
                Toast.makeText(this,"拒绝权限将无法使用程序", Toast.LENGTH_SHORT).
                        show();
                finish();
            }
            break;
            default:
}
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (! videoView.isPlaying ()) {
                    videoView.start(); //开始播放
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause(); //暂停播放
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                    videoView.start(); //重新播放
                    break;
                }
            }
        }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy ();
//        if (videoView != null) {
//            videoView.suspend();
//        }
//    }
}
