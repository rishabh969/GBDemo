package com.gb.demo.networking;

/*Rishabh*/

import android.os.AsyncTask;

import com.gb.demo.BuildConfig;
import com.gb.demo.ui.home.HomeView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebService extends AsyncTask<String, Void, String> {

    private HomeView mContext;
    private NetworkApiCallback onTaskDoneListener;


    public WebService(HomeView context, NetworkApiCallback onTaskDoneListener) {
        this.mContext = context;
        this.onTaskDoneListener = onTaskDoneListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            URL mUrl = new URL(BuildConfig.BASEURL);
            HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);

            httpConnection.connect();

            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (onTaskDoneListener != null && s != null) {
            onTaskDoneListener.onSuccess(s);
        } else
            onTaskDoneListener.onError(s);
    }


    public interface NetworkApiCallback{
        void onSuccess(String  successResponse);
        void onError(String errorResponse);
    }
}


