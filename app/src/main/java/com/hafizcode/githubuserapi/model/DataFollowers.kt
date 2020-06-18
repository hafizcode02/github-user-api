package com.hafizcode.githubuserapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataFollowers(
    var username: String? = "",
    var name: String? = "",
    var address: String? = "",
    var photo: String? = ""
) : Parcelable