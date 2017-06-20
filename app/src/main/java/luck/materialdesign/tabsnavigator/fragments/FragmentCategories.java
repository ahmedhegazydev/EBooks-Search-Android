package luck.materialdesign.tabsnavigator.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.ebooks.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

/**
 * Created by Edwin on 15/02/2015.
 */
public class FragmentCategories extends Fragment {


    View viewRoot = null;
    Context context = null;
    String url = "";
    String apiKey = "";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_categories,container,false);
        context = container.getContext();
        apiKey = context.getResources().getString(R.string.api_key);



//        url = "https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=";
//        instantiateRequestQueue(context, url, new interfaceGetResponse() {
//            @Override
//            public void getResponse(String response) {
//
//
//            }
//        });


        return viewRoot;
    }





    public void instantiateRequestQueue(Context context, String url, final interfaceGetResponse interfaceGetResponse){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        interfaceGetResponse.getResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }
        });


        // Add the request to the RequestQueue.
        queue.add(stringRequest);



    }



    public interface interfaceGetResponse{
        void getResponse(String response);
    }



}
