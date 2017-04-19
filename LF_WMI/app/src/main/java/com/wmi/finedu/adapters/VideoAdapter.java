package com.wmi.finedu.adapters;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wmi.finedu.R;
import com.wmi.finedu.models.VideoModel;

import java.util.List;

/**
 * Created by hle59 on 4/17/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private List<VideoModel> mLstVideos;
    private Handler mHandlerVideoSelected;

    public VideoAdapter(List<VideoModel> lstVideos, Handler handlerVideoSelected)
    {
        this.mLstVideos = lstVideos;
        mHandlerVideoSelected = handlerVideoSelected;
    }

    public void updateVideos(List<VideoModel> lstVideos)
    {
        this.mLstVideos = lstVideos;
        notifyDataSetChanged();
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item_layout, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        VideoModel video = mLstVideos.get(position);
        holder.mItemPhoto.setImageResource(video.photo);
        holder.mItemTitle.setText(video.title);
        holder.mItemDescription.setText(video.description);
        holder.mView.setTag(video.index);
    }

    @Override
    public int getItemCount() {
        return mLstVideos != null ? mLstVideos.size() : 0;
    }

    public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mItemPhoto;
        public TextView mItemTitle;
        public TextView mItemDescription;
        public View mView;

        public VideoHolder(View view) {
            super(view);
            mView = view;
            mItemPhoto = (ImageView) view.findViewById(R.id.iv_video_item);
            mItemTitle = (TextView) view.findViewById(R.id.tv_video_item_title);
            mItemDescription = (TextView) view.findViewById(R.id.tv_video_item_description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = Integer.parseInt(v.getTag().toString());
            Message msg = Message.obtain();
            msg.arg1 = index;
            mHandlerVideoSelected.sendMessage(msg);
        }
    }

}
