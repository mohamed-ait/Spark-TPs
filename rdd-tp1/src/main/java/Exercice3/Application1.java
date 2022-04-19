package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application1 {
    static double minValue=Double.MAX_VALUE;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature minimale moyenne").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            String[] els=f.split(",");
            return els[2].equals("TMIN");
        });
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });
      Double tmpSum=rddTmps.reduce((v1, v2)->v1+v2);
        System.out.println("la temperature minimale moyenne est \t"+tmpSum/rddTmps.count() );
    }
}
