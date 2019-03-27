package com.flyaudio.cameragl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private CameraPreview mCameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mCameraPreview = (CameraPreview) findViewById(R.id.glsurface_camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraPreview != null) {
            mCameraPreview.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraPreview != null) {
            mCameraPreview.onPause();
        }
    }
}
