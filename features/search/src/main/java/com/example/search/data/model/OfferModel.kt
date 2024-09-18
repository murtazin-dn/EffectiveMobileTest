package com.example.search.data.model
import com.example.network.dto.OfferDto

internal data class OfferModel(
    val buttonText: String? = null,
    val id: String? = null,
    val link: String,
    val title: String
)

internal fun OfferDto.toModel() = com.example.search.data.model.OfferModel(
    id = this.id,
    title = this.title,
    link = this.link,
    buttonText = this.button?.text
)