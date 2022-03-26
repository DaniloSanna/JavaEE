package servlets;

import java.io.Serializable;

import dao.DaoUserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletUserGenericUtil extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DaoUserRepository dUR = new DaoUserRepository();
	
	public Long getLoggedUser(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String loggedUser = (String) session.getAttribute("login");
		
		return dUR.searchLoogedUser(loggedUser).getId();
	}

}
