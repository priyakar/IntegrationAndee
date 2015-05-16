package com.example.piyu004.integrationandee;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piyu004 on 5/15/2015.
 */
public class UploadToServer extends ActionBarActivity{
    private String ipAddress = "52.11.86.64";
    TextView setResult;
    private int portNumber = 80;
    private int timeOut = 10;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        setResult = (TextView)findViewById(R.id.textView);
    }
    //       encodeTobase64();

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == 1) {
        if(resultCode == RESULT_OK){
            //String result=data.getStringExtra("result");
            tv.setText("Result OK");
        }
        if (resultCode == RESULT_CANCELED) {
            //Write your code if there's no result
            tv.setText("result cancelled");
        }
    }
}
*/
   /* public void sendImageforResult (String[] a){
        PingServerForResult p = new PingServerForResult(ipAddress,portNumber,timeOut);
        if(p.isPortOpen()){

        }
    }*/
    public UploadToServer(){

    }
    private Bitmap b = null;
    private Context context;
    String result = null;
    public UploadToServer (Bitmap bit, Context c){
        this.b = bit;
        this.context = c;
    }
    public String encodeTobase64()
    {
        Log.i("LOOK", "in encode");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);

        System.out.println(imageEncoded);
        return imageEncoded;
    }

    public void testPost(){

        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://52.11.86.64/api/find-face.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("priya", encodeTobase64());


        JSONObject json = new JSONObject(params);
        //Toast.makeText(context, json.toString(), Toast.LENGTH_LONG).show();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            String re = response.toString();
                           // result = re;
                            setResult.setText(re);
                            Toast.makeText(context,re,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                            result = "error";
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }

        });
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

// add the request object to the queue to be executed
        queue.add(req);


    }



}
