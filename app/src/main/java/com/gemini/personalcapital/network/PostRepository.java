package com.gemini.personalcapital.network;

import com.gemini.personalcapital.constant.Constant;
import com.gemini.personalcapital.model.PostItemList;
import com.gemini.personalcapital.util.RSSManger;

public class PostRepository implements PostAPI {

    @Override
    public PostItemList refreshList() {
        RSSManger rss = new RSSManger();
        PostItemList results = rss.fetchXML(Constant.SEARCH_URL);
        return results;
    }
}

