package com.abrahamlay.data.db

import androidx.paging.DataSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Flowable

/**
 * Created by Abraham Lay on 2019-11-22.
 */
interface BaseDao<T> {

    fun select(id: Long): Flowable<T>

    fun selectAllPaged(query: String): DataSource.Factory<Int, T>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(t: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ts: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(t: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(ts: List<T>)

//    @Delete
//    fun delete(t: T): Single<Int>

    @Delete
    fun delete(ts: List<T>)

    @Delete
    fun deleteAll()

    fun truncate()
}