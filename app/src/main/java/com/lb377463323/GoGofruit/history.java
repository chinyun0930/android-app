package com.lb377463323.GoGofruit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class history extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;

    private LinkedList<HashMap<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

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
        for(int i=0;i<100;i++){
            HashMap<String,String> row = new HashMap<>();
            int random = (int)(Math.random()*100);
            row.put("fruit","apple"+i);
            row.put("kcal","卡路里: "+random+" (kcal)");
            row.put("vitaminA","維他命A: "+random+" (I.U.)");
            row.put("vitaminC","維他命C: "+random+" (mg)");
            row.put("vitaminE","維他命E: "+random+" (mg)");
            row.put("fiber","膳食纖維: "+random+" (g)");
            row.put("sugar","碳水化合物: "+random+" (g)");
            row.put("date",""+ft.format(time));
            data.add(row);
        }
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
                    .inflate(R.layout.item2, parent, false);
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


    public void test2(View view){
        data.removeFirst();
        myAdapter.notifyDataSetChanged();
    }
}
