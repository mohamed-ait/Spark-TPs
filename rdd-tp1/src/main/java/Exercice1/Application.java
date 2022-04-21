package Exercice1;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Application {
public static void main(String[] args) {
	SparkConf conf= new SparkConf().setAppName("Word count").setMaster("local[*]");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> rdd1=sc.textFile("etudiants.txt");
    rdd1.foreach(r->System.out.println(r));
    JavaRDD<String> rdd2=rdd1.flatMap(f->Arrays.asList(f.split(" ")).iterator());
    JavaRDD<String> rdd3=rdd2.filter(e->e.length()>5);
    JavaRDD<String> rdd4=rdd2.filter(e->e.length()<=5);
    JavaRDD<String> rdd5=rdd2.filter(e->e.length()>7);
    JavaRDD<String> rdd6=rdd3.union(rdd4);
    JavaRDD<String> rdd71=rdd5.map(f->f+"_rdd71");
    JavaRDD<String> rdd81=rdd6.map(f->f+"_rdd81");
    JavaPairRDD<String, Integer> rdd82=rdd81.mapToPair(f->new Tuple2(f, 8));
    JavaPairRDD<String, Integer> rdd8=rdd82.reduceByKey((v1,v2)->v1+v2);
    JavaPairRDD<String, Integer> rdd72=rdd71.mapToPair(f->new Tuple2(f, 7));
    JavaPairRDD<String, Integer> rdd7=rdd72.reduceByKey((v1,v2)->v1+v2);
    JavaPairRDD<String, Integer> rdd9= rdd8.union(rdd7);
    JavaPairRDD<String, Integer> rdd10 = rdd9.sortByKey();
    rdd10.foreach(el->System.out.println(el));

    
}	

}
