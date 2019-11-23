package com.abrahamlay.data.mapper

import com.abrahamlay.data.dto.SearchResultDto
import com.abrahamlay.domain.entity.SearchResultEntity

/**
 * Created by Abraham Lay on 2019-11-19.
 */

fun SearchResultDto.map() = SearchResultEntity(
    totalCount = totalCount,
    incompleteResults = incompleteResults,
    items = items?.map { it.map() }
)

fun SearchResultDto.ItemsItem.map() = SearchResultEntity.ItemsItem(
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