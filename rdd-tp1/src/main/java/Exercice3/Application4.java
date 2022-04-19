package Exercice3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Application4 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Température minimale la plus basse.").setMaster("local[*]");
        JavaSparkContext cxt=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=cxt.textFile("temperatures.csv");
        JavaRDD<String> rdd2=rdd1.filter(f->{
            String[] els=f.split(",");
            return els[2].equals("TMIN");
        });
        //construir un rdd qui regroupe les temperatures minimaux
        JavaRDD<Double> rddTmps=rdd1.map(s->{
            String[] els=s.split(",");
            return Double.valueOf(els[3]);
        });
        //déterminer la temperature la plus elevée des temperatures maximales :
        Double tmpMin=rddTmps.reduce((v1,v2)->Math.min(v1,v2));
        System.out.println("Température minimale la plus basse: \t"+tmpMin);
    }
}
