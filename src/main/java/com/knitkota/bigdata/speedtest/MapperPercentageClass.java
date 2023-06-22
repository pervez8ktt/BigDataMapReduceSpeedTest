package com.knitkota.bigdata.speedtest;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperPercentageClass extends Mapper<LongWritable, Text, Text, DoubleWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {

		String str = value.toString();

		if (!str.isEmpty()) {

			String[] values = str.split(",");

			String vehicleRegistrationNumber = values[0];
			double speedRecored = Double.parseDouble(values[1]);
			String speedRecordTime = values[2];

			if (speedRecored > 65) {
				context.write(new Text("Above65"), new DoubleWritable(1.0));
			}

			context.write(new Text("Total"), new DoubleWritable(1.0));
		}

	}

}
