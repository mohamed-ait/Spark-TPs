package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.w3c.dom.ls.LSOutput;
import scala.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application6 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Top 5 des stations météo les plus froides.").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            return f.split(",")[2].equals("TMIN");
        });
        JavaPairRDD<Integer,String> rdd3=rdd2.mapToPair(f->new Tuple2<>(Integer.parseInt(f.split(",")[3]),f.split(",")[0]));
        JavaPairRDD<Integer,String> sortedRdd=rdd3.sortByKey(true);
        List<Tuple2<Integer,String>> els=sortedRdd.take(5);
        els.forEach(f-> System.out.println(f._2()+"-->"+f._1()));
    }



}
