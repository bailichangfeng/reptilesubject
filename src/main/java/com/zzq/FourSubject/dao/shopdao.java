package com.zzq.FourSubject.dao;

import java.util.List;
import com.zzq.FourSubject.bean.shopattributes;
public interface shopdao {


    //插入信息
    void insert(List<shopattributes> list);

    //拿到所有信息
    List<shopattributes> getall();

    //拿到300条随机数据
    List<shopattributes> getthree();

    //拿到城市数据
    List<shopattributes> getcity(String city);

    //删除所有信息
    void deleteall();

    //删除所有信息
    shopattributes getone(String id);
}

