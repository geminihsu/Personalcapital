package com.gemini.personalcapital.network;
import com.gemini.personalcapital.model.ArticleItemList;

public interface ArticleAPI {
    ArticleItemList fetchArticles(String url);
}
