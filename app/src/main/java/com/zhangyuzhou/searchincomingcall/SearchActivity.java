package com.zhangyuzhou.searchincomingcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String searchURL = intent.getStringExtra(IncomingCallListener.EXTRA_MESSAGE);
        Log.d("searchURL in SearchActivity=========", searchURL);

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://" + searchURL);
    }
}
