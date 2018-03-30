package com.accounting.db;

import com.accounting.daogen.DaoMaster;
import com.accounting.daogen.DaoSession;
import com.accounting.utils.MyConstant;

/**
 * Created by 12191 on 2018/1/29.
 */

public class GreenDaoManager {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static GreenDaoManager mInstance;   //单例

    private GreenDaoManager(){
        if (mInstance== null){
            DaoMaster.DevOpenHelper devOpenHelper=
                    new DaoMaster.DevOpenHelper(new GreenDaoContext(), MyConstant.DB_NAME,null);
            daoMaster= new DaoMaster(devOpenHelper.getWritableDatabase());
            daoSession= daoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance(){
        if (mInstance== null){
            synchronized (GreenDaoManager.class){
                if (mInstance== null){
                    mInstance= new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getDaoMaster(){
        return daoMaster;
    }
    public DaoSession getDaoSession(){
        return daoSession;
    }

    public DaoSession getNewSession(){
        daoSession= daoMaster.newSession();
        return daoSession;
    }
}
