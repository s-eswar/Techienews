package com.example.vishwa.techienews

import io.reactivex.Observable
import retrofit2.http.GET

internal interface APIService {

    @GET("everything?sources=wired&apiKey=939408f6ffc74b5bb899bda8f6e257b3")
    fun getallNews(): Observable<News>

    @GET("top-headlines?sources=wired&apiKey=939408f6ffc74b5bb899bda8f6e257b3")
    fun getheadlines(): Observable<News>
}
