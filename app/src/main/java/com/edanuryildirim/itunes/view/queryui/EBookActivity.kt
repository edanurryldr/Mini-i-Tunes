package com.edanuryildirim.itunes.view.queryui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edanuryildirim.itunes.R
import com.edanuryildirim.itunes.adapter.RecyclerViewAdapter
import com.edanuryildirim.itunes.databinding.ActivityEbookBinding
import com.edanuryildirim.itunes.model.Articles
import com.edanuryildirim.itunes.model.NewsModel
import com.edanuryildirim.itunes.service.NewsAPI
import com.edanuryildirim.itunes.view.ui.DetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_ebook.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class EBookActivity : AppCompatActivity(), RecyclerViewAdapter.Listener  {

    private lateinit var binding: ActivityEbookBinding
    private val BASE_URL = "https://itunes.apple.com/"
    private var newsModels: NewsModel? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    //ProgressDialog
    lateinit var progerssProgressDialog: ProgressDialog

    //Disposable
    private var compositeDisposable : CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEbookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        progerssProgressDialog= ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading."+"  "+"Please waiting")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recyclerViewAdapter?.filter?.filter(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                recyclerViewAdapter?.filter?.filter(newText)
                return false
            }
        })

        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsAPI::class.java)

        compositeDisposable?.add(retrofit.getEbook()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleRespone)
        )
    }

    override fun onItemClick(article: Articles) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("data",article)
        startActivity(intent)
    }

    override fun handleRespone(newsModel: NewsModel) {
        var articleList = ArrayList(newsModel.results)
        articleList?.let { recyclerViewAdapter = RecyclerViewAdapter(articleList,this)
            val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = recyclerViewAdapter
        }
        progerssProgressDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}