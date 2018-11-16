package com.example.lixiongjundywedn.mylocation.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.example.lixiongjundywedn.mylocation.community.fragment.InitWebView;

public class GaoDeLocationActivity extends AppCompatActivity {

    private  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initWebView();
        String url = "http://m.amap.com/";
        String url1 = "https://ditu.amap.com/";
        webView = new InitWebView(url1,this).BackWebView();

        setContentView(webView);
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
//            else
//            {
//                System.exit(0);//退出程序
//            }
        }
        return super.onKeyDown(keyCode, event);
    }
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KEYCODE_Back) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//    private void initWebView() {
//        //1.加载网页—H5,html,自定义浏览器（H5是html的扩展，javascript处理页面逻辑）
//         webView = new WebView(this);
//        WebSettings webSettings = webView.getSettings();
//
//        //设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
//
//        //不调用浏览器，自己定义浏览器
//        webView.setWebViewClient(new WebViewClient());
//
//        //加载网络网页或本地网页
//        webView.loadUrl("https://ditu.amap.com/");
//
//
//
//    }

}
