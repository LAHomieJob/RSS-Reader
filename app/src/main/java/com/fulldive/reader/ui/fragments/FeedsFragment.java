package com.fulldive.reader.ui.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fulldive.reader.DetailActivity;
import com.fulldive.reader.R;
import com.fulldive.reader.ui.adapters.NewsAdapter;
import com.fulldive.reader.ui.callbacks.NewsEntityUtilCallback;
import com.fulldive.reader.ui.interfaces.ItemClickListener;
import com.fulldive.reader.ui.interfaces.ToggleClickListener;
import com.fulldive.reader.ui.viewmodels.ViewModel;

/**
 * {@link FeedsFragment}
 * Provides list of all RSS feeds
 */
public class FeedsFragment extends Fragment {

    public static final String TITLE = "TITLE";
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ViewModel mViewModel;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_feeds, container, false);
        recyclerView = mView.findViewById(R.id.feeds_recycler_view);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecyclerView();
        mViewModel.getAllNewsEntities()
                .observe(this, allNewsEntities -> {
                    NewsEntityUtilCallback productDiffUtilCallback =
                            new NewsEntityUtilCallback(adapter.getNewsItems(), allNewsEntities);
                    DiffUtil.DiffResult newsEntitiesDiffResult = DiffUtil.calculateDiff(productDiffUtilCallback);
                    adapter.setNewsItems(allNewsEntities);
                    newsEntitiesDiffResult.dispatchUpdatesTo(adapter);
                });
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        initAdapterWithClickListeners();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAdapterWithClickListeners() {
        ItemClickListener onClickListener = (v, position) -> {
            String title = adapter.getNewsItems().get(position).getTitle();
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(TITLE, title);
            startActivity(intent);
        };
        ToggleClickListener onToggleClickListener = (v, bookmarked, position) -> {
            String title = adapter.getNewsItems().get(position).getTitle();
            if (bookmarked) {
                mViewModel.bookmarkNewsEntity(title);
                Log.d("BOOKMARK_STATUS", "Bookmarked");
            } else {
                mViewModel.removeBookmarkNewsEntity(title);
                Log.d("BOOKMARK_STATUS", "RemovedBookmark");
            }
        };
        adapter = new NewsAdapter(getContext(), onClickListener, onToggleClickListener);
        recyclerView.setAdapter(adapter);
    }
}
