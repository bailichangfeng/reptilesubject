package com.zzq.FourSubject.reptile;

        import org.springframework.util.ClassUtils;
        import org.springframework.util.ResourceUtils;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileReader;

public class test {
    public static void main(String[] args) throws Exception{
        String key = "郑州";
        String aver = "100";

                hadoop hadoop = new hadoop();
        hadoop.hadoop1();

//        hadoop2 hadoop2 = new hadoop2();
//        hadoop2.hadoop1();

        File file = new File("");
        String pathh = file.getAbsolutePath()+"/src/main/resources/static/txt/test.txt";
        pathh = pathh+"/src/main/resources/static/txt/test.txt";
        System.out.println(pathh);


        String path2 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        path2 = java.net.URLDecoder.decode(path2,"utf-8");
        System.out.println(path2);
        try {
            String string = path2.substring(1)+"static/txt/test.txt";

            System.out.println(string);
            BufferedReader in = new BufferedReader(new FileReader(pathh));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("[{\n"+"\"country\": \"" + key + "\",\n"+"\"visits\": " + aver + "\n" + "}, {\n" + "\"country\": \"" + key + "\",\n" + "\"visits\": " + aver + "\n" + "}]");
        System.out.println("ssssssss");
        String string ="[{\"country\":\""+ key + "\",\"visits\":" + key + "],[\"country\":\"郑州\",\"visits\":100}]";

        System.out.println(string);
        System.out.println("[{\"country\":\""+ key + "\",\"visits\":" + key + "],[\"country\":\"郑州\",\"visits\":100}]");
    }

}


