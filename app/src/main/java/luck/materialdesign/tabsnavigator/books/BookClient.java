package luck.materialdesign.tabsnavigator.books;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ahmed on 20/06/17.
 */

public class BookClient {

    public final static String API_BASE_URL = "http://openlibrary.org/";
    private AsyncHttpClient client;

    public BookClient(){
        this.client = new AsyncHttpClient();
    }


    public String getApiUrl(String relativeUrl){
        return this.API_BASE_URL + relativeUrl;
    }

    // Method for accessing the search API
    public void getBooks(String query, JsonHttpResponseHandler jsonHttpResponseHandler){

        try {

            String url = this.getApiUrl("search.json?q=");

            this.client.get(url + URLEncoder.encode(query, "utf-8"), jsonHttpResponseHandler);


        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    // Method for accessing the search API
    public void getExtraDetails(String id, JsonHttpResponseHandler jsonHttpResponseHandler){

        String url = null;
        try {
            url = "https://openlibrary.org/api/books?bibkeys=ISBN:"+ URLEncoder.encode(id, "utf-8")+"&jscmd=data&format=json";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.client.get(url , jsonHttpResponseHandler);

        Log.i("url", url.toString());



    }



}
