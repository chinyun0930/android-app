package com.lb377463323.GoGofruit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);


        GifImageView gifImageView1 = (GifImageView) findViewById(R.id.gif1);

        try {
// 如果載入的是gif動圖，第一步需要先將gif動圖資源轉化為GifDrawable
// 將gif圖資源轉化為GifDrawable
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.loading);
// gif1載入一個動態圖gif
            gifImageView1.setImageDrawable(gifDrawable);
// 如果是普通的圖片資源，就像Android的ImageView set圖片資源一樣簡單設定進去即可。
// gif2載入一個普通的圖片（如png，bmp，jpeg等等）

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
