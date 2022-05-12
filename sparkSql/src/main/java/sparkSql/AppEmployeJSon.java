package sparkSql;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import static org.apache.spark.sql.functions.*;
import org.apache.spark.sql.*;
import scala.Function1;

public class AppEmployeJSon {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("saprk sql").setMaster("local[*]");
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Encoder<Employe> employeEncoder= Encoders.bean(Employe.class);
        Dataset<Employe> ds = ss.read().option("multiline", true).json("employes.json").as(employeEncoder);        //ds.printSchema();
        //ds.show();
        //les employés qui ont un age entre 30 et 35
        ds.filter((FilterFunction<Employe>) employeeBean -> employeeBean.getAge()>=30 && employeeBean.getAge()<=35).show();        // la moyenne des salaires dans chaque departement :
        //la moyenne des salaires par departement :
        ds.groupBy(col("departement")).avg("salary").show();
         //le nombre des employés par département :
        ds.groupBy(col("departement")).count().show();
         // le salaire maximum de tous les départements :
        ds.select(max("salary")).show();
    }
}
