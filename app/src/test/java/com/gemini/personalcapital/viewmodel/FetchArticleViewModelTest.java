package com.gemini.personalcapital.viewmodel;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.gemini.personalcapital.constant.Constant;
import com.gemini.personalcapital.model.ArticleItemList;
import com.gemini.personalcapital.network.ArticleAPI;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class FetchArticleViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    ArticleAPI apiClient;

    @Mock
    Observer<ArticleItemList> observer;

    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    private FetchArticleViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new FetchArticleViewModel();
        viewModel.getSearchResults().observeForever(observer);
    }

    @Test
    public void testNull() {
        when(apiClient.fetchArticles(Constant.SEARCH_URL)).thenReturn(null);
        assertNotNull(viewModel.getSearchResults());
        assertTrue(viewModel.getSearchResults().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        when(apiClient.fetchArticles(Constant.SEARCH_URL)).thenReturn(new ArticleItemList());
        viewModel.getSearchResults();
        assertNotNull(viewModel.getSearchResults());
    }

    @After
    public void tearDown() throws Exception {
        apiClient = null;
        viewModel = null;
    }
}