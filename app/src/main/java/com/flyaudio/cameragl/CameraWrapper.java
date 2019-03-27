package com.flyaudio.cameragl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wehao on 19-3-27.
 */

public class CameraWrapper {
    private static final String TAG = "CameraWrapper";

    Camera mCamera;
    Camera.Parameters mParameters;
    int mWidth;
    int mHeight;
    public CameraWrapper() {

    }

    public void open(int id) {
        mCamera = Camera.open(id);
        mParameters = mCamera.getParameters();
        List<Camera.Size> supportSizes = mParameters.getSupportedPreviewSizes();
        Collections.sort(supportSizes, new PreviewSizeComp());
        for (Camera.Size size : supportSizes) {
            Log.d(TAG, "preview size width: " + size.width + " height: " + size.height);
        }
        mWidth = supportSizes.get(0).width;
        mHeight = supportSizes.get(0).height;
        Log.e(TAG, "set PreviewSize " + mWidth + " " + mHeight);
        mParameters.setPreviewSize(mWidth, mHeight);
        mCamera.setParameters(mParameters);
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        Log.e(TAG, "setSurfaceTexture: " + surfaceTexture);
        try {
            mCamera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPreview() {
        Log.e(TAG, "startPreview: ");
        mCamera.setPreviewCallback(null);
        mCamera.startPreview();
    }

    public void stopPreview() {
        Log.e(TAG, "stopPreview: ");
        mCamera.stopPreview();
    }

    public void close() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.release();
        }
    }

    class PreviewSizeComp implements Comparator<Camera.Size> {
        @Override
        public int compare(Camera.Size o1, Camera.Size o2) {
            return o1.width == o2.width? 0 : o1.width < o2.width? 1 : -1;
        }
    }
}
