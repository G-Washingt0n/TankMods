package com.pgmail.martsulg.tankmods.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by g_washingt0n on 24.01.2018.
 */

public class HotFeed implements Parcelable {
    private String uuid;
    private String type;
    private String title;
    private String thumbnail;
    private String caption;
    private int likes;
    private int downloads;
    private String cta_type;
    private String url;
    private ArrayList<HotItems> items;
    @SerializedName("tags")
    private ArrayList<String> tags;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getCta_type() {
        return cta_type;
    }

    public void setCta_type(String cta_type) {
        this.cta_type = cta_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<HotItems> getItems() {
        return items;
    }

    public void setItems(ArrayList<HotItems> items) {
        this.items = items;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.thumbnail);
        dest.writeString(this.caption);
        dest.writeInt(this.likes);
        dest.writeInt(this.downloads);
        dest.writeString(this.cta_type);
        dest.writeString(this.url);
        dest.writeList(this.items);
        dest.writeStringList(this.tags);
    }

    public HotFeed() {
    }

    protected HotFeed(Parcel in) {
        this.uuid = in.readString();
        this.type = in.readString();
        this.title = in.readString();
        this.thumbnail = in.readString();
        this.caption = in.readString();
        this.likes = in.readInt();
        this.downloads = in.readInt();
        this.cta_type = in.readString();
        this.url = in.readString();
        this.items = new ArrayList<HotItems>();
        in.readList(this.items, HotItems.class.getClassLoader());
        this.tags = in.createStringArrayList();
    }

    public static final Parcelable.Creator<HotFeed> CREATOR = new Parcelable.Creator<HotFeed>() {
        @Override
        public HotFeed createFromParcel(Parcel source) {
            return new HotFeed(source);
        }

        @Override
        public HotFeed[] newArray(int size) {
            return new HotFeed[size];
        }
    };
}
