package com.accounting.db;

import com.accounting.daogen.BillEntityDao;
import com.accounting.entity.BillEntity;
import com.accounting.exception.MyDaoException;

import org.greenrobot.greendao.query.QueryBuilder;

import static com.accounting.daogen.BillEntityDao.Properties.BillCustomerNum;
import static com.accounting.daogen.BillEntityDao.Properties.CustomerName;

/**
 * Created by Administrator on 2018/3/19.
 */

public class BillingDbHelper {
    //volatile只能让被它修饰内容具有可见性，但不能保证它具有原子性,
    // 非原子操作都会存在线程安全问题，需要我们使用同步技术（sychronized）来让它变成一个原子操作。
    private static volatile BillingDbHelper dbHelper;
    private BillEntityDao dao;
    private QueryBuilder<BillEntity> queryBuilder;

    public BillingDbHelper() {
        dao= GreenDaoManager.getInstance().getDaoSession().getBillEntityDao();
        queryBuilder= dao.queryBuilder();
    }

    public static  BillingDbHelper getInstance() {
        if (dbHelper== null){
            synchronized (BillingDbHelper.class){
                if (dbHelper== null){
                    dbHelper= new BillingDbHelper();
                }
            }
        }
        return dbHelper;
    }

    //unique没有查询结果返回null，uniqueOrThrow没有查询结果抛出异常
    public BillEntity queryBillByName(String name) throws MyDaoException{
        return queryBuilder.where(CustomerName.eq(name)).uniqueOrThrow();
    }
    public BillEntity queryBillByCode(String code) throws MyDaoException{
        return dao.queryBuilder().where(BillCustomerNum.eq(code)).unique();
    }

}
