package com.pgmail.martsulg.tankmods.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by g_washingt0n on 23.01.2018.
 */

public class HotRoot implements Parcelable {
    private HotUser user;
    private ArrayList<HotFeed> feed;

    public HotUser getUser() {
        return user;
    }

    public void setUser(HotUser user) {
        this.user = user;
    }

    public ArrayList<HotFeed> getFeed() {
        return feed;
    }

    public void setFeed(ArrayList<HotFeed> feed) {
        this.feed = feed;
    }


    public class HotUser implements Parcelable {
        private String uuid;
        private String name;
        private String lang;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.uuid);
            dest.writeString(this.name);
            dest.writeString(this.lang);
        }

        public HotUser() {
        }

        protected HotUser(Parcel in) {
            this.uuid = in.readString();
            this.name = in.readString();
            this.lang = in.readString();
        }

        public final Creator<HotUser> CREATOR = new Creator<HotUser>() {
            @Override
            public HotUser createFromParcel(Parcel source) {
                return new HotUser(source);
            }

            @Override
            public HotUser[] newArray(int size) {
                return new HotUser[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeList(this.feed);
    }

    public HotRoot() {
    }

    protected HotRoot(Parcel in) {
        this.user = in.readParcelable(HotUser.class.getClassLoader());
        this.feed = new ArrayList<HotFeed>();
        in.readList(this.feed, HotFeed.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotRoot> CREATOR = new Parcelable.Creator<HotRoot>() {
        @Override
        public HotRoot createFromParcel(Parcel source) {
            return new HotRoot(source);
        }

        @Override
        public HotRoot[] newArray(int size) {
            return new HotRoot[size];
        }
    };
}