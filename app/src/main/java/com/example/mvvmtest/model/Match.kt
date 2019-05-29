package com.example.mvvmtest.model

import android.os.Parcel
import android.os.Parcelable

data class Match(
        val id: Int,
        val created_at: String,
        val id_user: Int,
        val image: Int,
        val name: String,
        val age: Int,
        val last_message: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(created_at)
        parcel.writeInt(id_user)
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(last_message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}