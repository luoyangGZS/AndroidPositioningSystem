package com.example.lixiongjundywedn.mylocation.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lixiongjundywedn.mylocation.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ib_weibo;
    private ImageButton ib_qq;
    private ImageButton ib_wechat;

    private int count;
    private String screen_name;
    private String profile_image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFindViews();

    }


    private void  initFindViews(){

        //返回键设置点击事件
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        //密码是否可见设置点击事件
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);

        // /登录设置点击事件
        btnLogin = (Button) findViewById(R.id.btn_login);

        //其他登录设置点击事件
        ib_weibo = (ImageButton) findViewById(R.id.ib_weibo);
        ib_qq = (ImageButton) findViewById(R.id.ib_qq);
        ib_wechat = (ImageButton) findViewById(R.id.ib_wechat);

        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ib_weibo.setOnClickListener(this);
        ib_qq.setOnClickListener(this);
        ib_wechat.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        if (v == ibLoginBack) {
            finish();
        } else if (v == ibLoginVisible) {

            count++;
            if (count % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }
        } else if (v == btnLogin) {
            Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();

        } else if (v == ib_weibo) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
            Toast.makeText(this,"微博登录",Toast.LENGTH_SHORT).show();
        } else if (v == ib_qq) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
            Toast.makeText(this,"QQ登录",Toast.LENGTH_SHORT).show();
        } else if (v == ib_wechat) {
//            mShareAPI = UMShareAPI.get(this);
//            mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
            Toast.makeText(this,"微信登录",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
