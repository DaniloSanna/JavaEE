package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

		if (object.isNew()) {
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

public List<ModelLogin> searchForAll() throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM java_ee.modellogin";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			retorno.add( new ModelLogin(rs.getLong("id"), rs.getString("login"), rs.getString("pass"),
					rs.getString("email"), rs.getString("name")));
		}
		
		return retorno;
	}

	public List<ModelLogin> searchForName(String name) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM java_ee.modellogin WHERE name LIKE UPPER(?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, "%" + name + "%");
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			retorno.add( new ModelLogin(rs.getLong("id"), rs.getString("login"), rs.getString("pass"),
					rs.getString("email"), rs.getString("name")));
		}
		
		return retorno;
	}

	public ModelLogin searchForId(Long id) throws Exception {
		ModelLogin result = null;

		String sql = "SELECT * FROM java_ee.modellogin WHERE id ='" + id + "'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			result = new ModelLogin(rs.getLong("id"), rs.getString("login"), rs.getString("pass"),
					rs.getString("email"), rs.getString("name"));
		}
		return result;
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
	
	public boolean checkCreatedUser(String login) throws Exception {
		String sql = "SELECT IF(count(1) > 0, 'true', 'false') as 'exists' FROM java_ee.modellogin WHERE login = upper('" + login + "')";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		rs.next();
		return rs.getBoolean("exists");
	}

	public void deleteUser(Long id) throws Exception {
		String sql = "DELETE FROM java_ee.modellogin WHERE id = '" + String.valueOf(id) + "'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
		connection.commit();
	}
}
