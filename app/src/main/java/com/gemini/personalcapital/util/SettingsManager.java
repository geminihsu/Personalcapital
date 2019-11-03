package com.gemini.personalcapital.util;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SettingsManager {

    private static SettingsManager mInstance;
    private final Context mContext;
    private boolean mHighQualityPreview;

    public static SettingsManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SettingsManager(context);
        }
        return mInstance;
    }

    private SettingsManager(Context context) {
        mContext = context.getApplicationContext();
        init();
    }


    public boolean isHighQualityPreview() {
        return mHighQualityPreview;
    }

    private void init() {

        try {

            File settings = new File(mContext.getFilesDir(), "settings.xml");

            if (!settings.exists()) {
                return;
            }

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(settings);
            NodeList HQPreview = doc.getElementsByTagName("high_quality_preview");
            if (HQPreview.getLength() > 0) {
                mHighQualityPreview = Boolean.parseBoolean( HQPreview.item(0).getTextContent() );
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

}
