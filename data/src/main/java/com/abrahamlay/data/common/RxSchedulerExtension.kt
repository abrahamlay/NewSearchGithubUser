package com.abrahamlay.data.common

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dr.jacky on 10/6/2018.
 */
//Observable
fun <T> Observable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Observable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

fun <T> Observable<T>.applyJobExecutorScheduler() = applyScheduler(Schedulers.from(JobExecutor()))

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Flowable
fun <T> Flowable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Flowable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

fun <T> Flowable<T>.applyJobExecutorScheduler() = applyScheduler(Schedulers.from(JobExecutor()))

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Single
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

fun <T> Single<T>.applyJobExecutorScheduler() = applyScheduler(Schedulers.from(JobExecutor()))

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())


//Maybe
fun <T> Maybe<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Maybe<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

fun <T> Maybe<T>.applyJobExecutorScheduler() = applyScheduler(Schedulers.from(JobExecutor()))

private fun <T> Maybe<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())