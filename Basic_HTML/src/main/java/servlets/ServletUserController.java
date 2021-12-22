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

		try {
			if (request.getParameter("action") != null && 
					!request.getParameter("action").isEmpty() &&					 
					request.getParameter("action").equalsIgnoreCase("delete")) {

				daoUserRepository.deleteUser(Long.parseLong(request.getParameter("id")));
				request.setAttribute("msg", "User sussecefully deleted!");
				request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Deletion did not work as expected, check the information inserted!");
				request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String msg = "User sussecefully created!";
			ModelLogin modelLogin = new ModelLogin(
					(request.getParameter("id") != null && !request.getParameter("id").isEmpty()
							? Long.parseLong(request.getParameter("id"))
							: null),
					request.getParameter("login"), request.getParameter("pass"), request.getParameter("email"),
					request.getParameter("name"));

			if (daoUserRepository.checkCreatedUser(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "User alread created!";
				modelLogin = daoUserRepository.searchUser(modelLogin.getLogin());
			} else {
				if (!modelLogin.isNew()) {
					msg = "User sussecefully updated!";
				}
				modelLogin = daoUserRepository.recordUser(modelLogin);
			}

			request.setAttribute("information", modelLogin);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
