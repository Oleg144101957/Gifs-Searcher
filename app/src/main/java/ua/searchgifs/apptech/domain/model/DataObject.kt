package ua.searchgifs.apptech.domain.model

import com.google.gson.annotations.SerializedName

data class DataObject(
    @SerializedName("images") val images: DataImage
)