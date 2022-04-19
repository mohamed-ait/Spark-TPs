package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature minimale moyenne").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            String[] els=f.split(",");
            return els[2].equals("TMAX");
        });
        //construire un rdd des temperatures maximaux
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });
        //calculer la somme des temperatures maximaux:
        Double tmpSum=rddTmps.reduce((v1, v2)->v1+v2);
        //claculer la moyenne des temperatures minimiaux :
        double moyTmp=tmpSum/rddTmps.count();
        System.out.println("la temperature maximale moyenne est \t"+moyTmp);
    }
}
