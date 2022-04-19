package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application3 {
    static double maxTmp=Double.MIN_VALUE;
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature maximale la plus elevée").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            String[] els=f.split(",");
            return els[2].equals("TMAX");
        });
        //construir un rdd qui regroupe les temperatures minimaux
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });
        //déterminer la temperature la plus elevée des temperatures maximales :
        Double tmpMax=rddTmps.reduce((v1,v2)->Math.max(v1,v2));
        System.out.println("la temperature la plus elevée des temperatures maximaux est \t"+tmpMax);
    }
}
