package com.bitlove.fetlife.logic.dataholder

import android.arch.persistence.room.Ignore
import com.bitlove.fetlife.getBaseUrl
import com.bitlove.fetlife.hash
import com.mikepenz.google_material_typeface_library.GoogleMaterial

abstract class CardViewDataHolder {

    @Ignore
    var cardHash: String? = null

    //TODO: Clean up sync object hierarchy
    open fun getLocalId() : String? = null
    open fun getType() : String? = null
    open fun getRemoteId() : String? = null

    open fun getAvatar() : AvatarViewDataHolder? = null
    open fun getAvatarTitle() : String? = null

    open fun getTitle() : String? = null
    open fun getSupportingText() : String? = null

    open fun getThumbUrl() : String? = null

    open fun getMediaUrl() : String? = null
    open fun getMediaAspectRatio() : Float? = null

    open fun getCommentCountText() : String? = null
    open fun getCommentCount() : Int {
        return try {
            getCommentCountText()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    open fun getChild() : CardViewDataHolder? = null
    open fun getChildren() : List<CardViewDataHolder>? = null
    open fun getChildrenScreenTitle() : String? = getAvatarTitle()

    open fun hasNewComment() : Boolean? = false
    //open fun hasNewLove() : Boolean? = false
    open fun getLoveCount() : String? = null
    open fun isLoved() : Boolean? = null

    open fun isFavorite(): Boolean? = null

    open fun getComments() : List<ReactionViewDataHolder>? = null
    open fun getLoves() : List<ReactionViewDataHolder>? = null

    open fun displayComments() : Boolean? = null
    open fun isDeletable() : Boolean? = false

    open fun getUrl(): String? = null

    open fun isSame(other: CardViewDataHolder): Boolean {
        return this.getRemoteId() == other.getRemoteId() && this.getType() == this.getType()
    }

    open fun hasSameContent(other: CardViewDataHolder): Boolean {
        return getContentHash() == other.getContentHash()
    }

    open fun getContentHash() : String {
        if (cardHash == null) {
            cardHash = (
                    getAvatar()?.getContentHash() +
                    getAvatarTitle() +
                    getSupportingText() +
                    getMediaUrl()?.getBaseUrl() +
                    getMediaAspectRatio() +
                    getCommentCount() +
                    hasNewComment() +
                    getLoveCount() +
                    isLoved() +
                    isFavorite() +
                    getComments()?.size +
                    getLoves()?.size
            ).hash()
        }
        return cardHash!!
    }

}