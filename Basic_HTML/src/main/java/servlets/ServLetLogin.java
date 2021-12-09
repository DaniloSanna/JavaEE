package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
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
	private DAOLoginRepository dAOLoginRepository = new DAOLoginRepository();

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

		try {
			if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setPass(pass);
				
				System.out.println("-------------------------------------------");
				System.out.println("DENTRO DO SERVLETLOGIN");
				System.out.println(modelLogin.getLogin() + " - " + modelLogin.getPass());

				
				if (dAOLoginRepository.checkLoginAuthentication(modelLogin)) {

					request.getSession().setAttribute("login", modelLogin.getLogin());

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

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}

	}
}
