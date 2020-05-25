package com.linktrust.student.uidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.linktrust.student.uidemo.view.RefreshScrollView;

public class ThirdActivity extends AppCompatActivity implements RefreshScrollView.RefreshListener,View.OnClickListener {
    private RefreshScrollView mRefreshScrollView;
    private WebView mWebView;
    private TextView mHeadView;
    private TextView tv1;
    private TextView transion_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();
        mRefreshScrollView.setHeadView(mHeadView);
        mRefreshScrollView.setListsner(this);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://www.sina.com.cn//");

        //测量头View的高度
        ViewTreeObserver vto = transion_view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                transion_view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height_view = transion_view.getHeight();
                mRefreshScrollView.setHeight_view(height_view);

            }
        });

    }


    private void initView() {
        mRefreshScrollView = findViewById(R.id.rf);
        mWebView = findViewById(R.id.web);
        mHeadView = findViewById(R.id.headView);
        transion_view = findViewById(R.id.transion_view);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
    }




    @Override
    public void startRefresh() {
        startActivity(new Intent(ThirdActivity.this,LoginActivity.class));
        mRefreshScrollView.stopRefresh();
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this,"按钮被点击",Toast.LENGTH_SHORT).show();
    }
}
