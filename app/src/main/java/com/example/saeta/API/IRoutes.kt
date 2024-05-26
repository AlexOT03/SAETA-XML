package com.example.saeta.API

import android.os.Parcel
import android.os.Parcelable


data class Route(
        val id: Int,
        val agency: String,
        val agency_logo: String,
        val short_name: Int,
        val long_name: String,
        val vehicle_type: String,
        val vehicle_img: String,
        val status: Boolean,
        val color: String,
        val terminal_1: Int,
        val terminal_2: Int,
        val route_type: String,
        val Going: List<Trip>,
        val Return: List<Trip>
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString().toString(),
            TODO("Going"),
            TODO("Return")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(agency)
        parcel.writeString(agency_logo)
        parcel.writeInt(short_name)
        parcel.writeString(long_name)
        parcel.writeString(vehicle_type)
        parcel.writeString(vehicle_img)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeString(color)
        parcel.writeInt(terminal_1)
        parcel.writeInt(terminal_2)
        parcel.writeString(route_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route {
            return Route(parcel)
        }

        override fun newArray(size: Int): Array<Route?> {
            return arrayOfNulls(size)
        }
    }
}

data class Trip(
        val id: Int,
        val address: String,
        val stops: List<StopWrapper>
)
data class StopWrapper(
        val stop: Stop
)
data class Stop(
        val id: Int,
        val name: String,
        val road: String,
        val logo: String,
        val is_terminal: Boolean,
        val altitude: Number,
        val latitude: Number
)
data class StopsData(
        val goings:List<Stop>,
        val returns:List<Stop>
)