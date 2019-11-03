package com.gemini.personalcapital.ui.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemini.personalcapital.R;
import com.gemini.personalcapital.constant.Constant;
import com.gemini.personalcapital.model.ArticleItemList;
import com.gemini.personalcapital.ui.Adapter.ArticleListAdapter;
import com.gemini.personalcapital.util.DeviceManger;
import com.gemini.personalcapital.viewmodel.FetchArticleViewModel;

import static com.gemini.personalcapital.constant.Constant.PROGRESS_ERROR;
import static com.gemini.personalcapital.constant.Constant.PROGRESS_LOADING;


public class ArticleListFragment extends Fragment {
    private ProgressDialog progressDialog_loading;
    private final int ACTIONBAR_MENU_ITEM_REFRESH = 0x0001;

    private RecyclerView mPostResults;
    private FetchArticleViewModel mViewModel;

    private boolean mStartup = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.from(getActivity()).inflate(R.layout.fragment_postlist, container, false);

        setHasOptionsMenu(true);
        mPostResults = view.findViewById(R.id.search_results);

        // set view model & observers
        mViewModel = ViewModelProviders.of(getActivity()).get(FetchArticleViewModel.class);

        mViewModel.getSearchResults().observe(getActivity(), new Observer<ArticleItemList>() {
            @Override
            public void onChanged(ArticleItemList searchResults) {
                if (searchResults.isEmpty() && !mStartup) {
                     Toast.makeText(getActivity(), PROGRESS_ERROR, Toast.LENGTH_SHORT).show();
                }
                if(progressDialog_loading != null) {
                    progressDialog_loading.dismiss();
                    progressDialog_loading = null;
                }
                mStartup = false;
                mPostResults.getAdapter().notifyDataSetChanged();
                mPostResults.scrollToPosition(0);
            }
        });

        // init recycler view
        final ArticleListAdapter adapter = new ArticleListAdapter(getActivity(), mViewModel.getSearchResults().getValue(), view);
        adapter.setHasStableIds(true);
        mPostResults.setAdapter(adapter);


        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), DeviceManger.getRowsNum(getActivity()));

        //layoutManager will take over to display the first article ​prominence​ ​at​ ​the​ ​top​ ​and​ ​display​ ​the​ ​image
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        mPostResults.setLayoutManager(layoutManager);

        if (progressDialog_loading == null) {
            progressDialog_loading = ProgressDialog.show(getActivity(), "",
                    PROGRESS_LOADING, true);
        }

        //The network call be implemented on the view model
        mViewModel.fetchArticle(Constant.SEARCH_URL);

        return view;
    }

    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add(Menu.NONE, ACTIONBAR_MENU_ITEM_REFRESH, Menu.NONE, "refresh");
        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0); //fix the color to white
        item.setTitle(spanString);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu,inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case ACTIONBAR_MENU_ITEM_REFRESH:
                if (progressDialog_loading == null) {
                    progressDialog_loading = ProgressDialog.show(getActivity(), "",
                            PROGRESS_LOADING, true);
                }
                mViewModel.fetchArticle(Constant.SEARCH_URL);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
