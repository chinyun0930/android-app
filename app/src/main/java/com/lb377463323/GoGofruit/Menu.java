package com.lb377463323.GoGofruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    ImageButton historyButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //historyButton = (ImageButton) findViewById(R.id.history);
        startButton = (ImageButton) findViewById(R.id.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this, MainActivity.class);
                startActivity(intent);
            }
        });
     /*   historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this, history.class);
                startActivity(intent);
            }
        });*/
    }

}
