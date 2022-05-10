package com.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

// 코드에 대한 조건
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private final IntWritable outV = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        outV.set(sum);

        if (sum >= 50) { // total count가 50보다 큰 경우만
            context.write(key, outV);
        }
    }
}