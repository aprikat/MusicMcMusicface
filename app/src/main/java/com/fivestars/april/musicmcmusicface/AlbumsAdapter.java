package com.fivestars.april.musicmcmusicface;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlbumsResponse.Album> albumList;

    public AlbumsAdapter() {
        albumList = new ArrayList<>();
    }

    public AlbumsAdapter(List<AlbumsResponse.Album> albums) {
        setAlbumList(albums);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false);
        AlbumHolder holder = new AlbumHolder(v);

        holder.ivAlbumImage = (ImageView) v.findViewById(R.id.iv_album_image);
        holder.tvAlbumName = (TextView) v.findViewById(R.id.tv_album_name);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumsResponse.Album album = albumList.get(position);
        AlbumHolder albumHolder= (AlbumHolder) holder;

        Glide.with(holder.itemView.getContext())
                .load(album.getImages().get(0).getUrl())
                .fitCenter()
                .into(albumHolder.ivAlbumImage);
        albumHolder.tvAlbumName.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumHolder extends RecyclerView.ViewHolder {
        public ImageView ivAlbumImage;
        public TextView tvAlbumName;

        public AlbumHolder(View itemView) {
            super(itemView);
        }
    }

    public List<AlbumsResponse.Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<AlbumsResponse.Album> albums) {
        this.albumList = albums;
    }
}
