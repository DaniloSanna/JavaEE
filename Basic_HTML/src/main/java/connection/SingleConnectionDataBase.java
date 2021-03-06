package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionDataBase {
	private static String url = "jdbc:mysql://localhost:3306/java_ee?autoReconnect=true";
	private static String user = "root";
	private static String pass = "root";
	private static Connection connection = null;

	static {
		toConcect();
	}

	public SingleConnectionDataBase() {
		toConcect();
	}

	private static void toConcect() {
		try {
			if (connection == null) {
				// Carrega driver de conex?o do BD
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, pass);

				// N?o efetuar altera??es sem aprova??o direta.
				connection.setAutoCommit(false);
				//System.out.println("Conectou no banco, sucesso!");
			}
		} catch (Exception e) {
			// Mostrar qualquer erro.
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}