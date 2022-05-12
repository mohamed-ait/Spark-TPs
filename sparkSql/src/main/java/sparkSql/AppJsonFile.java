package sparkSql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.max;

public class AppJsonFile {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("la temperature minimale moyenne").setMaster("local[*]");
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();

        Dataset<Row> df=ss.read().option("multiLine",true).json("employes.json");
       // df.createOrReplaceTempView("employes");
        // les employes qui ont un salaire entre 30 et 35
        //df.filter(col("age").between(30,35)).show();
        //la moyenne des salaires par departement :
       // df.groupBy(col("departement")).avg("salary").show();
        //le nombre des employes par departement :
        //df.groupBy(col("departement")).count().show();
        //le salaire maximum de tous les departements :
        df.select(max("salary")).show();
    }
}
