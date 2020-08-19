package com.test.challenge;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.*;

import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.concat;
import static org.apache.spark.sql.functions.explode;


/**
 * Spark Kafka Streaming Challenge
 *
 */
public class App 
{	

	static StructType customerFields = new StructType()
			.add("root", new StructType().add("cust_info",DataTypes.createArrayType(new StructType().add("customer_id",DataTypes.StringType, true).add("Location",DataTypes.StringType, true), true),true ),true);

	@SuppressWarnings("deprecation")
	public static void main( String[] args )
	{
		if (args.length < 3) {
			System.err.println("Usage: JavaStructuredKafkaWordCount <bootstrap-servers> " +
					"<subscribe-type> <topics>");
			System.exit(1);
		}

		String bootstrapServers = args[0];
		String subscribeType = args[1];
		String topics = args[2];

		SparkSession spark = SparkSession
				.builder()
				.appName("JavaSparkKafkaStreaming")
				.master("local[4]")
				.config("spark.sql.warehouse.dir", "file:///C:/temp")
				.config("spark.testing.memory", "2147480000")
				.getOrCreate();

		// Create DataSet representing the stream of input lines from kafka
		Dataset<String> lines = spark
				.readStream()
				.format("kafka")
				.option("kafka.bootstrap.servers", bootstrapServers)
				.option(subscribeType, topics)
				.load()
				.selectExpr("CAST(value AS STRING)")
				.as(Encoders.STRING());
		
		Dataset<Row> customerInfo = spark.read().csv("C:\\Users\\at186045\\eclipse-workspace\\challenge\\src\\main\\resources\\custfile.csv")
				.toDF("customer_id","email").cache();

		Dataset<Row> ds1 = lines.select(from_json(lines.col("value"), customerFields).as("data")).select("data.*");
		Dataset<Row> ds2 = ds1.select(explode(ds1.col("root.cust_info")).as("cust")).select("cust.Location", "cust.customer_id");

		Dataset<Row> result = customerInfo.join(ds2, ds2.col("customer_id").equalTo(customerInfo.col("customer_id")));
		Dataset<Row> finalResult = result.withColumn("location", concat(lit("location="), result.col("Location"))).select("email","location");
		
		StreamingQuery query = finalResult.writeStream()
				.format("csv")
				.trigger(Trigger.ProcessingTime("5 seconds"))
				.option("path", "C:\\Users\\at186045\\Documents\\Project\\SparkStreaming\\Output1")
				.option("checkpointLocation", "C:\\Users\\at186045\\Documents\\Project\\SparkStreaming\\CheckPoint")
				.start();

		try {
			query.awaitTermination();
		} catch (StreamingQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
