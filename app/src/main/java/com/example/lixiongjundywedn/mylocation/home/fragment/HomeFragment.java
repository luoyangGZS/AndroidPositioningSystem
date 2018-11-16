package com.example.lixiongjundywedn.mylocation.home.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lixiongjundywedn.mylocation.R;
import com.example.lixiongjundywedn.mylocation.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * 作者：洛阳
 * 邮箱：1845313665@qq.com
 * Created  on 2018/4/12.
 * <p>
 * 功能：首页fragment
 */
public class HomeFragment extends BaseFragment {

//    private LocationListener mLocationListener = null;
//    private LocationManager mLocationManager = null;
//    private String mProviderName = null;
//
//    private TextView LatitudeText = null;
////    private TextView LongitudeText = null;
////    private TextView AltitudeText = null;
////    private TextView AccuracyText = null;
////    private TextView TimeText = null;
////    private TextView SpeedText = null;
////    private TextView BearingText = null;

    private Button bt_test_example;

    @Override
    public View initView() {

        Log.e(TAG, "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        // findView(view);


        bt_test_example = view.findViewById(R.id.bt_test_example);
        //connectOkhttpGet();

        bt_test_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Test123Activity.class);
                mContext.startActivity(intent);
            }
        });


        return view;

    }   

    //
//    //s实例化七个TextView组件
////    private void findView(View view) {
////        LatitudeText = (TextView) view.findViewById(R.id.tv_Latitude1);
////        LongitudeText = (TextView) view.findViewById(R.id.tv_Longitude1);
////        AltitudeText = (TextView) view.findViewById(R.id.tv_Altitude1);
////        AccuracyText = (TextView) view.findViewById(R.id.tv_Accuracy1);
////        TimeText = (TextView) view.findViewById(R.id.tv_Time1);
////        SpeedText = (TextView) view.findViewById(R.id.tv_Speed1);
////        BearingText = (TextView) view.findViewById(R.id.tv_Bearing1);
////
////    }


    @SuppressLint("MissingPermission")
    public void initData() {

        Log.e(TAG, "主页面的Fragment的数据被初始化了");
        super.initData();
//        /////////////////////////////////////////////////////////
//        //获取LocationManager实例
//        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        //获取LocationListener监听的接口实例，并重写其中的4个回调方法
//        mLocationListener = new LocationListener() {
//
//            @Override
//            public void onLocationChanged(Location location) {
//                if (location != null) {
//                    LatitudeText.setText("纬度-Latitude :" + location.getLatitude());
//                    LongitudeText.setText("经度-Longitude :" + location.getLongitude());
//                    AltitudeText.setText("海拔-Altitude :" + location.getAltitude());
//                    AccuracyText.setText("精度-Accuracy :" + location.getAccuracy());
//                    TimeText.setText("时间-Time :" + location.getTime());
//                    SpeedText.setText("速度-Speed :" + location.getSpeed());
//                    BearingText.setText("方位-Speed :" + location.getBearing());
//
//                } else {
//
//                    Toast.makeText(mContext, "NO GPS", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                Log.v("GPs", "onStatusChanged");
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//                Log.v("GPs", "onProviderEnabled");
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                Log.v("GPs", "onProviderDisabled");
//
//            }
//        };
//         onStart();
//          onResume();
//           onPause();



        //////////////////////////////////////////////////////////////////////////////////////
    }


//public void onStart(){
//
//    Location lastKnownLocation = null;
//    //获取GPS_PROVIDER上一次的定位信息保存于lastKnownLocation中
//    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        // TODO: Consider calling
//        //    ActivityCompat#requestPermissions
//        // here to request the missing permissions, and then overriding
//        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//        //                                          int[] grantResults)
//        // to handle the case where the user grants the permission. See the documentation
//        // for ActivityCompat#requestPermissions for more details.
//        return;
//    }
//    lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//    mProviderName = LocationManager.GPS_PROVIDER;
//
//    if (!TextUtils.isEmpty(mProviderName)) {
//        //mLocationManager注册位置监听器mLocationListener
//        mLocationManager.requestLocationUpdates(mProviderName, 1000, 1, mLocationListener);
//    }
//
//    //将lastKnownLocation中的位置信息显示在相应的TextView中
//    if (lastKnownLocation != null) {
//        LatitudeText.setText("纬度-Latitude :" + lastKnownLocation.getLatitude());
//        LongitudeText.setText("经度-Longitude :" + lastKnownLocation.getLongitude());
//        AltitudeText.setText("海拔-Altitude :" + lastKnownLocation.getAltitude());
//        AccuracyText.setText("精度-Accuracy :" + lastKnownLocation.getAccuracy());
//        TimeText.setText("时间-Time :" + lastKnownLocation.getTime());
//        SpeedText.setText("速度-Speed :" + lastKnownLocation.getSpeed());
//        BearingText.setText("方位-Speed :" + lastKnownLocation.getBearing());
//
//    }
//
//}
////
//    @SuppressLint("MissingPermission")
//    public void onResume(){
//        super.onResume();
//        Log.v("GPS", "onResume.providerName : " + mProviderName);
//        /**mLocationManager注册位置监听器mLocationListener,这里要求在Activity的onResume()中注册的，
//         * 虽然在onStart()方法中已经注册过该监听器，但此出还是必须的，（参考说的）
//         *
//         * */
//
//        if (!TextUtils.isEmpty(mProviderName)) {
//            //mLocationManager注册位置监听器mLocationListener
//            mLocationManager.requestLocationUpdates(mProviderName, 1000, 1, mLocationListener);
//        }
//    }


//    public void onPause(){
//
//        super.onPause();
//        if(mLocationManager != null){
//            mLocationManager.removeUpdates(mLocationListener);
//
//        }
//    }

//    private void connectOkhttpGet(){
//
//        String url = "http://www.csdn.net/";
//        String url2 = "http://10.0.2.2:8080/my_web/demo.jsp";
//        String url3 = "http://baidu.com/";
//
//        OkHttpUtils
//                .get()
//                .url(url2)
//                .addParams("name", "jun")
//                .addParams("age", "12")
//                .build()
//                .execute(new StringCallback()
//                {
//                    /**
//                     *当联失败时回调
//                     * response:请求成功的数据
//                     * id :
//                     * */
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                            Log.e(TAG,"okhttpGet请求失败=="+e.getMessage());
//                    }
//
//
//                    /**
//                     *当联网成功时回调
//                     * response:请求成功的数据
//                     * id :
//                     * */
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e(TAG,"okhttpGet请求成功=="+response);
//
//                    }
//
//
//                });
 //   }


}
