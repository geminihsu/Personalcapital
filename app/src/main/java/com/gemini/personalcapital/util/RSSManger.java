package com.gemini.personalcapital.util;

import android.net.Uri;

import com.gemini.personalcapital.constant.RSSXMLTag;
import com.gemini.personalcapital.model.Post;
import com.gemini.personalcapital.model.PostItemList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.gemini.personalcapital.constant.RSSXMLTag.CATEGORY;
import static com.gemini.personalcapital.constant.RSSXMLTag.COMMENTS;
import static com.gemini.personalcapital.constant.RSSXMLTag.DESCRIPTION;
import static com.gemini.personalcapital.constant.RSSXMLTag.IMG_URL;
import static com.gemini.personalcapital.constant.RSSXMLTag.LINK;
import static com.gemini.personalcapital.constant.RSSXMLTag.PUBDATE;
import static com.gemini.personalcapital.constant.RSSXMLTag.TITLE;

public class RSSManger {

    public PostItemList fetchXML(final String urlString) {
        PostItemList itemList = new PostItemList();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();
            InputStream stream = conn.getInputStream();

            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, null);

            itemList = parseXMLAndStoreIt(myparser);
            stream.close();
        } catch (Exception e) {
        }


        return itemList;
    }

    public PostItemList parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = "";
        PostItemList result = new PostItemList();
        String currentTag = "";

        try {
            event = myParser.getEventType();
            Post post = new Post();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equals("item")) {
                            post = new Post();
                            currentTag = RSSXMLTag.IGNORETAG;
                        } else if (myParser.getName().equals(TITLE)) {
                            currentTag = TITLE;
                        } else if (myParser.getName().equals(COMMENTS)) {
                            currentTag = COMMENTS;
                        } else if (myParser.getName().equals(PUBDATE)) {
                            currentTag = PUBDATE;
                        } else if (myParser.getName().equals(CATEGORY)) {
                            currentTag = CATEGORY;
                        } else if (myParser.getName().equals(IMG_URL)) {
                            currentTag = IMG_URL;
                            String url = myParser.getAttributeValue(0);
                            post.setImageUrl(url);
                            post.setmCover(Uri.parse(url));
                        } else if (myParser.getName().equals(DESCRIPTION)) {
                            currentTag = DESCRIPTION;
                        } else if (myParser.getName().equals("link")) {
                            currentTag = LINK;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText().trim();
                        if (post != null) {
                            switch (currentTag) {
                                case TITLE:
                                    post.setTitle(text);
                                    break;
                                case COMMENTS:
                                    post.setComments(text);
                                    break;
                                case PUBDATE:
                                    post.setPubDate(text);
                                    break;
                                case CATEGORY:
                                    post.setCategory(text);
                                    break;
                                case DESCRIPTION:
                                    post.setDescription(text);
                                    break;
                                case LINK:
                                    post.setLink(text);
                                    break;
                                default:
                                    break;
                            }
                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("item")) {
                            result.add(post);
                        } else {
                            currentTag = RSSXMLTag.IGNORETAG;
                        }
                        break;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
