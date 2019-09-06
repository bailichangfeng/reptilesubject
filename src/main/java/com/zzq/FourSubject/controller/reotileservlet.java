package com.zzq.FourSubject.controller;
import com.zzq.FourSubject.reptile.hadoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zzq.FourSubject.service.shopservice;
import com.zzq.FourSubject.bean.shopattributes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class reotileservlet {
    @Autowired
    shopservice shopservice;
    hadoop hadoop;


    //获取300条随机数据
    @RequestMapping(value = "getthree")
    public String getthree(ModelMap modelMap){
        List<shopattributes> insertlist = shopservice.getthree();
        modelMap.addAttribute("insertlist",insertlist);
        return "hellojava";
    }


    //hadoop实时计算
    @RequestMapping(value = "hadoop")
    public String hadoop(ModelMap modelMap){
        String str;
        String ids="";
        List<shopattributes> insertlist = new ArrayList<>();
        try {
            //hadoop运行有限制,每次项目启动hadoopsessionid唯一,调用过后就不能在进行下一次调用运行了
            //会报下面的catch错误,所以进行判断,如果没执行hadoop的话id的长度会为1,拿到上次hadoop执行玩的结果文件来使用,短期内数据不会有太大的更新.
            hadoop.hadoop1();
            File file = new File("");
            String path = file.getAbsolutePath()+"/src/main/resources/static/txt/data/part-r-00000";
            BufferedReader in = new BufferedReader(new FileReader(path));
            while ((str = in.readLine()) != null) {
                ids=ids+","+str.trim();
            }
        }catch (Exception e){
            System.out.println("Cannot initialize JVM Metrics with processName=JobTracker, sessionId= - already initialized");
        }
        String[] id = ids.trim().split(",");
        if(id.length==1){
            String path = new File("").getAbsolutePath()+"/src/main/resources/static/txt/data/part-r-00000";
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(path));
                while ((str = in.readLine()) != null) {
                    ids=ids+","+str.trim();
                }
                id = ids.trim().split(",");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i=id.length-1;i>id.length-100;i--){
            shopattributes shopattributes = shopservice.getone(id[i]);
            insertlist.add(shopattributes);
        }
        modelMap.addAttribute("insertlist",insertlist);
        return "hellojava";
    }

    //更新操作
    @RequestMapping(value = "update")
    public String update(ModelMap modelMap){
        List<shopattributes> updatelist = new ArrayList<>();
        shopservice.deleteall();
        try {
            shopservice.insert();
        }catch (Exception e){
            System.out.println("爬虫出错,代码正在维护");
        }
        updatelist=shopservice.getall();
        File file = new File("");
        String path = file.getAbsolutePath()+"/src/main/resources/static/txt/data.txt";
        writeFileContext(updatelist,path);
        modelMap.addAttribute("insertlist",updatelist);
        return "hellojava";
    }

    //获取信息
    @RequestMapping(value = "getall")
    public String getall(ModelMap modelMap){
        List<shopattributes> insertlist = shopservice.getall();
//        for (shopattributes shopattributes : insertlist) {
//            System.out.println("城市"+shopattributes.getCity()+"人均:" + shopattributes.getAvgPrice() + " 店铺名称:" + shopattributes.getShopName() + "网址" + shopattributes.getShopUrl() + "星级" + shopattributes.getShopPower());
//        }
        modelMap.addAttribute("insertlist",insertlist);
        return "hellojava";
    }


    //城市数据
    @RequestMapping(value = "getcity")
    public String getcity(@RequestParam(value = "city", required = false)String city,ModelMap modelMap){
        List<shopattributes> insertlist = shopservice.getcity(city);
        modelMap.addAttribute("insertlist",insertlist);
        return "hellojava";
    }

    //删除所有信息
    @RequestMapping(value = "deleteall")
    public String deleteall(){
        shopservice.deleteall();
        return "hellojava";
    }

    //加载人均
    @RequestMapping(value = "avg")
    public String avg(ModelMap modelMap){


        //下面tyrcatch中代码是取得json数据的  暂时没用
//        String aver = "[";
//        try {
//            File file = new File("");
//            String path = file.getAbsolutePath()+"/src/main/resources/static/txt/data/part-r-00000";
//            BufferedReader in = new BufferedReader(new FileReader(path));
//            String str;
//            while ((str = in.readLine()) != null) {
//                aver=aver+str.trim()+",";
//            }
//            //aver中存的是需要展示的所有json数据,但是前段有&报错,无法拿到,暂放.
//            aver=aver.substring(0,aver.length()-1)+"]";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //上面tyrcatch中代码是取得json数据的  暂时没用


        return "brokenline";
    }

    //初始化页面
    @RequestMapping(value = "/")
    public String allpage(ModelMap modelMap){
        List<shopattributes> insertlist = shopservice.getthree();
        modelMap.addAttribute("insertlist",insertlist);
        return "hellojava";
    }

    //写入文件
    public void writeFileContext(List<shopattributes>  strings, String path) {
        File file = new File(path);
        //如果没有文件就创建
        try {
            if (!file.isFile()) {
                file.createNewFile();
            }else if(file.isFile()){
                file.delete();
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (shopattributes shopattributes:strings){
                writer.write(shopattributes.getAvgPrice()+",");
                writer.write(shopattributes.getCity()+",");
                writer.write(shopattributes.getShopName()+",");
                writer.write(shopattributes.getShopPower()+",");
                writer.write(shopattributes.getShopUrl()+",");
                writer.write(shopattributes.getMainCategoryName()+",");
                writer.write(shopattributes.getMainRegionName()+",");
                writer.write(shopattributes.getRefinedScore1()+",");   //口味
                writer.write(shopattributes.getRefinedScore2()+",");   //环境
                writer.write(shopattributes.getRefinedScore3()+",");    //服务
                writer.write(shopattributes.getShopId());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
