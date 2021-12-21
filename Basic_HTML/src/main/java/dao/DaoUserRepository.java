package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionDataBase;
import model.ModelLogin;

public class DaoUserRepository {

	private Connection connection;

	public DaoUserRepository() {
		connection = SingleConnectionDataBase.getConnection();
	}

	public ModelLogin recordUser(ModelLogin object) throws Exception {
		String sql = "";
		PreparedStatement ps = null;
		boolean teste = object.isNew();
		boolean teste1 = !object.isNew();

		if (teste) {
			sql = "INSERT INTO modellogin (login, pass, name, email) VALUES (upper(?), ?, ?, ?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, object.getLogin());
			ps.setString(2, object.getPass());
			ps.setString(3, object.getName());
			ps.setString(4, object.getEmail());
			ps.execute();

		}else {
			sql = "UPDATE modellogin SET login=upper(?), pass=?, name=?, email=? WHERE id="+object.getId() + "";
			ps = connection.prepareStatement(sql);
			ps.setString(1, object.getLogin());
			ps.setString(2, object.getPass());
			ps.setString(3, object.getName());
			ps.setString(4, object.getEmail());
			ps.executeUpdate();
		}
		connection.commit();

		return this.searchUser(object.getLogin());

	}

	public ModelLogin searchUser(String login) throws Exception {
		ModelLogin result = null;

		String sql = "SELECT * FROM java_ee.modellogin WHERE login = upper('" + login + "')";

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			result = new ModelLogin(rs.getLong("id"), rs.getString("login"), rs.getString("pass"),
					rs.getString("email"), rs.getString("name"));
		}
		return result;
	}

	public boolean checkCreatedLogin(String login) throws Exception {
		String sql = "SELECT IF(count(1) > 0, 'true', 'false') as 'exists' FROM java_ee.modellogin WHERE login = upper('"
				+ login + "')";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		rs.next();
		return rs.getBoolean("exists");
	}

}
