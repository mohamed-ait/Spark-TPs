package sparkSql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.max;

public class AppEmployeCsv {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("saprk sql").setMaster("local[*]");
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Encoder<Employe> employeEncoder= Encoders.bean(Employe.class);
        Dataset<Employe> ds = ss.read().option("header", true).option("inferSchema",true)
                .option("delimiter",",")
                .csv("employes.csv").as(employeEncoder);        //ds.printSchema();
        //ds.show();
        //les employés qui ont un age entre 30 et 35
        System.out.println("les employés qui ont un age entre 30 et 35");
        ds.filter((FilterFunction<Employe>) em -> em.getAge()>=30 && em.getAge()<=35).show();
        //la moyenne des salaires par departement :
        System.out.println("la moyenne des salaires par departement ");
        ds.groupBy(col("departement")).avg("salary").show();
        //le nombre des employés par département :
       ds.groupBy(col("departement")).count().show();
         //le salaire maximum de tous les départements :
        System.out.println("le salaire maximum de tous les départements");
        ds.select(max("salary")).show();
    }
}
