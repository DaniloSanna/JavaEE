package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnectionDataBase;
import model.ModelLogin;

public class DaoUserRepository {

	private Connection connection;

	public DaoUserRepository() {
		connection = SingleConnectionDataBase.getConnection();
	}
	public void recordUser(ModelLogin object) throws Exception {

		String sql = "INSERT INTO modellogin (login, pass, name, email) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, object.getLogin());
		ps.setString(2, object.getPass());
		ps.setString(3, object.getName());
		ps.setString(4, object.getEmail());
		
		ps.execute();
		connection.commit();
		
		
	}
}
