package tp1StructuredStreaming;

import org.apache.spark.sql.SparkSession;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application3 {
    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.OFF);
        SparkSession ss=SparkSession.builder()
                .appName("stream processing ")
                .master("local[*]")
                .getOrCreate();
    }
}
