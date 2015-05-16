package com.example.piyu004.integrationandee;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class MainActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static Uri mImageCaptureUri1;
    private Bitmap imgBitmap;
    ImageView iv;
    TextView tv;
    FaceGrabber fg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //faces = (TextView) fi
        // force landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.faceNums);
        // fire camera
        iv = (ImageView) findViewById(R.id.imageView1);
    }

    // private void dispatchTakePictureIntent() {
    // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
    // startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    // }
    //
    // }

    public void getPix(View v) {
        if (imgBitmap != null) {

            fg = new FaceGrabber(imgBitmap);
            // get # faces

            tv.setText("faces=" + fg.getFaceNum());

            // test
           iv.setImageBitmap(fg.getFaceBitmap()[0]);

            //final Upload server = new Upload(fg.getFaceBitmap()[0]);
            //String baseEncode =  server.encodeTobase64();
           // server.testPost();
            Toast.makeText(getApplicationContext(), "after Post", Toast.LENGTH_LONG).show();
            // face rec image

            // grab faces
            // scale
            // black and white
            // normalize

            // display images
            // iv.setImageBitmap(imgBitmap);
        } else {
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG)
                    .show();
        }

    }
public void openServer(View v){
    Intent i = new Intent(this, Upload.class);
    //startActivityForResult(i, 1);
    i.putExtra("priya",fg.encodeToBase64(fg.getFaceBitmap()[0]));
    startActivity(i);

}
    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://52.11.86.64/api/find-face.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("image", fg.encodeToBase64(fg.getFaceBitmap()[0])));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            tv.setText("in try");
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            tv.setText("in catch 1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            tv.setText("in catch 2");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Bundle extras = data.getExtras();
            // imgBitmap = (Bitmap) extras.get("data");

            try {
                imgBitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), mImageCaptureUri1);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void takePic(View v) {
        // dispatchTakePictureIntent();
        openCamera();
    }

    public void openCamera() {

        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        mImageCaptureUri1 = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "tmp_avatar_"
                + String.valueOf(System.currentTimeMillis()) + ".jpg"));

        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImageCaptureUri1);
        cameraIntent.putExtra("return-data", true);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

}
