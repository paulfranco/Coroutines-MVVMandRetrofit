package co.paulfran.coroutines_mvvmandretrofit.view

import android.widget.ImageView
import co.paulfran.coroutines_mvvmandretrofit.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions



fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}