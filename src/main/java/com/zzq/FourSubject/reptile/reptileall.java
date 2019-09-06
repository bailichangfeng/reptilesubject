package com.zzq.FourSubject.reptile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.zzq.FourSubject.bean.shopbeans;
import com.zzq.FourSubject.bean.shopattributes;

import javax.security.auth.login.Configuration;

public class reptileall {
//            geturl(USER_AGENT_LIST,"西安-ad66274c7f5f8d27ffd7f6b39ec447b608e25c702ab1b810071e8e2c39502be1");
//             for (shopattributes shopattributes : listattributes) {
//                System.out.println("人均:" + shopattributes.getAvgPrice() + " 店铺名称:" + shopattributes.getShopName() + "网址" + shopattributes.getShopUrl() + "星级" + shopattributes.getShopPower());
//            }

    public reptileall() {

    }

    public List<shopattributes> basic(){
        List<shopbeans> list = new ArrayList<>();
        //listattribute是所有需要的最终数据
        List<shopattributes> listattributes = new ArrayList<>();
        List<String> USER_AGENT_LIST = new ArrayList();
        //请求头
        USER_AGENT_LIST.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1");
        USER_AGENT_LIST.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6");
        USER_AGENT_LIST.add("Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11");
        USER_AGENT_LIST.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6");
        USER_AGENT_LIST.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1");
        USER_AGENT_LIST.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5");
        //城市列表
        Map list_city = new HashMap();
        list_city.put("郑州","3785398756032fb8ebe4c2b1ed12d6fc30aacdebee4c4c9365dc18972daccaf7");
        list_city.put("上海","fce2e3a36450422b7fad3f2b90370efd71862f838d1255ea693b953b1d49c7c0");
        list_city.put("北京","d5036cf54fcb57e9dceb9fefe3917fff71862f838d1255ea693b953b1d49c7c0");
        list_city.put("广州","e749e3e04032ee6b165fbea6fe2dafab71862f838d1255ea693b953b1d49c7c0");
        list_city.put("深圳","e049aa251858f43d095fc4c61d62a9ec71862f838d1255ea693b953b1d49c7c0");
        list_city.put("天津","2e5d0080237ff3c8f5b5d3f315c7c4a508e25c702ab1b810071e8e2c39502be1");
        list_city.put("杭州","91621282e559e9fc9c5b3e816cb1619c71862f838d1255ea693b953b1d49c7c0");
        list_city.put("南京","d6339a01dbd98141f8e684e1ad8af5c871862f838d1255ea693b953b1d49c7c0");
        list_city.put("苏州","536e0e568df850d1e6ba74b0cf72e19771862f838d1255ea693b953b1d49c7c0");
        list_city.put("成都","c950bc35ad04316c76e89bf2dc86bfe071862f838d1255ea693b953b1d49c7c0");
        list_city.put("武汉","d96a24c312ed7b96fcc0cedd6c08f68c08e25c702ab1b810071e8e2c39502be1");
        list_city.put("重庆","6229984ceb373efb8fd1beec7eb4dcfd71862f838d1255ea693b953b1d49c7c0");
        list_city.put("西安","ad66274c7f5f8d27ffd7f6b39ec447b608e25c702ab1b810071e8e2c39502be1");
        Iterator it = list_city.entrySet().iterator() ;
        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry) it.next() ;
            Object key = entry.getKey() ;
            Object value = entry.getValue() ;
            String string = key+"-"+value;
            System.out.println(string.split("-")[0]+"----"+string.split("-")[1]);
            listattributes=geturl(USER_AGENT_LIST,key+"-"+value,listattributes,list);
        }

        return listattributes;
    }
    public List<shopattributes> geturl(List<String> USER_AGENT_LIST,String city,List<shopattributes> listattributes,List<shopbeans> list){
        Document doc = null;
        String cityname = city.split("-")[0];
        String rankid = city.split("-")[1];
        int flag =0;

        try{
            String head = USER_AGENT_LIST.get((int)(Math.random()*USER_AGENT_LIST.size()));
            System.out.println(head);
            String url = "http://www.dianping.com/mylist/ajax/shoprank?rankId="+rankid;
            System.out.println(url);
            doc=Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent(head)
                    .timeout(5000).get();
            Elements body = doc.getElementsByTag("body");
            String text = body.text();
            text = text.substring(text.indexOf("["),text.indexOf("]")+1);
            System.out.println(text);
            JSONArray jsonArray = JSONArray.fromObject(text);
            for(int i = 0;i<jsonArray.size();i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
                shopbeans shopbeans = (shopbeans) JSONObject.toBean(jsonObject, shopbeans.class);
                list.add(shopbeans);
            }
            for (shopbeans shopbean : list) {
                shopattributes shopattributes = new shopattributes();
                shopattributes.setAvgPrice(shopbean.getAvgPrice());
                shopattributes.setCity(cityname);
                shopattributes.setMainCategoryName(shopbean.getMainCategoryName());
                shopattributes.setMainRegionName(shopbean.getMainRegionName());
                shopattributes.setRefinedScore1(shopbean.getRefinedScore1());
                shopattributes.setRefinedScore2(shopbean.getRefinedScore2());
                shopattributes.setRefinedScore3(shopbean.getRefinedScore3());
                shopattributes.setShopId(shopbean.getShopId());
                shopattributes.setShopName(shopbean.getShopName());
                shopattributes.setShopPower(shopbean.getShopPower());
                shopattributes.setShopUrl("http://www.dianping.com/shop/"+shopbean.getShopId());
                listattributes.add(shopattributes);
            }
        }catch (IOException e){
            System.out.println("io异常");
        }
        return listattributes;
    }

}