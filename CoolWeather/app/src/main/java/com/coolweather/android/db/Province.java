package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class Province extends DataSupport {

    private int id;
    private String proviceName;
    private int proviceCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public void setProviceCode(int proviceCode) {
        this.proviceCode = proviceCode;
    }

    public int getId() {
        return id;
    }

    public String getProviceName() {
        return proviceName;
    }

    public int getProviceCode() {
        return proviceCode;
    }
}
