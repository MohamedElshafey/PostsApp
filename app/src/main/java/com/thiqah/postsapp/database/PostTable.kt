package com.thiqah.postsapp.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mohamed Elshafey on 4/18/2018.
 */
@Entity(tableName = "postTable")
class PostTable(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "userId") var userId: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "body") var body: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(userId)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostTable> {
        override fun createFromParcel(parcel: Parcel): PostTable {
            return PostTable(parcel)
        }

        override fun newArray(size: Int): Array<PostTable?> {
            return arrayOfNulls(size)
        }
    }

}