package com.example.truyentranhonline.coltroler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.truyentranhonline.R;

public class tongquan extends AppCompatActivity {
    private WebView webview;
    private static tongquan instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongquan);

        //load webview trỏ tới đường dẫn trang web nào đó
        webview=findViewById(R.id.webview);
        webview.loadUrl("https://goctruyen.com/truyen-tien-hiep/?gclid=Cj0KCQjwitPnBRCQARIsAA5n84ns4dizLqmrShviYlLLSz6_BFZmRUlJakr3Lv9iKO--hn1fZRb2vrAaAjTGEALw_wcB/");
        webview.setWebViewClient(new WebViewClient());
    }

    public static tongquan getInstance(){
        if(instance == null){
            instance = new tongquan();
        }
        return instance;
    }
}
