package org.sunbird.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.sunbird.BuildConfig;
import org.sunbird.R;

public class KeyCloakRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keycloak);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(BuildConfig.REDIRECT_BASE_URL + "/oauth2callback")) {
                    Uri data = Uri.parse(url);
                    Intent intent = new Intent(KeyCloakRequestActivity.this, KeyCloakResponseActivity.class);
                    intent.setData(data);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        String urlToLaunch = getIntent().getData().toString();
        webView.loadUrl(urlToLaunch);
    }
}
