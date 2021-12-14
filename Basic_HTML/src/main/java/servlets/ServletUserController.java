package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

public class ServletUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = (  request.getParameter("id") != null && !request.getParameter("id").isEmpty() ? Long.getLong(request.getParameter("id")) : null);
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		ModelLogin modelLogin = new ModelLogin(id, login, pass, email, name);
		
		System.out.println(modelLogin);
		
		request.setAttribute("information", modelLogin);
		request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
		
	}

}
