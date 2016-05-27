package com.fivestars.april.musicmcmusicface;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Artist> list;

    public AlbumsAdapter() {
        list = new ArrayList<>();
    }

    public AlbumsAdapter(List<Artist> data) {
        setList(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false);
        AlbumHolder holder = new AlbumHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AlbumHolder extends RecyclerView.ViewHolder {

        public AlbumHolder(View itemView) {
            super(itemView);
        }
    }

    public List<Artist> getList() {
        return list;
    }

    public void setList(List<Artist> data) {
        this.list = data;
    }
}
