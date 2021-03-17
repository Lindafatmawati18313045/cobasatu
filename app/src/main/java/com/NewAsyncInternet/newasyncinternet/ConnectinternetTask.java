package com.NewAsyncInternet.newasyncinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectinternetTask extends AsyncTask<String,Void,String> {

    Context ctx;

    public ConnectinternetTask(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;

        try {
            URL myurl = new URL(s1);
            HttpURLConnection myconn = (HttpURLConnection) myurl.openConnection();
            myconn.setReadTimeout(10000);
            myconn.setConnectTimeout(20000);
            myconn.setRequestMethod("GET");
            myconn.connect();

            in = myconn.getInputStream();

            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String Line="";

            while ((Line = myBuf.readLine()) != null){
                st.append(Line+" \n");
            }

            myBuf.close();
            in.close();

            return  st.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
       MainActivity.myText.setText(s);
    }
}
