package com.abrahamlay.data.datasource.database

import androidx.paging.DataSource
import com.abrahamlay.data.db.user.UserDao
import com.abrahamlay.data.mapper.map
import com.abrahamlay.domain.entity.SearchResultEntity
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by Abraham Lay on 2019-11-22.
 */
class UserDatabaseDataSourceImpl(private val userDao: UserDao, private val ioExecutor: Executor) :
    UserDatabaseDataSource {
    override fun getUsers(query: String): DataSource.Factory<Int, SearchResultEntity.ItemsItem> =
        userDao.selectAllPaged(query).map { it.map() }

    override fun persist(users: List<SearchResultEntity.ItemsItem>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            userDao.insert/*persist*/(users.map { it.map() })
            insertFinished()
        }
    }
}