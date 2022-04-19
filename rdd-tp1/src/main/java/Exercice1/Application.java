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
    JavaRDD<String> rdd1=sc.textFile("/home/med/eclipse-workspace/rdd-tp1/etudiants");
    rdd1.foreach(r->System.out.println(r));
    JavaRDD<String> rdd2=rdd1.flatMap(f->Arrays.asList(f.split(" ")).iterator());
    rdd2.foreach(el->System.out.println(el));
    JavaRDD<String> rdd3=rdd2.filter(e->e.length()>5);
    rdd3.foreach(el->System.out.println(el));
    JavaRDD<String> rdd4=rdd2.filter(e->e.length()<=5);
    rdd4.foreach(el->System.out.println(el));
    JavaRDD<String> rdd5=rdd2.filter(e->e.length()>7);
    rdd5.foreach(el->System.out.println(el));
    JavaRDD<String> rdd6=rdd3.union(rdd4);
    rdd6.foreach(el->System.out.println(el));
    JavaRDD<String> rdd71=rdd5.map(f->f+"_rdd71");
    rdd71.foreach(el->System.out.println(el));
    JavaRDD<String> rdd81=rdd6.map(f->f+"_rdd81");
    rdd81.foreach(el->System.out.println(el));
    JavaPairRDD<String, Integer> rdd82=rdd81.mapToPair(f->new Tuple2(f, 8));
    JavaPairRDD<String, Integer> rdd8=rdd82.reduceByKey((v1,v2)->v1+v2);
    rdd8.foreach(el->System.out.println(el));
    JavaPairRDD<String, Integer> rdd72=rdd71.mapToPair(f->new Tuple2(f, 7));
    JavaPairRDD<String, Integer> rdd7=rdd72.reduceByKey((v1,v2)->v1+v2);
    rdd7.foreach(el->System.out.println(el));
    JavaPairRDD<String, Integer> rdd9= rdd8.union(rdd7);
    rdd9.foreach(el->System.out.println(el));
    JavaPairRDD<String, Integer> rdd10 = rdd9.sortByKey();
    rdd10.foreach(el->System.out.println(el));
    
    
}	

}
