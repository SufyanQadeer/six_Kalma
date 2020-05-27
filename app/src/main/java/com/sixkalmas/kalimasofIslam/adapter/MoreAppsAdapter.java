package com.sixkalmas.kalimasofIslam.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sixkalmas.kalimasofIslam.MoreAppsModel;
import com.sixkalmas.kalimasofIslam.R;

import java.util.List;

public class MoreAppsAdapter extends RecyclerView.Adapter<MoreAppsAdapter.MyViewHolder> {

    public List<MoreAppsModel> moreAppsModelArrayList;
    Context mcontext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textview_title, textview_description;
        ImageView imageView_icon;
        RelativeLayout relativeLayout;

        public MyViewHolder(View view) {

            super(view);
            textview_title = view.findViewById(R.id.title);
            textview_description = view.findViewById(R.id.descriptionText);
            imageView_icon = view.findViewById(R.id.iv_icon);
            relativeLayout = view.findViewById(R.id.layout_relative);
        }

    }


    public MoreAppsAdapter(Context context, List<MoreAppsModel> moreAppsModelList) {

        this.mcontext = context;
        this.moreAppsModelArrayList = moreAppsModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_app, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.textview_title.setText(moreAppsModelArrayList.get(position).getTitle());
        holder.textview_description.setText(moreAppsModelArrayList.get(position).getDescription());
        Glide.with(mcontext).load(moreAppsModelArrayList.get(position).getIconLink()).into(holder.imageView_icon);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("" + moreAppsModelArrayList.get(position).getLink()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mcontext.startActivity(intent);

                } catch (ActivityNotFoundException e) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("" + moreAppsModelArrayList.get(position).getLink()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mcontext.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return moreAppsModelArrayList.size();
    }

}
