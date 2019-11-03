package com.gemini.personalcapital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gemini.personalcapital.model.PostItemList;
import com.gemini.personalcapital.network.PostRepository;

public class FetchViewModel extends ViewModel {

    private MutableLiveData<PostItemList> mFetchResults;
    private MutableLiveData<Boolean> mIsFetching;
    private FetchViewModel_Thread fetchViewModel_Thread;

    public LiveData<PostItemList> getSearchResults() {
        if (mFetchResults == null) {
            mFetchResults = new MutableLiveData<>();
            mFetchResults.setValue(new PostItemList());
        }
        return mFetchResults;
    }

    public LiveData<Boolean> isSearching() {
        if (mIsFetching == null) {
            mIsFetching = new MutableLiveData<>();
            mIsFetching.setValue(false);
        }
        return mIsFetching;
    }

    public void fetch() {
        mIsFetching.setValue(true);

        if (fetchViewModel_Thread == null)
            fetchViewModel_Thread = new FetchViewModel_Thread();
        fetchViewModel_Thread.start();

    }

    private class FetchViewModel_Thread extends Thread {

        public void run() {

            PostRepository store = new PostRepository();
            PostItemList searchResults = mFetchResults.getValue();
            PostItemList newResults = store.refreshList();
            searchResults.clear();

            if (newResults != null) {
                searchResults.addAll(newResults);
            }

            mFetchResults.postValue(searchResults);
            mIsFetching.postValue(false);
        }
    }
}
