package com.example.gifloaderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.gifloaderapplication.core.GifDecoder;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    public static String url = "https://gddev.highlight2018.com/virtual/scene/1/photo/speaking.gif";
    public static String url1 = "https://gddev.highlight2018.com/virtual/scene/1/photo/question.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        try {
            GifDecoder.with(this).load(url).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
