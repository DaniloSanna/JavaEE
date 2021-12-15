package servlets;

import java.io.IOException;

import dao.DaoUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

public class ServletUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUserRepository daoUserRepository = new DaoUserRepository();

	public ServletUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ModelLogin modelLogin = new ModelLogin(
					(request.getParameter("id") != null && !request.getParameter("id").isEmpty()
							? Long.getLong(request.getParameter("id"))
							: null),
					request.getParameter("login"), request.getParameter("pass"), request.getParameter("email"),
					request.getParameter("name"));

			daoUserRepository.recordUser(modelLogin);

			request.setAttribute("information", modelLogin);
			request.setAttribute("msg", "User sussecefully created!");
			request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
