package com.abrahamlay.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Abraham Lay on 2019-11-18.
 */

data class SearchResultDto(
    @SerializedName("total_count")
    var totalCount: Int = 0,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,
    @SerializedName("items")
    var items: List<ItemsItem>? = null
) {
    data class ItemsItem(
        @SerializedName("gists_url")
        var gistsUrl: String? = null,

        @SerializedName("repos_url")
        var reposUrl: String? = null,

        @SerializedName("following_url")
        var followingUrl: String? = null,

        @SerializedName("starred_url")
        var starredUrl: String? = null,

        @SerializedName("login")
        var login: String? = null,

        @SerializedName("followers_url")
        var followersUrl: String? = null,

        @SerializedName("type")
        var type: String? = null,

        @SerializedName("url")
        var url: String? = null,

        @SerializedName("subscriptions_url")
        var subscriptionsUrl: String? = null,

        @SerializedName("score")
        var score: Double = 0.toDouble(),

        @SerializedName("received_events_url")
        var receivedEventsUrl: String? = null,

        @SerializedName("avatar_url")
        var avatarUrl: String? = null,

        @SerializedName("events_url")
        var eventsUrl: String? = null,

        @SerializedName("html_url")
        var htmlUrl: String? = null,

        @SerializedName("site_admin")
        var siteAdmin: Boolean = false,

        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("gravatar_id")
        var gravatarId: String? = null,

        @SerializedName("node_id")
        var nodeId: String? = null,

        @SerializedName("organizations_url")
        var organizationsUrl: String? = null
    )
}