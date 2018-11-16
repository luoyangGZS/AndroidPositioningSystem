package com.example.lixiongjundywedn.mylocation.home.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.CreateBucketResponse;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.util.BLog;
import com.example.lixiongjundywedn.mylocation.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import db.DBHelper;

import static android.content.ContentValues.TAG;

//AppCompatActivity
public class Test123Activity extends AppCompatActivity {

    private LocationListener mLocationListener = null;
    private LocationManager mLocationManager = null;
    private String mProviderName = null;
    Location lastKnownLocation = null;

    private TextView LatitudeText = null;
    private TextView LongitudeText = null;
    private TextView AltitudeText = null;
    private TextView AccuracyText = null;
    private TextView TimeText = null;
    private TextView SpeedText = null;
    private TextView BearingText = null;
    private String AccessKeyId ="f425876795e946209bea311294e8b006";
    private String SecretKey ="882b84a5afcc4f4eb59364c19c1b62ec";
    private String EndPoint ="http://luoyang.gz.bcebos.com";
    private String BucketName="luoyang223";
    private String ObjectKey="luoyang123";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test123);

        findView();
        //创建luoyanglocation.db数据库
        CreateDB();
        //位置改变就上传字符型数据
       inItbosdata();
        testQuery();

        //获取LocationManager实例
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取LocationListener监听的接口实例，并重写其中的4个回调方法
        mLocationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    LatitudeText.setText("纬度-Latitude :" + location.getLatitude());
                    LongitudeText.setText("经度-Longitude :" + location.getLongitude());
                    AltitudeText.setText("海拔-Altitude :" + location.getAltitude());
                    AccuracyText.setText("精度-Accuracy :" + location.getAccuracy());
                    TimeText.setText("时间-Time :" + location.getTime());
                    SpeedText.setText("速度-Speed :" + location.getSpeed());
                    BearingText.setText("方位-Speed :" + location.getBearing());



                } else {

                    Toast.makeText(Test123Activity.this, "NO GPS", Toast.LENGTH_LONG).show();
                }


                //位置更变就更新luoyanglocation.db数据库第1条
                DBHelper dbHelper = new DBHelper(Test123Activity.this,2);
                //获取连接
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                //执行更新
                ContentValues values = new ContentValues();
                values.put("latitude",location.getLatitude());
                values.put("longitude",location.getLongitude());
                values.put("altitude",location.getAltitude());
                values.put("accuracy",location.getAccuracy());
                values.put("time",location.getTime());
                values.put("speed",location.getSpeed());
                values.put("Bearing",location.getBearing());
                int updateCount = database.update("luoyang_location",values,"_id=?",new String[]{"1"});
                database.close();
                Toast.makeText(Test123Activity.this, "updateCount1 ="+updateCount, Toast.LENGTH_SHORT).show();

                //位置改变就上传字符型数据
                inItbosdata();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.v("GPs", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.v("GPs", "onProviderEnabled");

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.v("GPs", "onProviderDisabled");

            }
        };





    }


    private void inItbosdata(){
        //打开bossdk运行时log
        BLog.enableLog();

        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AccessKeyId, SecretKey));
        config.setEndpoint(EndPoint); //传入Bucket所在区域域名
        final BosClient client = new BosClient(config); //创建BOSClient实例 ;

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    //创建Bucket
                    CreateBucketResponse response = client.createBucket(BucketName);
                    // 新建一个Bucket，指定Bucket名称 ​
                    System.out.println(response.getLocation());
                    System.out.println(response.getName());

                    //上传Object
//                    File file = new File("/data/data/com.example.lixiongjundywedn.mylocation/databases/luoyanglocaton.db" );//上传文件的目录
//                    PutObjectResponse putObjectFromFileResponse = client.putObject(BucketName, ObjectKey, file);
//                    System.out.println(putObjectFromFileResponse.getETag());
//                    Log.e(TAG,"putObjectFromFileResponse.getETag()"+putObjectFromFileResponse.getETag());

                    // 以字符串上传Object
                    PutObjectResponse putObjectResponseFromString = client.putObject(BucketName, ObjectKey,  "  纬度-Latitude :" + lastKnownLocation.getLatitude()
                    +"   经度-Longitude :" + lastKnownLocation.getLongitude()+"   海拔-Altitude :" + lastKnownLocation.getAltitude()+"   精度-Accuracy :" +
                            lastKnownLocation.getAccuracy()+"  时间-Time :" + lastKnownLocation.getTime()+
                            "   速度-Speed :" + lastKnownLocation.getSpeed()+"   方位-Bearing :" + lastKnownLocation.getBearing());

                    Log.e(TAG,"以字符串上传反应putObjectResponseFromString.getETag()"+putObjectResponseFromString.getETag());

                    //查看Object
                    ListObjectsResponse list = client.listObjects(BucketName);

                    for (BosObjectSummary objectSummary : list.getContents()) {
                        System.out.println("ObjectKey: " + objectSummary.getKey());
                        Toast.makeText(Test123Activity.this, "ObjectKey"+ objectSummary.getKey(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,"ObjectKey目标文件钥匙:"+objectSummary.getKey());
                    }

                    // 获取Object
                    BosObject object = client.getObject(BucketName, ObjectKey);
                    // 获取ObjectMeta
                    ObjectMetadata meta = object.getObjectMetadata();
                    // 获取Object的输入流
                    InputStream objectContent = object.getObjectContent();
                    // 处理Object
                    FileOutputStream fos=new FileOutputStream("/data/data/com.example.lixiongjundywedn.mylocation/databases/luoyanglocaton223.db");//下载文件的目录/文件名
                    Log.e(TAG,"获取Object的输入流-保存成功");
                   byte[] buffer=new byte[2048];
                    int count=0;
                    while ((count=objectContent.read(buffer))>=0) {
                        fos.write(buffer,0,count);
                    }

                    // 关闭流
                    objectContent.close();
                    fos.close();
                    System.out.println(meta.getETag());
                    Log.e(TAG,"打印meta.getETag()日志:"+meta.getETag());
                    System.out.println(meta.getContentLength());

                }catch (BceServiceException e) {
                    Log.e(TAG, "BceServiceException出错了");
                    System.out.println("Error ErrorCode: " + e.getErrorCode());
                    System.out.println("Error RequestId: " + e.getRequestId());
                    System.out.println("Error StatusCode: " + e.getStatusCode());
                    System.out.println("Error Message: " + e.getMessage());
                    System.out.println("Error ErrorType: " + e.getErrorType());
                } catch (BceClientException e) {
                    System.out.println("Error Message: " + e.getMessage());
                    Log.e(TAG, "BceClientException发生出错了");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e(TAG, "IOException发生出错了");
                }



            }
        }).start();


    }

    //s实例化七个TextView组件
    private void findView() {
        LatitudeText = (TextView) findViewById(R.id.tv_Latitude);
        LongitudeText = (TextView) findViewById(R.id.tv_Longitude);
        AltitudeText = (TextView) findViewById(R.id.tv_Altitude);
        AccuracyText = (TextView) findViewById(R.id.tv_Accuracy);
        TimeText = (TextView) findViewById(R.id.tv_Time);
        SpeedText = (TextView) findViewById(R.id.tv_Speed);
        BearingText = (TextView) findViewById(R.id.tv_Bearing);

    }


    public void onStart() {
        super.onStart();


        //获取GPS_PROVIDER上一次的定位信息保存于lastKnownLocation中
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mProviderName = LocationManager.GPS_PROVIDER;

        if (!TextUtils.isEmpty(mProviderName)) {
            //mLocationManager注册位置监听器mLocationListener
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLocationManager.requestLocationUpdates(mProviderName, 100, 1, mLocationListener);
        }

        //将lastKnownLocation中的位置信息显示在相应的TextView中
        if (lastKnownLocation != null) {
            LatitudeText.setText("纬度-Latitude :" + lastKnownLocation.getLatitude());
            LongitudeText.setText("经度-Longitude :" + lastKnownLocation.getLongitude());
            AltitudeText.setText("海拔-Altitude :" + lastKnownLocation.getAltitude());
            AccuracyText.setText("精度-Accuracy :" + lastKnownLocation.getAccuracy());
            TimeText.setText("时间-Time :" + lastKnownLocation.getTime());
            SpeedText.setText("速度-Speed :" + lastKnownLocation.getSpeed());
            BearingText.setText("方位-Bearing :" + lastKnownLocation.getBearing());
            upDateOneDb();


        }



    }

    @SuppressLint("MissingPermission")
    public void onResume(){
        super.onResume();
        Log.v("GPS", "onResume.providerName : " + mProviderName);
        /**mLocationManager注册位置监听器mLocationListener,这里要求在Activity的onResume()中注册的，
         * 虽然在onStart()方法中已经注册过该监听器，但此出还是必须的，（参考说的）
         *
         * */

        if (!TextUtils.isEmpty(mProviderName)) {
            //mLocationManager注册位置监听器mLocationListener
            mLocationManager.requestLocationUpdates(mProviderName, 100, 1, mLocationListener);

        }
    }


    public void onPause(){

        super.onPause();
        if(mLocationManager != null){
            mLocationManager.removeUpdates(mLocationListener);

        }
    }

    /**
     * 什么时候创建数据库?
     *1)数据库文件不存在时
     * 2)连接到数据库
     *
     * */
    public void CreateDB(){

        DBHelper dbHelper = new DBHelper(this,1);
        //获取连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Toast.makeText(this, "Sqlite数据库已创建", Toast.LENGTH_SHORT).show();


    }

    public void testInsert(){

        DBHelper dbHelper = new DBHelper(this,2);
        //获取连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //执行插入
        ContentValues values = new ContentValues();
        values.put("latitude",lastKnownLocation.getLatitude());
        values.put("longitude",lastKnownLocation.getLongitude());
        values.put("altitude",lastKnownLocation.getAltitude());
        values.put("accuracy",lastKnownLocation.getAccuracy());
        values.put("time",lastKnownLocation.getTime());
        values.put("speed",lastKnownLocation.getSpeed());
        values.put("Bearing",lastKnownLocation.getBearing());
        long id =  database.insert("luoyang_location",null,values);
        database.close();
        Toast.makeText(this, "id ="+id, Toast.LENGTH_SHORT).show();
    }


   public void upDateOneDb(){

        DBHelper dbHelper = new DBHelper(this,2);
        //获取连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //执行更新
        ContentValues values = new ContentValues();
        values.put("latitude",lastKnownLocation.getLatitude());
        values.put("longitude",lastKnownLocation.getLongitude());
        values.put("altitude",lastKnownLocation.getAltitude());
        values.put("accuracy",lastKnownLocation.getAccuracy());
        values.put("time",lastKnownLocation.getTime());
        values.put("speed",lastKnownLocation.getSpeed());
        values.put("Bearing",lastKnownLocation.getBearing());
        int updateCount = database.update("luoyang_location",values,"_id=?",new String[]{"2"});
        database.close();
        Toast.makeText(this, "updateCount ="+updateCount, Toast.LENGTH_SHORT).show();
    }


    public void testQuery(){

        //得到对象
        DBHelper dbHelper = new DBHelper(this,2);
        //获取连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //执行query
        Cursor cursor = database.query("luoyang_location",null,null,null,null,null,null);

        int count = cursor.getCount();
        //取出cursor中的所有的数据
        while(cursor.moveToNext()){
            //_id
            int id = cursor.getInt(0);
            Double latitude =cursor.getDouble(1);
            Double longitude =cursor.getDouble(2);
            Double altitude =cursor.getDouble(3);
            Double accuracy =cursor.getDouble(4);
            Double time =cursor.getDouble(5);
            Double speed =cursor.getDouble(6);
            Double Bearing =cursor.getDouble(7);
                Log.e("TAG查询的信息",latitude+"--"+longitude+"--"+altitude+"--"+accuracy+"--"+time+"--"+
                        "--"+speed+"--"+Bearing);
            Toast.makeText(this, "查询的信息 ="+latitude+"--"+longitude+"--"+altitude+"--"+accuracy+"--"+time+"--"+
                    "--"+speed+"--"+Bearing, Toast.LENGTH_LONG).show();
        }

        database.close();
        Toast.makeText(this, "cursor查询数据 "+count+"条", Toast.LENGTH_SHORT).show();


    }

}
