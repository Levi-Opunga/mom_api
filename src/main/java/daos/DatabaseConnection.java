package daos;

import org.sql2o.Sql2o;

public class DatabaseConnection {
//    static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/mum_app","postgres","MAINA");
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-44-205-41-76.compute-1.amazonaws.com:5432/d38sm6e0n3ep29", "ddwibsrgqqonrh", "2408cd632b79c5bd776faf910db5169a02c1b0e8b3a18c2fecdfc79057465ab9");


}
