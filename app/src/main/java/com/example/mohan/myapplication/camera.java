package com.example.mohan.myapplication;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class camera extends AppCompatActivity {
    Camera camera;
    FrameLayout frameLayout;
    showcamera showCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        frameLayout=(FrameLayout)findViewById(R.id.frame_layout);
        camera=Camera.open();
        showCamera=new showcamera(this,camera);
        frameLayout.addView(showCamera);


    }
    Camera.PictureCallback mPictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            File picture_file=getOutputMediaFile();
            if(picture_file==null){
                return;
            }
            else{
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    try{fos.write(bytes);
                    fos.close();
                        camera.startPreview();}
                    catch (IOException e){
                        e.printStackTrace();
                    }

                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    };
    private File getOutputMediaFile(){
        String state= Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            return  null;

        }
        else{
            File folder_gui=new File(Environment.getExternalStorageDirectory()+File.separator+"GUI");
            if(!folder_gui.exists()){
                folder_gui.mkdirs();
            }
            File outputFile=new File(folder_gui,"temp.jpg");
            return  outputFile;
        }
    }
    public void captureImage(View view){
        if(camera!=null){
            camera.takePicture(null,null,mPictureCallback);
        }
    }

}
