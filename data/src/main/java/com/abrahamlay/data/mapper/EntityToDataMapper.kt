package com.abrahamlay.data.mapper

import com.abrahamlay.data.db.user.SearchResultData
import com.abrahamlay.domain.entity.SearchResultEntity

/**
 * Created by Abraham Lay on 2019-11-22.
 */

fun SearchResultEntity.map() = SearchResultData(
    totalCount = totalCount,
    incompleteResults = incompleteResults,
    items = items?.map { it.map() }
)

fun SearchResultEntity.ItemsItem.map() = SearchResultData.ItemsItem(
    gistsUrl,
    reposUrl,
    followingUrl,
    starredUrl,
    login,
    followersUrl,
    type,
    url,
    subscriptionsUrl,
    score,
    receivedEventsUrl,
    avatarUrl,
    eventsUrl,
    htmlUrl,
    siteAdmin,
    id,
    gravatarId,
    nodeId,
    organizationsUrl
)