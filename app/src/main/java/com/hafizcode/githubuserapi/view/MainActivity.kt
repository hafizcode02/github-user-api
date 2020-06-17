package com.hafizcode.githubuserapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizcode.githubuserapi.R
import com.hafizcode.githubuserapi.model.DataUsers
import com.hafizcode.githubuserapi.viewModel.ListDataUsersAdapter
import com.hafizcode.githubuserapi.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listDataUser: ArrayList<DataUsers> = ArrayList()
    private lateinit var mainViewModel: MainViewModel

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewConfig()
        searchData()
    }

    private fun viewConfig() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val listAdapter = ListDataUsersAdapter(listDataUser)
        listAdapter.notifyDataSetChanged()
        recyclerView.adapter = listAdapter

        configMainViewModel(listAdapter)

        listAdapter.setOnItemClickCallback(object : ListDataUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(dataUsers: DataUsers) {

            }
        })
    }

    private fun configMainViewModel(adapter: ListDataUsersAdapter) {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        mainViewModel.getDataGit(applicationContext)
        showLoading(true)
        mainViewModel.getListUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            loadingProgress.visibility = View.VISIBLE
        } else {
            loadingProgress.visibility = View.INVISIBLE
        }
    }

    private fun searchData() {
        user_search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isEmpty()!!) {
                    return true
                } else {

                    listDataUser.clear()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

//    private fun getDataGitSearch(query: String) {
//
//    }

//    private fun getDataGit() {
//        loadingProgress.visibility = View.VISIBLE
//
//        val httpClient = AsyncHttpClient()
//        httpClient.addHeader("Authorization", "f49f4bf36e789f74efb91cc10f7e97bc8b3a5e77")
//        httpClient.addHeader("User-Agent", "request")
//        val urlClient = "https://api.github.com/users"
//
//        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?
//            ) {
//                loadingProgress.visibility = View.INVISIBLE
//                val result = responseBody?.let { String(it) }
//                Log.d(TAG, result)
//                try {
//                    val jsonArray = JSONArray(result)
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        val usernameLogin = jsonObject.getString("login")
//                        getDataGitDetail(usernameLogin)
//                    }
//                } catch (e: Exception) {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//                loadingProgress.visibility = View.INVISIBLE
//                val errorMessage = when (statusCode) {
//                    401 -> "$statusCode : Bad Request"
//                    403 -> "$statusCode : Forbidden"
//                    404 -> "$statusCode : Not Found"
//                    else -> "$statusCode : ${error?.message}"
//                }
//                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

//    private fun getDataGitDetail(usernameLogin: String) {
//        loadingProgress.visibility = View.VISIBLE
//
//        val httpClient = AsyncHttpClient()
//        httpClient.addHeader("Authorization", "f49f4bf36e789f74efb91cc10f7e97bc8b3a5e77")
//        httpClient.addHeader("User-Agent", "request")
//        val urlClient = "https://api.github.com/users/$usernameLogin"
//
//        httpClient.get(urlClient, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?
//            ) {
//                loadingProgress.visibility = View.INVISIBLE
//                val result = responseBody?.let { String(it) }
//                Log.d(TAG, result)
//
//                try {
//                    val jsonObject = JSONObject(result)
//                    val username = "@" + jsonObject.getString("login")
//                    val name = jsonObject.getString("name")
//                    val avatar = jsonObject.getString("avatar_url")
//                    val company = jsonObject.getString("company")
//                    val location = jsonObject.getString("location")
//                    val repository =
//                        getString(R.string._100_repository, jsonObject.getString("public_repos"))
//                    val followers =
//                        getString(R.string.follower, jsonObject.getString(("followers")))
//                    val following =
//                        getString(R.string.followings, jsonObject.getString("following"))
//                    listDataUser.add(
//                        DataUsers(
//                            username,
//                            name,
//                            avatar,
//                            company,
//                            location,
//                            repository,
//                            followers,
//                            following
//                        )
//                    )
//                    viewConfig()
//                } catch (e: Exception) {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//                loadingProgress.visibility = View.INVISIBLE
//                val errorMessage = when (statusCode) {
//                    401 -> "$statusCode : Bad Request"
//                    403 -> "$statusCode : Forbidden"
//                    404 -> "$statusCode : Not Found"
//                    else -> "$statusCode : ${error?.message}"
//                }
//                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//    }

}
