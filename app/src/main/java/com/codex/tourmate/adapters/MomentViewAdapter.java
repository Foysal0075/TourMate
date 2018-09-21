package com.codex.tourmate.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.codex.tourmate.AddMomentActivity;
import com.codex.tourmate.R;
import com.codex.tourmate.event_class.EventInfo;
import com.codex.tourmate.event_class.Moments;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MomentViewAdapter extends RecyclerView.Adapter<MomentViewAdapter.MomentViewHolder>{

    private List<Moments> momentsList;
    private Activity context;

    public MomentViewAdapter(List<Moments> momentsList, Activity context) {
        this.momentsList = momentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MomentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.moment_view_model,parent,false);
        return new MomentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MomentViewHolder holder, final int position) {

        holder.captionView.setText(momentsList.get(position).getMomentCaption());
        holder.momentImageView.setImageBitmap(momentsList.get(position).retrieveMomentImage());
        holder.momentDateView.setText(momentsList.get(position).getMomentdateTime());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final Moments moment = momentsList.get(position);

                PopupMenu popupMenu = new PopupMenu(context,holder.itemView);
                popupMenu.inflate(R.menu.event_up_del_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        EventInfo eventInfo = (EventInfo)context.getIntent().getSerializableExtra("expensefromevent");

                        switch (item.getItemId()){

                            case R.id.delete_event :
                                //DO firebase code
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                DatabaseReference rootReference= FirebaseDatabase.getInstance().getReference();
                                rootReference.child("user").child(user.getUid()).child("moment").child(moment.getMomentKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "Moment Deleted", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                break;
                            case R.id.update_event :


                                Intent intent = new Intent(context, AddMomentActivity.class);
                                intent.putExtra("updatemoment",moment);
                                intent.putExtra("fromadapter",eventInfo);
                                //intent.putExtra("updateMomentState","update");
                                context.startActivity(intent);
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
        return momentsList.size();
    }

    public class MomentViewHolder extends RecyclerView.ViewHolder{

        TextView captionView,momentDateView;
        ImageView momentImageView;

        public MomentViewHolder(View itemView) {
            super(itemView);
            momentImageView = itemView.findViewById(R.id.moment_picture_view);
            momentDateView = itemView.findViewById(R.id.moment_date_view);
            captionView = itemView.findViewById(R.id.moment_caption_view);

        }
    }
}
