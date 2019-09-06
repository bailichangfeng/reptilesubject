package com.zzq.FourSubject.reptile;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(path = "/hadoop")
public class hadoop {
    public static void hadoop1() throws Exception {
        //计算三项评分最高的餐厅
        Job job = new Job();
        job.setJarByClass(hadoop.class);
        job.setJobName("temp MapReduce");
        File file = new File("");
        String path = file.getAbsolutePath()+"/src/main/resources/static/txt/data";
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
//                System.out.println(file1.getAbsolutePath());
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
            String shop_id = toks[10];//商品ID
            String taste_score = toks[7];//三个评分
            String environment_score = toks[8];
            String service_score = toks[9];
            //评分相加
            String sum = Double.parseDouble(taste_score) + Double.parseDouble(environment_score)+ Double.parseDouble(service_score)+"";
            context.write(new Text(sum), new Text(shop_id));  //第一次运行时候
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
                for (Text sum : values) {
                    count++;
                        context.write(new Text(sum.toString().trim()), new Text("".trim()));
                }
        }

        @Override
        protected void cleanup(Reducer<Text, Text, Text, Text>.Context context)
                throws IOException, InterruptedException {
            System.out.println("hadoop结束");
            super.cleanup(context);

        }
    }

}

