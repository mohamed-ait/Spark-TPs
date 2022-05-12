package sparkSql;
import org.apache.spark.sql.*;
import static org.apache.spark.sql.functions.*;
public class AppCsvFile {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Dataset<Row> df=ss.read().option("header",true).csv("employes.csv");
     // les employes qui ont un age entre 30 et 35
        //df.filter(col("age").between(30,35)).show();
        // la moyenne des salaires dans chaque departement :
       // df.select(col("departement"),col("salary").cast("int")).groupBy("departement").avg("salary").show();
        //le nombre des salariés par département :
        //df.groupBy(col("departement")).count().show();
        //le salaire maximale de tous les départements :
        df.select(max(col("salary").cast("long"))).show();

    }
}
