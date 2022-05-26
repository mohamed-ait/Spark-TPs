package tp1StructuredStreaming;

import ma.enset.tpsparksql.Employe;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions.*;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.*;


public class Application1 {

    public static void main(String[] args) throws Exception{
        Logger.getLogger("org").setLevel(Level.OFF);
        SparkSession ss = SparkSession.builder()
                .appName("structured streaming ")
                .master("local[*]")
                .getOrCreate();
        //create schema :
        StructType schema = new StructType(new StructField[]{
            new StructField("id", DataTypes.LongType,false,Metadata.empty()),
                new StructField("name", DataTypes.ShortType,false,Metadata.empty()),
                new StructField("age", DataTypes.IntegerType,false,Metadata.empty()),
        });
        /*Dataset<Row> ds1=ss.read()
                .option("inferschema",true)//detection du schema de la table en basant sur les données
                .option("header",true)
                .option("delimiter",",")
                .csv("employes.csv");*/
        Dataset<Row> lines = ss.readStream()
                .option("heder",true)
                .schema(schema)
                .csv("*input");
        StreamingQuery query=lines.writeStream()
                .outputMode("append")
                .format("console")
                .start();
        query.awaitTermination();

    }
}
