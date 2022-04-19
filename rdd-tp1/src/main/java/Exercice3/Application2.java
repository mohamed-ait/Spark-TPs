package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application2 {
    static double maxValue=Double.MIN_VALUE;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature maximale ").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });

        rddTmps.foreach(a -> {
            if(a>=maxValue){
                maxValue=a;
            }
        });
        System.out.println("la temperature maximale est \t"+maxValue);
    }
}
