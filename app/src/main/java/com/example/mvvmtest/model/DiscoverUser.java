package com.example.mvvmtest.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.mvvmtest.util.Vices;
import com.example.mvvmtest.util.Zodiac;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DiscoverUser implements Parcelable {

    @SerializedName("images")
    private List<Integer> images;

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("age")
    private int age;

    @SerializedName("zodiac")
    private Zodiac zodiac;

    @SerializedName("distance")
    private int distance;

    @SerializedName("city")
    private String city;

    @SerializedName("hometown")
    private String hometown;

    @SerializedName("profession")
    private String profession;

    @SerializedName("job")
    private String job;

    @SerializedName("height")
    private String height;

    @SerializedName("drink")
    private Vices drink;

    @SerializedName("smoke")
    private Vices smoke;

    public DiscoverUser(List<Integer> images, int idUser, int age, Zodiac zodiac, int distance, String city, String hometown, String profession, String job, String height, Vices drink, Vices smoke) {
        this.images = images;
        this.idUser = idUser;
        this.age = age;
        this.zodiac = zodiac;
        this.distance = distance;
        this.city = city;
        this.hometown = hometown;
        this.profession = profession;
        this.job = job;
        this.height = height;
        this.drink = drink;
        this.smoke = smoke;
    }

    public List<Integer> getImages() {
        return images;
    }

    public int getAge() {
        return age;
    }

    public Zodiac getZodiac() {
        return zodiac;
    }

    public String getCity() {
        return city;
    }

    public String getHometown() {
        return hometown;
    }

    public String getProfession() {
        return profession;
    }

    public String getJob() {
        return job;
    }

    public String getHeight() {
        return height;
    }

    public Vices getDrink() {
        return drink;
    }

    public Vices getSmoke() {
        return smoke;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getDistance() {
        return distance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.images);
        dest.writeInt(this.idUser);
        dest.writeInt(this.age);
        dest.writeInt(this.zodiac == null ? -1 : this.zodiac.ordinal());
        dest.writeInt(this.distance);
        dest.writeString(this.city);
        dest.writeString(this.hometown);
        dest.writeString(this.profession);
        dest.writeString(this.job);
        dest.writeString(this.height);
        dest.writeInt(this.drink == null ? -1 : this.drink.ordinal());
        dest.writeInt(this.smoke == null ? -1 : this.smoke.ordinal());
    }

    protected DiscoverUser(Parcel in) {
        this.images = new ArrayList<Integer>();
        in.readList(this.images, Integer.class.getClassLoader());
        this.idUser = in.readInt();
        this.age = in.readInt();
        int tmpZodiac = in.readInt();
        this.zodiac = tmpZodiac == -1 ? null : Zodiac.values()[tmpZodiac];
        this.distance = in.readInt();
        this.city = in.readString();
        this.hometown = in.readString();
        this.profession = in.readString();
        this.job = in.readString();
        this.height = in.readString();
        int tmpDrink = in.readInt();
        this.drink = tmpDrink == -1 ? null : Vices.values()[tmpDrink];
        int tmpSmoke = in.readInt();
        this.smoke = tmpSmoke == -1 ? null : Vices.values()[tmpSmoke];
    }

    public static final Creator<DiscoverUser> CREATOR = new Creator<DiscoverUser>() {
        @Override
        public DiscoverUser createFromParcel(Parcel source) {
            return new DiscoverUser(source);
        }

        @Override
        public DiscoverUser[] newArray(int size) {
            return new DiscoverUser[size];
        }
    };
}
