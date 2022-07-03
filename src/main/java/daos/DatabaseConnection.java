package daos;

import org.sql2o.Sql2o;

public class DatabaseConnection {
    static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/mum_app","postgres","MAINA");

}
