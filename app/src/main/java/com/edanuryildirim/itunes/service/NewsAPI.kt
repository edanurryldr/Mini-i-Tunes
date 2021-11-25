package com.edanuryildirim.itunes.service

import com.edanuryildirim.itunes.model.NewsModel
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsAPI {

    @GET("search?term=jack+johnson&entity=musicVideo")
    fun getMusicVideo(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=podcast")
    fun getPodcast(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=music")
    fun getMusic(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=movie")
    fun getMovie(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=shortFilm")
    fun getShortFilm(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=tvShow")
    fun getTVShow(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=audiobook")
    fun getAudioBook(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=ebook")
    fun getEbook(): Observable<NewsModel>

    @GET("search?term=jack+johnson&entity=software")
    fun getSoftware(): Observable<NewsModel>

}