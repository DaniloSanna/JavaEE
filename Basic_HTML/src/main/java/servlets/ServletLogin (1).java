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
		
		System.out.println("Login Dif Null: " + login != null);
		System.out.println("Senha Dif Null: " + pass != null);
		System.out.println("------------------------------------");
		System.out.println("Login IsNotEmpty: " + !login.isEmpty());
		System.out.println("Senha IsNotEmpty: " + !pass.isEmpty());
		
		if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
			ModelLogin modellogin = new ModelLogin();
			modellogin.setLogin(login);
			modellogin.setPass(pass);
		
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Sucesso!");
			redirecionar.forward(request, response);
		
		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}

	}

}
