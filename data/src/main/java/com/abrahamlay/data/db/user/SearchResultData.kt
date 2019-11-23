package com.abrahamlay.data.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abraham Lay on 2019-11-18.
 */

@Entity(tableName = "search_result_table")
data class SearchResultData(
    var totalCount: Int = 0,
    var incompleteResults: Boolean = false,
    var items: List<ItemsItem>? = null
) {
    @Entity(tableName = "search_item_table")
    data class ItemsItem(
        @ColumnInfo(name = "gistsUrl")
        var gistsUrl: String? = null,
        @ColumnInfo(name = "reposUrl")
        var reposUrl: String? = null,
        @ColumnInfo(name = "followingUrl")
        var followingUrl: String? = null,
        @ColumnInfo(name = "starredUrl")
        var starredUrl: String? = null,
        @ColumnInfo(name = "login")
        var login: String? = null,
        @ColumnInfo(name = "followersUrl")
        var followersUrl: String? = null,
        @ColumnInfo(name = "type")
        var type: String? = null,
        @ColumnInfo(name = "url")
        var url: String? = null,
        @ColumnInfo(name = "subscriptionsUrl")
        var subscriptionsUrl: String? = null,
        @ColumnInfo(name = "score")
        var score: Double = 0.toDouble(),
        @ColumnInfo(name = "receivedEventsUrl")
        var receivedEventsUrl: String? = null,
        @ColumnInfo(name = "avatarUrl")
        var avatarUrl: String? = null,
        @ColumnInfo(name = "eventsUrl")
        var eventsUrl: String? = null,
        @ColumnInfo(name = "htmlUrl")
        var htmlUrl: String? = null,
        @ColumnInfo(name = "siteAdmin")
        var siteAdmin: Boolean = false,
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false)
        var id: Int = 0,
        @ColumnInfo(name = "gravatarId")
        var gravatarId: String? = null,
        @ColumnInfo(name = "nodeId")
        var nodeId: String? = null,
        @ColumnInfo(name = "organizationsUrl")
        var organizationsUrl: String? = null
    )
}