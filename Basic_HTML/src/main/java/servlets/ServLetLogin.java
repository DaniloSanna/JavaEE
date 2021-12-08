package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = { "/principal/ServLetLogin", "/ServLetLogin" }) // Mapeamento de URL
public class ServLetLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String url = request.getParameter("url");

		if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
			ModelLogin modellogin = new ModelLogin();
			modellogin.setLogin(login);
			modellogin.setPass(pass);

			if (modellogin.getLogin().equalsIgnoreCase("a") && modellogin.getPass().equalsIgnoreCase("a")) {

				request.getSession().setAttribute("login", modellogin.getLogin());

				if (url == null || url.equals("null") || url.isEmpty()) {
					url = "principal/principal.jsp";
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);

			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Login or password should be wrong!");
				redirecionar.forward(request, response);
			}

		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Login or password should be wrong!");
			redirecionar.forward(request, response);
		}

	}

}
