package com.accounting.exception;

import org.greenrobot.greendao.DaoException;

/**
 * Created by 12191 on 2018/1/30.
 */

public class MyDaoException extends DaoException {
    public MyDaoException(String message){
        super(message);
    }
}
