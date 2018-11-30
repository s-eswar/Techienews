package com.example.vishwa.techienews

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.newslayout.view.*
import android.graphics.drawable.shapes.RoundRectShape
import com.squareup.picasso.Transformation
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import android.R.attr.radius
import android.R.attr.bitmap
import android.graphics.*


data class MainAdapter(var context: Context, var news: News): RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): CustomViewHolder {
        val view_layout_news = LayoutInflater.from(context).inflate(R.layout.newslayout, parent,false)
        return CustomViewHolder(view_layout_news)
    }

    override fun getItemCount(): Int {
        return news.articles!!.size;
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.view.maintitle.text = news.articles?.get(position)?.title;
        holder.view.author.text = "~" + news.articles?.get(position)?.author;
        holder.view.news_text.text = news.articles?.get(position)?.content?.substring(0,80) + "...";
        var image_url = news.articles?.get(position)?.urlToImage;
        holder.view.image.visibility=View.VISIBLE
        Picasso.get().load(image_url)
            .into(holder.view.image)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}

fun getBitmapFromURL(src: String): Bitmap? {
    try {
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.setDoInput(true)
        connection.connect()
        val input = connection.getInputStream()
        return BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

}