package FragmentPages;

import com.t_danbubbletea.bubbleteaapp.R;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.etsy.android.grid.StaggeredGridView;

import Adapters.ImageViewAdapter;

public class TeaFragment extends Fragment implements AbsListView.OnScrollListener,
                                                     AbsListView.OnItemClickListener{

    private StaggeredGridView stGridView;
    private boolean mHasRequestedMore;
    private ImageViewAdapter imageAdapter;
    private ArrayList<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tea_frag, container, false);
        // init grid view and image adapters
        stGridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);
        imageAdapter = new ImageViewAdapter(getActivity(), android.R.layout.simple_list_item_1,
                                            generateData());
        if (mData == null) {
            mData = generateData();
        }

        for (String data : mData) {
            imageAdapter.add(data);
        }

        stGridView.setAdapter(imageAdapter);
        stGridView.setOnScrollListener(this);
        stGridView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {

    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem,
                         final int visibleItemCount, final int totalItemCount) {

        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                mHasRequestedMore = true;
                onLoadMoreItems();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }

    private void onLoadMoreItems() {
        final ArrayList<String> sampleData = generateData();
        for (String data : sampleData) {
            imageAdapter.add(data);
        }
        // stash all the data in our backing store
        mData.addAll(sampleData);
        // notify the adapter that we can update now
        imageAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }

    private ArrayList<String> generateData(){
        ArrayList<String> data = new ArrayList<String>();
        data.add("http://i62.tinypic.com/2iitkhx.jpg");
        data.add("http://i61.tinypic.com/w0omeb.jpg");
        data.add("http://i60.tinypic.com/w9iu1d.jpg");
        data.add("http://i60.tinypic.com/iw6kh2.jpg");
        data.add("http://i57.tinypic.com/ru08c8.jpg");
        data.add("http://i60.tinypic.com/k12r10.jpg");

        return data;
    }

}