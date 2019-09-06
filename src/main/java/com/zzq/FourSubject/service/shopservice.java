package com.zzq.FourSubject.service;

import com.zzq.FourSubject.bean.shopattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzq.FourSubject.dao.shopdao;

import java.util.ArrayList;
import java.util.List;
import com.zzq.FourSubject.reptile.reptileall;
@Service
public class shopservice {
    @Autowired
    shopdao shopdao;
    //插入信息
    public void insert(){
        System.out.println("shopservice"+"---"+"insert");
        reptileall reptileall=new reptileall();
        List<shopattributes> insertlist = reptileall.basic();
//        for (shopattributes shopattributes : insertlist) {
//            System.out.println("城市"+shopattributes.getCity()+"人均:" + shopattributes.getAvgPrice() + " 店铺名称:" + shopattributes.getShopName() + "网址" + shopattributes.getShopUrl() + "星级" + shopattributes.getShopPower());
//        }
        shopdao.insert(insertlist);
    }
    //获取信息
    public List<shopattributes> getall(){
        System.out.println("shopservice"+"---"+"getall");
        List<shopattributes> insertlist = shopdao.getall();
        return insertlist;
    }
    //获取300条随机数据
    public List<shopattributes> getthree(){
        System.out.println("shopservice"+"---"+"getthree");
        List<shopattributes> insertlist = shopdao.getthree();
        return insertlist;
    }
    //获取城市数据
    public List<shopattributes> getcity(String city){
        System.out.println("shopservice"+"---"+"getcity"+city);
        List<shopattributes> insertlist = shopdao.getcity(city);
        return insertlist;
    }
    //删除所有信息
    public void deleteall(){
        System.out.println("shopservice"+"---"+"deleteall");
        shopdao.deleteall();
    }
    //根据id拿到一条数据
    public shopattributes getone(String id){
        return shopdao.getone(id);
    }

}
