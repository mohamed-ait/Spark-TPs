package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.w3c.dom.ls.LSOutput;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

public class Application5 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Top 5 des stations météo les plus chaudes.").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            return f.split(",")[2].equals("TMIN");
        });
        JavaPairRDD<Double,String> rdd3=rdd2.mapToPair(f->new Tuple2<>(Double.valueOf(f.split(",")[3]),f.split(",")[0]));
        JavaPairRDD<Double,String> sortedRdd=rdd3.sortByKey();
        System.out.println("affichage----------------------->"+sortedRdd.count());
        sortedRdd.foreach(f-> System.out.println(f));
    }



}
