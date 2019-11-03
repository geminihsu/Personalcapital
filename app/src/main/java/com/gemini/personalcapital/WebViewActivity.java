package com.gemini.personalcapital;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.gemini.personalcapital.constant.Constant.PROGRESS_LOADING;
import static com.gemini.personalcapital.constant.Constant.WEBSITE_ADDRESS;
import static com.gemini.personalcapital.constant.Constant.WEBSITE_TITLE;

public class WebViewActivity extends AppCompatActivity {
    private ProgressDialog progressDialog_loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (progressDialog_loading == null) {
            progressDialog_loading = ProgressDialog.show(this, "",
                    PROGRESS_LOADING, true);
        }

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            setTitle(bundle.getString(WEBSITE_TITLE));
            WebView theWebPage = new WebView(this);
            theWebPage.getSettings().setJavaScriptEnabled(true); // enable javascript

            final Activity activity = this;

            theWebPage.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                }
                @TargetApi(android.os.Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    if(progressDialog_loading != null) {
                        progressDialog_loading.dismiss();
                        progressDialog_loading = null;
                    }
                    super.onPageFinished(view, url);
                }
            });

            theWebPage.loadUrl(bundle.getString(WEBSITE_ADDRESS));
            setContentView(theWebPage);

        }
    }
}
