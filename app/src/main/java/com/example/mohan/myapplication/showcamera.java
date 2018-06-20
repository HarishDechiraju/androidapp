package com.example.mohan.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class showcamera extends SurfaceView implements SurfaceHolder.Callback{
    private android.hardware.Camera camera;
    private SurfaceHolder holder;
    public showcamera(Context context, android.hardware.Camera camera) {
        super(context);
        this.camera= camera;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        android.hardware.Camera.Parameters params=camera.getParameters();
        if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE){
            params.set("orientation","portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }
        else{
            params.set("orientation","landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
