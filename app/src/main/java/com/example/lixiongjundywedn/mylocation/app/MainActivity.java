package com.example.lixiongjundywedn.mylocation.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.lixiongjundywedn.mylocation.R;
import com.example.lixiongjundywedn.mylocation.base.BaseFragment;
import com.example.lixiongjundywedn.mylocation.community.fragment.CommunityFragment;
import com.example.lixiongjundywedn.mylocation.home.fragment.HomeFragment;
import com.example.lixiongjundywedn.mylocation.user.fragment.UserFragment;

import java.util.ArrayList;

import static com.example.lixiongjundywedn.mylocation.R.layout.activity_main;

public class MainActivity extends FragmentActivity {


    private RadioGroup rg_main;


    //转载多个Fragment的实例集合
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragemnt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        rg_main = findViewById(R.id.rg_main);

        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        initListener();






    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home://主页
                        position = 0;
                        break;

                    case R.id.rb_community://生态
                        position = 1;
                        break;

                    case R.id.rb_user://用户
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;

                }

                BaseFragment baseFragment = getFragment(position);
                
                switchFragment(tempFragemnt, baseFragment);

            }

       
        });
        //一开始选定首页
        rg_main.check(R.id.rb_home);

    }


    //根据位置取不同的Fragment
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }


/**
*功能：
*@param: fromFragment 上次显示的Fragment
*@param: nextFragment 当前正要显示的Fragment
*@return:
**/
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


    //添加是要按照顺序
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new UserFragment());
    }

}
