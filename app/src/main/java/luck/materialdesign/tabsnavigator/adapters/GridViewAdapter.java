package luck.materialdesign.tabsnavigator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmed.ebooks.R;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by ahmed on 20/06/17.
 */

public class GridViewAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<BookItem> bookItems = null;



    public GridViewAdapter(Context context, ArrayList<BookItem> bookItems){
        this.context = context;
        this.bookItems = bookItems;

    }

    @Override
    public int getCount() {
        return this.bookItems.size();
    }

    @Override
    public BookItem getItem(int position) {
        return this.bookItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View view1 = null;

        if (view1 == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view1 = layoutInflater.inflate(R.layout.item_one_book, null);
            NetworkImageView bookImage  = (NetworkImageView) view1.findViewById(R.id.bookImageView);
            TextView bookName = (TextView) view1.findViewById(R.id.bookName);

            BookItem bookItem = this.getItem(pos);

            ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(context), new BitmapLruCache());


            bookImage.setImageUrl(bookItem.getBookImageUrl(), imageLoader);
            bookName.setText(bookItem.getBookName());



        }



        return view1;
    }
}
