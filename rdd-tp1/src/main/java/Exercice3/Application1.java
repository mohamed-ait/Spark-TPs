package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application1 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature maximale moyenne").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            String[] els=f.split(",");
            return els[2].equals("TMIN");
        });
        //construire un rdd des temperatures minimaux
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });
        //calculer la somme des temperatures minimaux:
      Double tmpSum=rddTmps.reduce((v1, v2)->v1+v2);
      //claculer la moyenne des temperatures minimiaux :
        double moyTmp=tmpSum/rddTmps.count();
        System.out.println("la temperature minimale moyenne est \t"+moyTmp);
    }
}
