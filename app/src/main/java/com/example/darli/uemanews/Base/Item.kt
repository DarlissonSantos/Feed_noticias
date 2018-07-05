package com.example.darli.uemanews.Base

import java.lang.Object

/**
 * Created by darli on 2018-07-04.
 */

data class Item(val title:String, val pubDate :String, val link:String, val guide:String,
                val author:String, val thumbnail:String, val description:String, val content:String,
                val enclosure: Object, val categories:List<String>)