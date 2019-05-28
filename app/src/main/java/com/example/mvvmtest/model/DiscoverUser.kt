package com.example.mvvmtest.model

import android.os.Parcel
import android.os.Parcelable
import com.example.mvvmtest.util.Vices
import com.example.mvvmtest.util.Zodiac

data class DiscoverUser(
        val idUser: Int,
        val age: Int,
        val zodiac: String,
        val distance: Int,
        val city: String,
        val hometown: String,
        val profession: String,
        val job: String,
        val height: String,
        val drink: String,
        val smoke: String) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUser)
        parcel.writeInt(age)
        parcel.writeString(zodiac)
        parcel.writeInt(distance)
        parcel.writeString(city)
        parcel.writeString(hometown)
        parcel.writeString(profession)
        parcel.writeString(job)
        parcel.writeString(height)
        parcel.writeString(drink)
        parcel.writeString(smoke)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiscoverUser> {
        override fun createFromParcel(parcel: Parcel): DiscoverUser {
            return DiscoverUser(parcel)
        }

        override fun newArray(size: Int): Array<DiscoverUser?> {
            return arrayOfNulls(size)
        }
    }

}

