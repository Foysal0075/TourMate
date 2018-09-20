package com.codex.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codex.tourmate.R;
import com.codex.tourmate.event_class.Moments;

import java.util.List;

public class MomentViewAdapter extends RecyclerView.Adapter<MomentViewAdapter.MomentViewHolder>{

    private List<Moments> momentsList;
    private Context context;

    public MomentViewAdapter(List<Moments> momentsList, Context context) {
        this.momentsList = momentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MomentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MomentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
