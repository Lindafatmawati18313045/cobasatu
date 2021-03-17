package com.NewAsyncInternet.newasyncinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectinternetTask c1;
    DownloadImageTask downloadImage;

    static TextView myText;
    static ImageView myImage;

    ConnectivityManager myconnManager;
    NetworkInfo myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.myResult);
        myImage = (ImageView)findViewById(R.id.myimageresult);

        myconnManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        myInfo = myconnManager.getActiveNetworkInfo();
    }

    public void doSomething(View view) {
        c1 = new ConnectinternetTask(this);
        c1.execute("view-source:https://www.google.com/");
    }

    public void downloadImage(View view) {
        if (myInfo != null && myInfo.isConnected()){
            downloadImage =new DownloadImageTask();
            downloadImage.execute("https://images2.alphacoders.com/433/433637.jpg");
        }
        else {
            Toast.makeText(this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
        }
    }
}