package com.gemini.personalcapital.network;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.gemini.personalcapital.model.ArticleItemList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.gemini.personalcapital.constant.Constant.SEARCH_URL;
import static junit.framework.TestCase.assertNotNull;

public class PostRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    ArticleRepository repository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new ArticleRepository();
    }

    @Test
    public void testNull() {
        ArticleItemList list = repository.fetchArticles(SEARCH_URL);
        assertNotNull(list);
    }
}