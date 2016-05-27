package com.fivestars.april.musicmcmusicface;

import java.util.List;

/**
 * Created by April.Luo on 5/26/16.
 */
public class AlbumsResponse {
    private String href;
    private List<Album> items;

    public String getHref() { return href; }
    public void setHref(String link) { this.href = link; }

    public List<Album> getItems() { return items; }
    public void setItems(List<Album> items) { this.items = items; }

    public static class Album {
        String id;
        String name;

        public Album() {}

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

}
