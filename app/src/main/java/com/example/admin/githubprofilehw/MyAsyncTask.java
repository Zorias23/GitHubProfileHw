package com.example.admin.githubprofilehw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 11/15/2017.
 */
//String is parameters, Integer is for progress, String is for result
public class MyAsyncTask extends AsyncTask<String, Integer,Bitmap> {
    public static final String TAG = "MyAsyncTask";
    TextView textView;
    ImageView imageView;

    public MyAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

    //order of execution: onPreExecute, doInBackground, onPostExecute. doInBackground runs in its own thread
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: "+ Thread.currentThread());

        super.onPreExecute();
    }
    @Override
    protected Bitmap doInBackground(String... strings) { //arg is only String array, because we specified String as parameter
        Log.d(TAG, "doInBackground: "+ Thread.currentThread());
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


    //if I want onProgressUpdate to be called, i have to call publishProgress in doInBackground for it to be called, then it shows progress, the values I passed
    @Override
    protected void onProgressUpdate(Integer... values) { //Integer because we set progress to Integer
        Log.d(TAG, "onProgressUpdate: " + values[0] + " " + Thread.currentThread());

        super.onProgressUpdate(values);
    }
    //*** what doInBackground returns, onPostExecute receives as argument
    @Override
    protected void onPostExecute(Bitmap image) {
        Log.d(TAG, "onPostExecute: "+ Thread.currentThread());
        super.onPostExecute(image);
        imageView.setImageBitmap(image);
    }
}
