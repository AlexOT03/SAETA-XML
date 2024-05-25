package com.example.saeta

import android.os.Parcel
import android.os.Parcelable

data class LanguageData(val title : String , val logo : Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(logo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LanguageData> {
        override fun createFromParcel(parcel: Parcel): LanguageData {
            return LanguageData(parcel)
        }

        override fun newArray(size: Int): Array<LanguageData?> {
            return arrayOfNulls(size)
        }
    }
}
