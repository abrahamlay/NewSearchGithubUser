package com.abrahamlay.data.db.user

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.abrahamlay.data.db.BaseDao
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-22.
 */
@Dao
interface UserDao : BaseDao<SearchResultData.ItemsItem> {

    @Query("SELECT * FROM search_item_table WHERE id = :id")
    override fun select(id: Long): Flowable<SearchResultData.ItemsItem>

    @Query("SELECT * FROM search_item_table WHERE login LIKE '%'||:query||'%' ORDER BY id")
    override fun selectAllPaged(query: String): DataSource.Factory<Int, SearchResultData.ItemsItem>

    @Query("DELETE FROM search_item_table")
    override fun truncate()

    @Transaction
    fun replace(users: List<SearchResultData.ItemsItem>) {
        val firstId: Int = users.firstOrNull()?.id ?: run {
            0
        }

        val lastId = users.lastOrNull()?.id ?: run {
            Int.MAX_VALUE
        }

        deleteRange(firstId, lastId)
        insert(users)
    }

    @Query("DELETE FROM search_item_table WHERE id BETWEEN :firstId AND :lastId")
    fun deleteRange(firstId: Int, lastId: Int)

    @Query("DELETE FROM search_item_table")
    override fun deleteAll()
}