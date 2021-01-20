package com.badsoft.battitorch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static boolean on = true;
    String cameraId;
    CameraManager camManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.turnOn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (!on) {

                    String uri = "@drawable/turnoff";  //

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    on = true;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        cameraId = null;
                        try {
                            cameraId = camManager.getCameraIdList()[0];
                            camManager.setTorchMode(cameraId, true);
                            //Turn ON


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                    String uri = "@drawable/turnon";  //

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    on = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        cameraId = null;
                        try {
                            cameraId = camManager.getCameraIdList()[0];

                                camManager.setTorchMode(cameraId, false);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}