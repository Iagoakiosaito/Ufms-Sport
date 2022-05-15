package com.example.controledejogos.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Team")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val idTeam: Int = 0,
    val description: String
) : Parcelable

