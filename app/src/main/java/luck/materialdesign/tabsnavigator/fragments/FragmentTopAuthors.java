package luck.materialdesign.tabsnavigator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.ebooks.R;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

/**
 * Created by Edwin on 15/02/2015.
 */
public class FragmentTopAuthors extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_authors,container,false);
        return v;
    }
}
