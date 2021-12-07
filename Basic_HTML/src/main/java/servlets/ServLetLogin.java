package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin
 */

@WebServlet("/ServLetLogin") // Mapeamento de URL
public class ServLetLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServLetLogin() {
		// TODO Auto-generated constructor stub
	}

	// Recebe os dados da URL em Parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// Recebe os dados por um form.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		
		
		if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
			ModelLogin modellogin = new ModelLogin();
			modellogin.setLogin(login);
			modellogin.setPass(pass);
		
			if(modellogin.getLogin().equalsIgnoreCase("a") & modellogin.getPass().equalsIgnoreCase("a")) {
				
				request.getSession().setAttribute("usu�rio", modellogin.getLogin());
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
				redirecionar.forward(request, response);
				
				
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		
		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}

	}

}
