package com.fivestars.april.musicmcmusicface;

import java.util.List;

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
        List<Image> images;

        public Album() {}

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public List<Image> getImages() { return images; }
        public void setImages(List<Image> images) { this.images = images; }
    }

    public static class Image {
        String url;

        public Image() {}

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

}
