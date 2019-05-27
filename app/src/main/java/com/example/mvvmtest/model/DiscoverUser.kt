package com.example.mvvmtest.model

import android.os.Parcel
import android.os.Parcelable
import com.example.mvvmtest.util.Vices
import com.example.mvvmtest.util.Zodiac

data class DiscoverUser(
        val images: List<Int>,
        val idUser: Int,
        val age: Int,
        val zodiac: Zodiac,
        val distance: Int,
        val city: String,
        val hometown: String,
        val profession: String,
        val job: String,
        val height: String,
        val drink: Vices,
        val smoke: Vices) : Parcelable {

    constructor(parcel: Parcel) : this(
            TODO("images"),
            parcel.readInt(),
            parcel.readInt(),
            TODO("zodiac"),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("drink"),
            TODO("smoke")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUser)
        parcel.writeInt(age)
        parcel.writeInt(distance)
        parcel.writeString(city)
        parcel.writeString(hometown)
        parcel.writeString(profession)
        parcel.writeString(job)
        parcel.writeString(height)
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

