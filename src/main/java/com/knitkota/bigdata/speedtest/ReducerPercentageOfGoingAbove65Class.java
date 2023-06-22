package com.knitkota.bigdata.speedtest;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerPercentageOfGoingAbove65Class extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> value,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {

		try {

			double totalCounts = 0;

			double totalAbove65 = 0;

			for (DoubleWritable v : value) {

				totalCounts++;

				if (key.toString().equals("Above65")) {

					totalAbove65++;

				}

			}

			double percent = totalAbove65 / totalCounts * 100.0;

			context.write(new Text("above65Percent"), new DoubleWritable(percent));

//			context.write(new Text(key), new DoubleWritable(totalCounts));

		} catch (Exception e) {

		}

	}

}
