package com.zzq.FourSubject.reptile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.util.ClassUtils;
public class hadoop2 {
    public static void hadoop1() throws Exception {
        //统计各城市人均消费
        /*
                特别注意
                城市数量已经写死
                如果有所改动
                需在下面改动city变量的值!!!!!!!!!!!
         */
        Job job = new Job();
        job.setJarByClass(hadoop2.class);
        job.setJobName("temp MapReduce");
        File file = new File("");
        String path = file.getAbsolutePath()+"/src/main/resources/static/txt/data2";
        String inputpath = file.getAbsolutePath()+"/src/main/resources/static/txt/data.txt";
        file = new File(path);
        deleteall(file);
        FileInputFormat.addInputPath(job, new Path(inputpath));
        FileOutputFormat.setOutputPath(job, new Path(path));//运行加输出路径
        job.setMapperClass(TempMapper.class);
        job.setReducerClass(TempReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        if (job.waitForCompletion(true)){
            return;
        }
    }
    public static void deleteall(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1.getAbsolutePath());
                file1.delete();
            }
        }
        file.delete();
    }
    private static class TempMapper extends Mapper<LongWritable, Text, Text, Text> {

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            super.setup(context);
        };
        @Override
        public void map(LongWritable key,
                        Text value,
                        Context context)
                throws IOException, InterruptedException {
            String[] toks = value.toString().trim().split(",");
            String city = toks[1];
            String avg = toks[0];
            context.write(new Text(city), new Text(avg));  //第一次运行时候
        }
        @Override
        protected void cleanup(Mapper<LongWritable, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }
    private static class TempReducer extends Reducer<Text, Text, Text, Text> {
        int count=0;
        int num;
        int city;
        boolean flag = true;
        @Override
        protected void setup(Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            super.setup(context);
        }
        @Override
        public void reduce(Text key,
                           Iterable<Text> values,
                           Context context)
                throws IOException, InterruptedException {
            //key.getLength();
            city++;
            for(Text text : values) {
                //data = 2018 - Integer.parseInt(key.toString().substring(0, 4));
                count++;
                num = num+Integer.parseInt(text.toString());
            }
            Double aver = (double) (num/count);
            if(flag){
                context.write(new Text("["), new Text(""));
                flag=false;
            }
            if(city<13) {
                context.write(new Text("{\"country\":\"" + key + "\",\"visits\":\"" + aver + "\"},"), new Text(""));
//            context.write(new Text(key+"人均消费为"), new Text("  "+aver+"  总消费"+num+"  总人数"+count));
            }
            else {
                context.write(new Text("{\"country\":\"" + key + "\",\"visits\":\"" + aver + "\"}"), new Text(""));
            }
            count=0;
            num=0;
        }

        @Override
        protected void cleanup(Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            super.cleanup(context);
            context.write(new Text("]"), new Text(""));
        }
    }

}

