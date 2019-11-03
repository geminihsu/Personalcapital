package com.gemini.personalcapital.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gemini.personalcapital.model.ArticleItemList;
import com.gemini.personalcapital.network.ArticleRepository;

public class FetchArticleViewModel extends ViewModel {

    private MutableLiveData<ArticleItemList> mFetchResults;
    private FetchViewModel_Thread fetchViewModel_Thread;
    private String url;

    public LiveData<ArticleItemList> getSearchResults() {
        if (mFetchResults == null) {
            mFetchResults = new MutableLiveData<>();
            mFetchResults.setValue(new ArticleItemList());
        }
        return mFetchResults;
    }

    public void fetchArticle(String url) {
        this.url = url;
        if (fetchViewModel_Thread == null)
            fetchViewModel_Thread = new FetchViewModel_Thread();
        fetchViewModel_Thread.start();

    }

    /*
    * It will fetchArticles from the url by hitting url,
    * ArticleRepository class pass the content and return it.
    * Since it extends thread, it won't block the main thread.
    * */
    private class FetchViewModel_Thread extends Thread {

        public void run() {

            ArticleRepository store = new ArticleRepository();
            ArticleItemList searchResults = mFetchResults.getValue();
            ArticleItemList newResults = store.fetchArticles(url);
            searchResults.clear();

            if (newResults != null) {
                searchResults.addAll(newResults);
            }

            mFetchResults.postValue(searchResults);
        }
    }
}
