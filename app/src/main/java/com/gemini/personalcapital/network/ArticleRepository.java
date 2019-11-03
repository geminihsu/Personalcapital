package com.gemini.personalcapital.network;

import com.gemini.personalcapital.model.ArticleItemList;
import com.gemini.personalcapital.util.RSSManger;

public class ArticleRepository implements ArticleAPI {

    /*
    * It will return the Articles list after RSSManger parse HTML tag
    * */
    @Override
    public ArticleItemList fetchArticles(String url) {
        RSSManger rss = new RSSManger();
        ArticleItemList results = rss.fetchXML(url);
        return results;
    }
}

