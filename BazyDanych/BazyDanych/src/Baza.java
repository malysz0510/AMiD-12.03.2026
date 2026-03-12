import java.sql.Connection;
import java.sql.DriverManager;

public class Baza {

    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ksiegarnia",
                    "root",
                    ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}