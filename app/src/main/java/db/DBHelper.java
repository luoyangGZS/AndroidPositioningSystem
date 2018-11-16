package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 作者：洛阳
 * 邮箱：1845313665@qq.com
 * Created  on 2018/4/26.
 * <p>
 * 功能：数据库操作的帮助类
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context , int verison){
        super(context,"luoyanglocation.db",null,1);



    }


    /**
     *当数据库创建时，调用一次
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG","DBHelper-oncreate");
        db.execSQL("create table luoyang_location ( _id integer primary key autoincrement, latitude double, " +
                "longitude double,altitude double,accuracy double,time datetime,speed double,Bearing double)");

        //插入一组初始化数据
        db.execSQL("insert into luoyang_location (latitude,longitude,altitude,accuracy,time,speed,Bearing) " +
                "values(25.859220027923000,114.92407858371000,74.9747314453000,61.000,1524707484000,0,195.46800)");//插入一组初始化数据
        db.execSQL("insert into luoyang_location (latitude,longitude,altitude,accuracy,time,speed,Bearing) " +
                "values(25.859220027923584,114.92407858371735,74.9747314453125,61.104,1524707484585,0,195.46875)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //当版本更新时调用
        Log.i("TAG","DBHelper-onUpgrade");


    }
}
