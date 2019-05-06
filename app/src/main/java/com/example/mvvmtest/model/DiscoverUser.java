package com.example.mvvmtest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mvvmtest.util.Vices;
import com.example.mvvmtest.util.Zodiac;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverUser implements Parcelable {

    @SerializedName("images")
    private List<String> images;

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("age")
    private int age;

    @SerializedName("zodiac")
    private Zodiac zodiac;

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

    public List<String> getImages() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.images);
        dest.writeInt(this.idUser);
        dest.writeInt(this.age);
        dest.writeInt(this.zodiac == null ? -1 : this.zodiac.ordinal());
        dest.writeString(this.city);
        dest.writeString(this.hometown);
        dest.writeString(this.profession);
        dest.writeString(this.job);
        dest.writeString(this.height);
        dest.writeInt(this.drink == null ? -1 : this.drink.ordinal());
        dest.writeInt(this.smoke == null ? -1 : this.smoke.ordinal());
    }

    public DiscoverUser() {
    }

    protected DiscoverUser(Parcel in) {
        this.images = in.createStringArrayList();
        this.idUser = in.readInt();
        this.age = in.readInt();
        int tmpZodiac = in.readInt();
        this.zodiac = tmpZodiac == -1 ? null : Zodiac.values()[tmpZodiac];
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
