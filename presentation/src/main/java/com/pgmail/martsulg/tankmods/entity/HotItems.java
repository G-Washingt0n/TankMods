package com.pgmail.martsulg.tankmods.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by g_washingt0n on 24.01.2018.
 */

public class HotItems implements Parcelable {
    private String uuid;
    private String name;
    private String thumbnail;
    private String text;
    private String created_at;
    private Subject subject;
    private Author author;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public class Subject implements Parcelable {
        private String uuid;
        private String thumbnail;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.uuid);
            dest.writeString(this.thumbnail);
        }

        public Subject() {
        }

        protected Subject(Parcel in) {
            this.uuid = in.readString();
            this.thumbnail = in.readString();
        }

        public final Creator<Subject> CREATOR = new Creator<Subject>() {
            @Override
            public Subject createFromParcel(Parcel source) {
                return new Subject(source);
            }

            @Override
            public Subject[] newArray(int size) {
                return new Subject[size];
            }
        };
    }

    public class Author implements Parcelable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            Log.e("AAAAA", "author's name setted");
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
        }

        public Author() {
        }

        protected Author(Parcel in) {
            this.name = in.readString();
        }

        public final Creator<Author> CREATOR = new Creator<Author>() {
            @Override
            public Author createFromParcel(Parcel source) {
                return new Author(source);
            }

            @Override
            public Author[] newArray(int size) {
                return new Author[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.name);
        dest.writeString(this.thumbnail);
        dest.writeString(this.text);
        dest.writeString(this.created_at);
        dest.writeParcelable(this.subject, flags);
        dest.writeParcelable(this.author, flags);
    }

    public HotItems() {
    }

    protected HotItems(Parcel in) {
        this.uuid = in.readString();
        this.name = in.readString();
        this.thumbnail = in.readString();
        this.text = in.readString();
        this.created_at = in.readString();
        this.subject = in.readParcelable(Subject.class.getClassLoader());
        this.author = in.readParcelable(Author.class.getClassLoader());
    }

    public static final Parcelable.Creator<HotItems> CREATOR = new Parcelable.Creator<HotItems>() {
        @Override
        public HotItems createFromParcel(Parcel source) {
            return new HotItems(source);
        }

        @Override
        public HotItems[] newArray(int size) {
            return new HotItems[size];
        }
    };
}