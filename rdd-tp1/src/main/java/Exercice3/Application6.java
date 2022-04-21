package Exercice3;

import org.apache.commons.collections.map.HashedMap;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

public class Application6 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Top 5 des stations météo les plus froides.").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            return f.split(",")[2].equals("TMIN");
        });
        JavaPairRDD<String,Integer> rdd3=rdd2.mapToPair(f->new Tuple2<>(f.split(",")[0],Integer.parseInt(f.split(",")[3])));
        JavaPairRDD<String, Iterable<Integer>> rdd4=rdd3.groupByKey();
        JavaPairRDD<Integer,String> rdd5=rdd4.mapToPair(f->{
            Iterator<Integer> it=f._2.iterator();
            Integer min=Integer.MAX_VALUE;
            Integer i=0;
            while(it.hasNext()){
                if(min>=(i=it.next())){
                    min=i;
                }
            }
            return new Tuple2<Integer,String>(min,f._1());
        });
        JavaPairRDD<Integer,String> sortedRdd=rdd5.sortByKey(true);
        List<Tuple2<Integer,String>> els=sortedRdd.take(5);
        els.forEach(f-> System.out.println(f._2()+"-->"+f._1()));
    }



}
