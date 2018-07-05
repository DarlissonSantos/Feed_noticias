package com.example.darli.uemanews

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.example.darli.main.activity_main.*
import com.example.darli.Adapter.Interface.ClickListener
import com.example.darli.Adapter.FeedAdapter
import com.example.darli.Base.RSS


class MainActivity : AppCompatActivity() {
    private val RSS_link = "http://www.uema.br/feed/"
    private val RSS_to_JSON = "https://api.rss2json.com/v1/api.json?rss_url="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "UEMANEWS"
        setSupportActionBar(toolbar)

        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)

        recyclerView.layoutManager = linearLayoutManager

        CarregaRss()
    }

    private fun CarregaRss() {

        val RssAsync = object:AsyncTask<String,String,String>(){
            internal  var mDialog = ProgressDialog(this@MainActivity)

            override fun onPreExecute() {
                mDialog.setMessage("Please Wait...")
                mDialog.show()
            }

            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rssObject:RSS
                rssObject = Gson().fromJson<RSS>(result,RSS::class.java!!)
                val adapter = FeedAdapter(rssObject,baseContext)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun doInBackground(vararg p0: String): String {

                val result:String
                val http = HTTPDataHandler()
                result = http.GetHTTPDataHandler(p0[0])
                return result
            }





        }

        val url_get_data = StringBuilder(RSS_to_JSON)
        url_get_data.append(RSS_link)
        RssAsync.execute(url_get_data.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_refresh)
            CarregaRss()
        return true
    }
}
