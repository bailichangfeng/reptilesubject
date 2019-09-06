package com.zzq.FourSubject.bean;

import lombok.Data;

@Data
public class shopattributes {
    //    分类名称
    private String mainRegionName;
    //    所在区域名称
    private String mainCategoryName;
    //    口味评分
    private String refinedScore1;
    //    环境评分
    private String refinedScore2;
    //    服务评分
    private String refinedScore3;
    //    商品编号
    private String shopId;
    //    商铺名称
    private String shopName;
    //    所在城市
    private String city;
    //    人均消费
    private String avgPrice;
    //    商铺星级
    private String shopPower;
    //     商铺网址
    private String shopUrl;
}
