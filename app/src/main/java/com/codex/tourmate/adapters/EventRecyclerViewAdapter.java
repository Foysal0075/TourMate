package com.codex.tourmate.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.codex.tourmate.AddEventActivity;
import com.codex.tourmate.R;
import com.codex.tourmate.event_class.EventInfo;

import java.util.ArrayList;
import java.util.List;


public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder>{

    private List<EventInfo> eventList;
    private Context context;

    public EventRecyclerViewAdapter(Context context, List<EventInfo> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_view_model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.budget.setText(eventList.get(position).getEventBudget());
        holder.from.setText(eventList.get(position).getFromDate());
        holder.to.setText(eventList.get(position).getToDate());
        holder.destination.setText(eventList.get(position).getEventDestination());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                EventInfo eventInfo = eventList.get(position);

                PopupMenu popupMenu = new PopupMenu(context,holder.itemView);
                popupMenu.inflate(R.menu.event_up_del_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.update_event :
                                Intent intent = new Intent(context, AddEventActivity.class);
                                context.startActivity(intent);
                                break;
                            case R.id.delete_event :
                                //Do database delet code
                                break;

                        }
                        return false;
                    }
                });

                popupMenu.show();
                return false;
            }
        });




    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView destination, from, to, budget;

        public MyViewHolder (View itemView){
            super(itemView);

            destination = itemView.findViewById(R.id.event_destination_view);
            from = itemView.findViewById(R.id.from_date_view);
            to = itemView.findViewById(R.id.to_date_view);
            budget = itemView.findViewById(R.id.budget_view);

        }

    }
}
