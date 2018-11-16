package com.example.lixiongjundywedn.mylocation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：洛阳
 * 邮箱：1845313665@qq.com
 * Created  on 2018/4/12.
 * <p>
 * 功能：基类Fragment
 * 首页HomeFragment
 * 小生态Community
 * 用户User
 * 都要继承该类
 */
public abstract class BaseFragment extends Fragment {


        public Context mContext;

        //该类被创建时被回调
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mContext = getActivity();
        }


      /**
      *功能：当视图被创建时回调
      *@param:
      *@param:
      *@return:
      **/
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup
                container, Bundle savedInstanceState) {
            return initView();
        }

            /**
             * 由子类实现，实现特有视图效果
             * @return
             */
            public abstract View initView();


        /**
        *功能：当Activity 被创建时回调该方法
        *@param:
        *@param:
        *@return:
        **/
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            initData();
        }



        /**
         * 初始化数据
         * 当子类需要联网请求数据的时候，可以重写该方法，
         * 在该方法中实现联网请求
         */
        public void initData() {
        }
    }

