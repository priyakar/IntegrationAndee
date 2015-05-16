package com.example.piyu004.integrationandee;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Upload extends ActionBarActivity {
TextView text;
    Bitmap b = null;
    String value = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        text = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
             value = extras.getString("priya");
        }


    }
    public Upload(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload, menu);
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
    public Upload(Bitmap images){
        this.b = images;
    }
    public String encodeTobase64()
    {
        Log.i("LOOK", "in encode");
        Toast.makeText(getApplicationContext(), "in encode", Toast.LENGTH_LONG).show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);

        System.out.println(imageEncoded);
        return imageEncoded;
    }
    public void testPost(){

       /* RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://52.11.86.64/api/find-face.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("image", value);


        JSONObject json = new JSONObject(params);
       // Toast.makeText(getApplicationContext(), json.toString(), Toast.LENGTH_LONG).show();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(5));
                            String re = response.toString();
                            // result = re;
                            text.setText(re);
                           // Toast.makeText(context, re, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            //Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                           // result = "error";
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }

        });
      //  Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

// add the request object to the queue to be executed
        queue.add(req);*/
      /*  URL url = new URL("");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            writeStream(out);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
                urlConnection.disconnect();
            }
*/


    }
   /* public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://52.11.86.64/api/find-face.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("image", value));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            text.setText("in try");
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            text.setText("in catch 1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            text.setText("in catch 2");
        }

    }*/
    public void te(View v){
        Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_LONG).show();
        //testPost();
        //postData();


    }


}
