package com.spikan.BetMySalt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class StreamActivity extends Activity {

    WebView twitch;
    WebView chat;

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String channel = "http://twitch.tv/saltybet/embed?&autoplay=true";
        String chatURL = "http://twitch.tv/saltybet/chat";

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        twitch = (WebView) findViewById(R.id.twitch);
        twitch.getSettings().setJavaScriptEnabled(true);
        twitch.setWebViewClient(new MyWebViewClient());
                twitch.loadUrl(channel);

        chat = (WebView) findViewById(R.id.chat);
        chat.getSettings().setJavaScriptEnabled(true);
        chat.setWebViewClient(new MyWebViewClient());
            chat.loadUrl(chatURL);


        String saltyUrl = "http://saltybet.com";

        try {
            Document doc = Jsoup.connect(saltyUrl).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


}
