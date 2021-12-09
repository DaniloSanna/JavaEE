package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionDataBase;
import model.ModelLogin;

public class DAOLoginRepository {
	private Connection connection;
	
	
	public DAOLoginRepository() {
		connection = SingleConnectionDataBase.getConnection();
	}
	
	public boolean checkLoginAuthentication(ModelLogin modelLogin) throws Exception{
		
		String sqlquery = "SELECT * FROM modellogin WHERE login = ? and pass = ?";
		PreparedStatement statement = connection.prepareStatement(sqlquery);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getPass());
		
		System.out.println("-------------------------------------------");
		System.out.println("DENTRO DO DAO");
		System.out.println(modelLogin.getLogin() + " - " + modelLogin.getPass());
		
		ResultSet resultset = statement.executeQuery();

		if(resultset.next()) return true;
		return false;
	}
}
