package com.example.controledejogos.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Player")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val idPlayer: Int = 0,
    @ForeignKey(
        entity = Team::class,
        parentColumns = ["idTeam"],
        childColumns = ["idTeam"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val idTeam: Int,
    val name: String,
    val cpf: String,
    val bornAt: Int,
) : Parcelable