package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<GetSet> getset;

    public MyAdapter(Context c , ArrayList<GetSet> g){
        context = c;
        getset = g;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int a) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int a) {
        holder.title.setText(getset.get(a).getTitle());
        holder.plan.setText(getset.get(a).getPlan());
        holder.time.setText(getset.get(a).getTime());

        final String title = getset.get(a).getTitle();
        final String plan = getset.get(a).getPlan();
        final String time = getset.get(a).getTime();
        final String key = getset.get(a).getKey() ;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(context, EditItem.class);
                move.putExtra("title", title);
                move.putExtra("plan", plan);
                move.putExtra("time", time);
                move.putExtra("key", key);
                context.startActivity(move);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getset.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, plan, time;
        public MyViewHolder(@NonNull View itemview)
        {
            super(itemview);
            title = (TextView) itemview.findViewById(R.id.title);
            plan = (TextView) itemview.findViewById(R.id.plan);
            time = (TextView) itemview.findViewById(R.id.time);
        }
    }
}
