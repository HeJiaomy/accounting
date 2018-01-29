package com.accounting.entity;

/**
 * Created by 12191 on 2018/1/26.
 */

public class BaseEntity<T> {
    public T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "response=" + response +
                '}';
    }
}
