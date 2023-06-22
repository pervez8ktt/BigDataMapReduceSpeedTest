package com.knitkota.bigdata.speedtest;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerGoingAbove65Class extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> value,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {

		double totalValues = 0;

		for (DoubleWritable v : value) {

			if (!(key.toString().equals("Above65") || key.toString().equals("Total"))) {

				if (v.get() > 65) {
					totalValues++;
				}

			}

		}

		context.write(key, new DoubleWritable(totalValues));

	}

}
