package com.example.darli.uemanews.Base

/**
 * Created by darli on 2018-07-04.
 */


class HTTPDataHandler {


    fun GetHTTPDataHandler(urlStr: String): String? {

        try {

            val url = URL(urlStr)
            val urlConnection = url.openConnection() as java.net.HttpURLConnection

            if (urlConnection.getResponseCode() == java.net.HttpURLConnection.HTTP_OK) {

                val inputStream = BufferedInputStream(urlConnection.getInputStream())

                val r = BufferedReader(InputStreamReader(inputStream))
                val sb = StringBuilder()
                var line = ""
                while ((line = r.readLine()) != null) {
                    sb.append(line)
                    stream = sb.toString()
                    urlConnection.disconnect()

                }

            }

        } catch (ex: Exception) {
            return null
        }

        return stream
    }

    companion object {

        internal var stream = ""
    }
}
