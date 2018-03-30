package com.accounting.db;

import com.accounting.daogen.CompanyEntityDao;
import com.accounting.entity.CompanyEntity;
import com.accounting.exception.MyDaoException;
import com.accounting.utils.AllUtils;

import java.util.List;

/**
 * Created by 12191 on 2018/1/29.
 */

public class CompanyDbHelper {
    private static volatile CompanyDbHelper dbHelper;
    private CompanyEntityDao dao;

    private CompanyDbHelper(){
        dao= GreenDaoManager.getInstance().getDaoSession().getCompanyEntityDao();
    }

    public static CompanyDbHelper getInstance(){
        if (dbHelper== null){
            synchronized (CompanyDbHelper.class){
                if (dbHelper== null){
                    dbHelper= new CompanyDbHelper();
                }
            }
        }
        return dbHelper;
    }

    public void saveCompany(CompanyEntity bean) throws MyDaoException{
        dao.insertOrReplace(bean);
    }

    public CompanyEntity queryCompanyByName(String name) throws MyDaoException{
        return dao.queryBuilder().where(CompanyEntityDao.Properties.Name.eq(name)).uniqueOrThrow();
    }

    public CompanyEntity queryCompanyById(long id) throws MyDaoException{
        //TODO 查询id为什么用Name
        return dao.queryBuilder().where(CompanyEntityDao.Properties.Name.eq(id)).uniqueOrThrow();
    }

    public CompanyEntity queryCompany() throws MyDaoException{
        List<CompanyEntity> lists= dao.queryBuilder().list();
        if (AllUtils.isEmpty(lists)){
            return null;
        }
        return lists.get(0);
    }
}
