package tp1StructuredStreaming;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.functions.*;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class Application2 {
    public static void main(String[] args) throws Exception {
        Logger.getLogger("org").setLevel(Level.OFF);
        SparkSession ss=SparkSession.builder()
                .appName("stream processing ")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> inputTable=ss.readStream()
                .format("socket")
                .option("host","localhost")
                .option("port","9090")
                .load();
        Dataset<String> dsWords=inputTable.as(Encoders.STRING())
                .flatMap((FlatMapFunction<String,String>) line-> Arrays.asList(line.split(" ")).iterator(),Encoders.STRING());
    Dataset<Row> worsCout=dsWords.groupBy("value").count();
        StreamingQuery query=worsCout.writeStream()
                .outputMode("complete")
                .format("console")
                .start();
        query.awaitTermination();

    }
}
