package com.hafizcode.githubuserapi.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizcode.githubuserapi.R
import com.hafizcode.githubuserapi.model.DataUsers
import com.hafizcode.githubuserapi.viewModel.ViewPagerDetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(applicationContext, "landscape", Toast.LENGTH_SHORT).show()
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        } else {
            Toast.makeText(applicationContext, "portrait", Toast.LENGTH_SHORT).show()
            viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
        }

        if (supportActionBar != null) {
            supportActionBar?.title = "Detail User"
        }
        setData()
        viewPagerConfig()


    }

    private fun viewPagerConfig() {
        val viewPagerDetail = ViewPagerDetailAdapter(this, supportFragmentManager)
        viewpager.adapter = viewPagerDetail
        tabs.setupWithViewPager(viewpager)
        supportActionBar?.elevation = 0f
    }

    private fun setData() {
        val dataUser = intent.getParcelableExtra(EXTRA_DETAIL) as DataUsers
        Glide.with(this)
            .load(dataUser.avatar)
            .apply(RequestOptions().override(150, 130))
            .into(avatars)
        fullName.text = dataUser.name
        username.text = getString(R.string.hafizcode02, dataUser.username)
        company.text = dataUser.company
        location.text = dataUser.location
        following.text = dataUser.following
        followers.text = dataUser.followers
        repositories.text = dataUser.repository
    }


}
