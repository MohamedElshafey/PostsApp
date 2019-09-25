package com.thiqah.postsapp.data


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "postTable")
data class PostModel(
    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String?,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    val userId: Int?
) : Parcelable

//,
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "rowId")
//    var rowId: Int = 0