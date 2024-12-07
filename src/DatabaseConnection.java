import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	// URL de conexión
	private static final String URL = "jdbc:mysql://localhost:3306/apppapa";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			// Cargar el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexion
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver no encontrado: " + e.getMessage());
		} catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
		
		return connection;
		
	}

}
