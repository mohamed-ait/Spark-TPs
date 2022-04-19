package Exercice2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDDLike;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Application1 {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("total des ventes par ville").setMaster("local[*]");
		JavaSparkContext cxt=new JavaSparkContext(conf);
		JavaRDD<String> rdd1=cxt.textFile("ventes.txt");
		JavaRDD<String> rdd2=rdd1.filter(f->f.isEmpty()==false);
		JavaPairRDD<String, Double> rddVente=rdd2.mapToPair(r->{
			String els[]=r.split(" ");
			return new Tuple2<String, Double>(els[1],Double.valueOf(els[3]));
		});
		rdd1.foreach(f->System.out.println(f));
		JavaPairRDD<String, Double> rddTotalVille=rddVente.reduceByKey((v1,v2)->v1+v2);
        rddTotalVille.foreach(nameTuple-> System.out.println(nameTuple._1()+" "+nameTuple._2()));
	}
	
	

}
