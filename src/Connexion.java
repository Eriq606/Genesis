import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    public static Connection getMysqlConnexion(String db_name, String host, String port, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db_name+"?user="+user+"&password="+password+"&useSSL=false", user, password);
        c.setAutoCommit(false);
        return c;
    }

    public static Connection getPsqlConnexion(String db_name, String host, String port, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name, user, password);
        c.setAutoCommit(false);
        return c;
    }
    
}
