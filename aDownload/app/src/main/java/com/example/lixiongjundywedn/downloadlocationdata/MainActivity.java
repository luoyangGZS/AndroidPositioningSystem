package com.example.lixiongjundywedn.downloadlocationdata;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.util.BLog;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TextView tv_data;

    private String AccessKeyId ="f425876795e946209bea311294e8b006";
    private String SecretKey ="882b84a5afcc4f4eb59364c19c1b62ec";
    private String EndPoint ="http://luoyang.gz.bcebos.com";
    private String BucketName="luoyang223";
    private String ObjectKey="luoyang123";
    private Handler handler = new Handler();

    private Runnable task = new Runnable() {
        public void run() {
            // TODO Auto-generated method stub
            handler.postDelayed(this,1000);//设置延迟时间，此处是1秒
            //需要执行的代码
            getIotBucketData();



            new Thread() {

                public void run() {

                    //这里执行耗时操作，完成之后更新UI
                    runOnUiThread(new Runnable() {

                        public void run() {
                            //更新UI
//

                            try {
                                readUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            }.start();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_data = findViewById(R.id.tv_data);
        handler.postDelayed(task,4*1000);//延迟调用
        handler.post(task);//立即调用

        //定时任务执行，更新显示数据
 //       new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(MainActivity.this,"定时更新时间到...",Toast.LENGTH_SHORT);
//                try {
//                    readData();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Looper.loop();
//            }
//        },3000);

    }



    //拿到百度IOT bucket数据并保存为
    public void getIotBucketData(){


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
                    // CreateBucketResponse response = client.createBucket(BucketName);
                    // 新建一个Bucket，指定Bucket名称 ​
                    //System.out.println(response.getLocation());
                    // System.out.println(response.getName());

                    //上传Object
//                    File file = new File("/data/data/com.example.lixiongjundywedn.mylocation/databases/luoyanglocaton.db" );//上传文件的目录
//                    PutObjectResponse putObjectFromFileResponse = client.putObject(BucketName, ObjectKey, file);
//                    System.out.println(putObjectFromFileResponse.getETag());
//                    Log.e(TAG,"putObjectFromFileResponse.getETag()"+putObjectFromFileResponse.getETag());

//                        // 以字符串上传Object
//                        PutObjectResponse putObjectResponseFromString = client.putObject(BucketName, ObjectKey,  "  纬度-Latitude :" + lastKnownLocation.getLatitude()
//                                +"   经度-Longitude :" + lastKnownLocation.getLongitude()+"   海拔-Altitude :" + lastKnownLocation.getAltitude()+"   精度-Accuracy :" +
//                                lastKnownLocation.getAccuracy()+"  时间-Time :" + lastKnownLocation.getTime()+
//                                "   速度-Speed :" + lastKnownLocation.getSpeed()+"   方位-Bearing :" + lastKnownLocation.getBearing());

                    //  Log.e(TAG,"以字符串上传反应putObjectResponseFromString.getETag()"+putObjectResponseFromString.getETag());

                    //查看Object
                    ListObjectsResponse list = client.listObjects(BucketName);

                    for (BosObjectSummary objectSummary : list.getContents()) {
                        System.out.println("ObjectKey: " + objectSummary.getKey());
                        //Toast.makeText(MainActivity.this, "ObjectKey"+ objectSummary.getKey(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,"ObjectKey目标文件钥匙:"+objectSummary.getKey());
                    }

                    // 获取Object
                    BosObject object = client.getObject(BucketName, ObjectKey);
                    // 获取ObjectMeta
                    ObjectMetadata meta = object.getObjectMetadata();
                    // 获取Object的输入流
                    InputStream objectContent = object.getObjectContent();
                    // 处理Object
                    FileOutputStream fos=new FileOutputStream("/data/data/com.example.lixiongjundywedn.downloadlocationdata/luoyanglocaton223.txt");//下载文件的目录/文件名
                    Log.e(TAG,"获取Object的输入流-保存成功");
                    //Toast.makeText(MainActivity.this,"百度IOT获取数据并保存成功",Toast.LENGTH_SHORT).show();
                    byte[] buffer=new byte[2048];
                    int count=0;
                    while ((count=objectContent.read(buffer))>=0) {
                        fos.write(buffer,0,count);

                    }

                    // 关闭流
                    objectContent.close();
                    //fos.close();
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

        //读取文件，并显示
        public void readUpdate() throws Exception {



        //得到文件路径
        String filePath = "/data/data/com.example.lixiongjundywedn.downloadlocationdata/luoyanglocaton223.txt";
        //创建FileInputStream
            FileInputStream fis = new FileInputStream(filePath);
            //读取数据，成String
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len=fis.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
        String content = baos.toString();
        tv_data.setText(content);
            Log.e(TAG,"获取Object的数据-读取显示成功");
            Toast.makeText(MainActivity.this, "更新UI数据成功", Toast.LENGTH_SHORT).show();
        }

}



