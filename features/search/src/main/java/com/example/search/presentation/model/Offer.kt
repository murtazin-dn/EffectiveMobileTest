package com.example.search.presentation.model

internal data class Offer(
    val buttonText: String? = null,
    val id: String? = null,
    val link: String,
    val title: String
)

internal fun com.example.search.data.model.OfferModel.toUi() = Offer(
    id = this.id,
    title = this.title,
    link = this.link,
    buttonText = this.buttonText
)
