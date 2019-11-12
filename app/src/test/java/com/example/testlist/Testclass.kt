package com.example.testlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testlist.Api.ListDataService
import com.example.testlist.Model.Listmodel
import com.example.testlist.Model.Row
import com.example.testlist.Viewmodel.MainActivityviewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class Testclass {

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Mock
    lateinit var dataService: ListDataService

    @Mock
    lateinit var rowdata: ArrayList<Row>

    @InjectMocks
    var listviewmodel = MainActivityviewModel()


    private var testsingle: Single<Listmodel>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getListdataSucess() {
        val row = Row("heading", "", "Subheading")

        rowdata.add(row)
        val datalist = Listmodel(rowdata, "Title")
        testsingle = Single.just(datalist)

        `when`(dataService.getList()).thenReturn(testsingle)

        listviewmodel.refresh()

        Assert.assertEquals(1, listviewmodel.listdata.value)

        Assert.assertEquals(false, listviewmodel.listerror.value)

        Assert.assertEquals(false, listviewmodel.loading.value)
    }

    @Before
    fun setupRxSchedulers() {
        val immdeiate = object : Scheduler() {

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { t: Callable<Scheduler> -> immdeiate }
        RxJavaPlugins.setInitComputationSchedulerHandler { t: Callable<Scheduler> -> immdeiate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { t: Callable<Scheduler> -> immdeiate }
        RxJavaPlugins.setInitSingleSchedulerHandler { t: Callable<Scheduler> -> immdeiate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { t: Callable<Scheduler> -> immdeiate }
    }
}