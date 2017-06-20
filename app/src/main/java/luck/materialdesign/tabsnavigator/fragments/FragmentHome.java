package luck.materialdesign.tabsnavigator.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.ebooks.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import luck.materialdesign.tabsnavigator.MainActivity;
import luck.materialdesign.tabsnavigator.books.Book;
import luck.materialdesign.tabsnavigator.books.BookAdapter;
import luck.materialdesign.tabsnavigator.books.BookClient;
import luck.materialdesign.tabsnavigator.books.BookDetailActivity;

/**
 * Created by Edwin on 15/02/2015.
 */
public class FragmentHome extends Fragment implements AdapterView.OnItemClickListener{

    View viewRoot = null;
    Context context = null;
    ListView lvBooks = null;
    BookClient client = null;
    BookAdapter bookAdapter = null;
    ProgressBar progressBar = null;
    public static final String BOOK_DETAIL_KEY = "book";



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home,container,false);
        context = container.getContext();
        setHasOptionsMenu(true);


        progressBar= (ProgressBar) viewRoot.findViewById(R.id.progress);
        // Show progress bar before making network request
        progressBar.setVisibility(ProgressBar.VISIBLE);

        lvBooks = (ListView) viewRoot.findViewById(R.id.homeLv);
        client = new BookClient();
        bookAdapter = new BookAdapter(context, new ArrayList<Book>());
        lvBooks.setAdapter(bookAdapter);
        lvBooks.setOnItemClickListener(this);

        fetchBooks("oscar Wilde");


        return viewRoot;
    }

    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchBooks(String query) {
        client = new BookClient();
        client.getBooks(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    //Log.d("json", response.toString());
                    progressBar.setVisibility(View.GONE);

                    JSONArray docs = null;
                    if(response != null) {


                        // Get the docs json array
                        docs = response.getJSONArray("docs");
                        // Parse json array into array of model objects
                        final ArrayList<Book> books = Book.fromJson(docs);
                        // Remove all books from the adapter
                        bookAdapter.clear();
                        // Load model objects into the adapter
                        for (Book book : books) {
                            bookAdapter.add(book); // add book through the adapter
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    //e.printStackTrace();
                    createToast(e.getMessage().toString(), Toast.LENGTH_LONG);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }

    private void createToast(String s, int lengthLong) {
        Toast.makeText(context, s, lengthLong).show();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        // Implementing ActionBar Search inside a fragment
        MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search); // sets icon
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        final SearchView sv = new SearchView(getActivity());


        // modifying the text inside edittext component
        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) sv.findViewById(id);
        textView.setHint("Search for book ..");
        textView.setHintTextColor(getResources().getColor(R.color.DarkGray));
        textView.setTextColor(getResources().getColor(R.color.clouds));

        // implementing the listener
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                if (!progressBar.isShown()){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }

                //// Fetch the data remotely
                fetchBooks(s.toString());

                // Reset SearchView
                sv.clearFocus();
                sv.setQuery("", false);
                sv.setIconified(true);



                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        item.setActionView(sv);





    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the detail view passing book as an extra

                String isbn =  view.getId()+"";

//                Log.i("id",isbn);


                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra(BOOK_DETAIL_KEY, bookAdapter.getItem(position));
                intent.putExtra("isbn", isbn);

                startActivity(intent);
            }
        });


    }
}
