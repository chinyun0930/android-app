package com.lb377463323.GoGofruit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class show extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;
    private ImageView myImage;
    String answer1;
    private LinkedList<HashMap<String,String>> data;


    File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+"/CameraV1");
    File imgFileOrig =  new File(directory, "IMG_123.jpg");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        answer1 = intent.getStringExtra(Main2Activity.answer);
        System.out.println(answer1);


        setContentView(R.layout.show);
        if(imgFileOrig.exists()){
            myImage = (ImageView) findViewById(R.id.image);
            myImage.setImageDrawable(Drawable.createFromPath(imgFileOrig.toString()));
        }

        recyclerView = (RecyclerView) findViewById((R.id.RecyclerView));
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        doData();
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
    }

    private void doData(){
        data = new LinkedList<>();
        Date time = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:s a");
      //  for(int i=0;i<100;i++){
            HashMap<String,String> row = new HashMap<>();
            int random = (int)(Math.random()*100);
            String[] tokens = answer1.split(";");
            row.put("fruit",tokens[0]);
            row.put("kcal"," 卡路里: "+tokens[1]+" (kcal)");
            row.put("vitaminA"," 維他命A: "+tokens[4]+" (I.U.)");
            row.put("vitaminC"," 維他命C: "+tokens[5]+" (mg)");
            row.put("vitaminE"," 維他命E: "+tokens[6]+" (mg)");
            row.put("fiber"," 膳食纖維: "+tokens[3]+" (g)");
            row.put("sugar"," 碳水化合物: "+tokens[2]+" (g)");
            row.put("date",""+ft.format(time));
            data.add(row);
       // }
    }
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        class MyViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView fruit, date, kcal, sugar, fiber, vitaminA, vitaminC, vitaminE;

            public MyViewHolder(View v) {
                super(v);
                itemView= v;

                fruit = (TextView) itemView.findViewById(R.id.item_fruit);
                kcal = (TextView) itemView.findViewById(R.id.item_kcal);
                vitaminA = (TextView) itemView.findViewById(R.id.item_vitaminA);
                vitaminC = (TextView) itemView.findViewById(R.id.item_vitaminC);
                vitaminE = (TextView) itemView.findViewById(R.id.item_vitaminE);
                fiber = (TextView) itemView.findViewById(R.id.item_fiber);
                sugar = (TextView) itemView.findViewById(R.id.item_sugar);
                date = (TextView) itemView.findViewById(R.id.item_date);
            }
        }


        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }


        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
            holder.fruit.setText(data.get(position).get("fruit"));
            holder.kcal.setText(data.get(position).get("kcal"));
            holder.vitaminA.setText(data.get(position).get("vitaminA"));
            holder.vitaminC.setText(data.get(position).get("vitaminC"));
            holder.vitaminE.setText(data.get(position).get("vitaminE"));
            holder.fiber.setText(data.get(position).get("fiber"));
            holder.sugar.setText(data.get(position).get("sugar"));

            holder.date.setText(data.get(position).get("date"));

            holder.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("brad","Click: "+ position);
                }
            });


        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }



}
