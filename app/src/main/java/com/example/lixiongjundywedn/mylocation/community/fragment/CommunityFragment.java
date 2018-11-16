package com.example.lixiongjundywedn.mylocation.community.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lixiongjundywedn.mylocation.R;
import com.example.lixiongjundywedn.mylocation.app.BaiduLocationActivity;
import com.example.lixiongjundywedn.mylocation.app.GaoDeLocationActivity;
import com.example.lixiongjundywedn.mylocation.app.GoogleLocationActivity;
import com.example.lixiongjundywedn.mylocation.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * 作者：洛阳
 * 邮箱：1845313665@qq.com
 * Created  on 2018/4/12.
 * <p>
 * 功能：小生态fragment
 */
public class CommunityFragment extends BaseFragment {

    private Button bt_one_gaode;
    private Button bt_two_baidu;
    private Button bt_three_google;

    @Override
    public View initView() {

        Log.e(TAG,"小生态页面的Fragment的UI被初始化了");

         View view = View.inflate(mContext, R.layout.fragment_community,null);
        bt_one_gaode = view.findViewById(R.id.bt_one_gaode);
        bt_two_baidu = view.findViewById(R.id.bt_two_baidu);
        bt_three_google = view.findViewById(R.id.bt_three_google);

//        String url = "https://ditu.amap.com/";
//        WebView view = new InitWebView(url,mContext).BackWebView();

        //设置点击事件
        initListener();
         return view;
    }



    public void initData(){

        Log.e(TAG,"小生态的Fragment的数据被初始化了");
        super.initData();
    }

    private void initListener() {
        bt_one_gaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1）创建Intent对象（显示）
                Intent intent =new Intent(mContext, GaoDeLocationActivity.class);
                //2）启动Activity
                mContext.startActivity(intent);


            }
        });

        bt_two_baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1）创建Intent对象（显示）
                Intent intent =new Intent(mContext, BaiduLocationActivity.class);
                //2）启动Activity
                mContext.startActivity(intent);


            }
        });

        bt_three_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1）创建Intent对象（显示）
                Intent intent =new Intent(mContext, GoogleLocationActivity.class);
                //2）启动Activity
                mContext.startActivity(intent);


            }
        });

    }



}
