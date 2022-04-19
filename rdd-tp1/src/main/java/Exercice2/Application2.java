package Exercice2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Application2 {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("total des ventes par ville pour une année donnée").setMaster("local[*]");
		JavaSparkContext cxt=new JavaSparkContext(conf);
		JavaRDD<String> rdd1= cxt.textFile("ventes.txt");
		JavaRDD<String> rdd2=rdd1.filter(f->f.isEmpty()==false);
		JavaPairRDD<String, Double> rddVentes=rdd2.mapToPair(r->{
			String els[]=r.split(" ");
			return new Tuple2<String,Double>(els[0]+els[1],Double.valueOf(els[3]));
		});
		JavaPairRDD<String, Double> rddTotalVilleAnne=rddVentes.reduceByKey((v1,v2)->v1+v2);
		rddTotalVilleAnne.foreach(f->System.out.println(f._1()+"----->"+f._2()));
	}

}
