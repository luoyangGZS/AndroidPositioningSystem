package com.example.lixiongjundywedn.mylocation.community.fragment;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者：洛阳
 * 邮箱：1845313665@qq.com
 * Created  on 2018/4/19.
 * <p>
 * 功能：
 */
 public class InitWebView {

    private  String ulr;
    private Context mContext;
    private WebView webView;

   public  InitWebView(String ulr,Context mContext){
       this.ulr = ulr;
       this.mContext = mContext;
      }




  public   WebView BackWebView(){

    //1.加载网页—H5,html,自定义浏览器（H5是html的扩展，javascript处理页面逻辑）
       webView = new WebView(mContext);
    WebSettings webSettings = webView.getSettings();

    //设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

    //不调用浏览器，自己定义浏览器
        webView.setWebViewClient(new WebViewClient());
        //优先使用缓存
      webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    //加载网络网页或本地网页
        webView.loadUrl(ulr);
        return webView;
   }


}
