package com.example.shahbazahmed.favouritesapp.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahbazahmed on 19/07/17.
 */

public class Item {
    @Expose
    private String title;
    @Expose
    private String desc;
    @Expose
    private String type;
    @Expose
    private String imageUrl;
    @SerializedName("view-count")
    @Expose
    private int viewCount;
    @Expose(serialize = false, deserialize = false)
    private boolean isFavourite;

    public Item(
            String title,
            String desc,
            String type,
            String imageUrl,
            int viewCount,
            boolean isFavourite
    ) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.imageUrl = imageUrl;
        this.viewCount = viewCount;
        this.isFavourite = isFavourite;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getViewCount() {
        return viewCount;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }
        Item otherItem = (Item) obj;
        return this.title.equals(otherItem.title) && this.desc.equals(otherItem.desc) &&
                this.type.equals(otherItem.type) && this.imageUrl.equals(otherItem.imageUrl);
    }
}
