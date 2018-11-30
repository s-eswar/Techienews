package com.example.vishwa.techienews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonAPI:APIService
    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.instance
        jsonAPI = retrofit.create(APIService::class.java)

        recyclerview.setHasFixedSize(true)
        recyclerview.recycledViewPool.setMaxRecycledViews(0,0)
        recyclerview.layoutManager = LinearLayoutManager(this)
        fetchData();
    }

    private fun fetchData() {
        compositeDisposable.add(jsonAPI.getallNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                displayData(data)
                compositeDisposable.dispose()
                compositeDisposable.clear()
            }, {

                it.printStackTrace()
            })
        )
    }

    private fun displayData(data: News?) {
        var adapter = MainAdapter(this@MainActivity,data!!)
        recyclerview.adapter = adapter
    }
}

