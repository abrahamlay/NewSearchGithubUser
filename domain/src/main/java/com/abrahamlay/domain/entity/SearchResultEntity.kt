package com.abrahamlay.domain.entity

/**
 * Created by Abraham Lay on 2019-11-18.
 */

data class SearchResultEntity(
    var totalCount: Int = 0,
    var incompleteResults: Boolean = false,
    var items: List<ItemsItem>? = null
) {
    data class ItemsItem(
        var gistsUrl: String? = null,

        var reposUrl: String? = null,

        var followingUrl: String? = null,

        var starredUrl: String? = null,

        var login: String? = null,

        var followersUrl: String? = null,

        var type: String? = null,

        var url: String? = null,

        var subscriptionsUrl: String? = null,

        var score: Double = 0.toDouble(),

        var receivedEventsUrl: String? = null,

        var avatarUrl: String? = null,

        var eventsUrl: String? = null,

        var htmlUrl: String? = null,

        var siteAdmin: Boolean = false,

        var id: Int = 0,

        var gravatarId: String? = null,

        var nodeId: String? = null,

        var organizationsUrl: String? = null
    )
}