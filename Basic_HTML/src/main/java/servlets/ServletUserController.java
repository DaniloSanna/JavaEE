package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import jakarta.servlet.annotation.WebServlet;

//@WebServlet("/ServletUserController")
public class ServletUserController extends ServletUserGenericUtil {
	private static final long serialVersionUID = 1L;

	private DaoUserRepository daoUserRepository = new DaoUserRepository();

	public ServletUserController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (request.getParameter("acao") != null && 
					!request.getParameter("acao").isEmpty() &&					 
					request.getParameter("acao").equalsIgnoreCase("delete")) {

				daoUserRepository.deleteUser(Long.parseLong(request.getParameter("id")));
				request.setAttribute("msg", "User sussecefully deleted!");
				
				 List<ModelLogin> modelLogins = daoUserRepository.searchForAll(super.getLoggedUser(request));
				 request.setAttribute("informationAll", modelLogins);
				
				request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
			
			}else if (request.getParameter("acao") != null && 
					!request.getParameter("acao").isEmpty() &&					 
					request.getParameter("acao").equalsIgnoreCase("deleteajax")) {
				
				daoUserRepository.deleteUser(Long.parseLong(request.getParameter("id")));
				response.getWriter().write("User sussecefully deleted!");
						 }
			else if (request.getParameter("acao") != null && 
					!request.getParameter("acao").isEmpty() &&					 
					request.getParameter("acao").equalsIgnoreCase("searchForUser")) {
				
				 List<ModelLogin>dataJsonUser = daoUserRepository.searchForName(request.getParameter("nameSearched"),super.getLoggedUser(request));
				// dataJsonUser.forEach(e->System.out.println(e));
				 
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dataJsonUser);
				//System.out.println(json);
				response.getWriter().write(json);
				 
						 }
			
			else if (request.getParameter("acao") != null && 
					!request.getParameter("acao").isEmpty() &&					 
					request.getParameter("acao").equalsIgnoreCase("searchForSelected")) {
					
				    ModelLogin modelLogin = daoUserRepository.searchForId(Long.parseLong(request.getParameter("id")), super.getLoggedUser(request) );
				 
					 List<ModelLogin> modelLogins = daoUserRepository.searchForAll(super.getLoggedUser(request));
					 request.setAttribute("informationAll", modelLogins);
					 
				    request.setAttribute("msg", "Editing user");
				    request.setAttribute("information", modelLogin);
					request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
				 
						 }
			
			else if (request.getParameter("acao") != null && 
					!request.getParameter("acao").isEmpty() &&					 
					request.getParameter("acao").equalsIgnoreCase("searchForAll")) {
					
				    List<ModelLogin> modelLogin = daoUserRepository.searchForAll(super.getLoggedUser(request));
				   // modelLogin.forEach(e->System.out.println(e));
				    
				    request.setAttribute("msg", "Loaded Users");
				    request.setAttribute("informationAll", modelLogin);
					request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
				 
						 }
			
			
			else {
				//request.setAttribute("msg", "Deletion did not work as expected, check the information inserted!");
				 List<ModelLogin> modelLogin = daoUserRepository.searchForAll(super.getLoggedUser(request));
				 request.setAttribute("informationAll", modelLogin);
				request.getRequestDispatcher("principal/user-registration.jsp").forward(request, response);
			}


		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
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
					request.getParameter("name"), request.getParameter("assignment"));

			if (daoUserRepository.checkCreatedUser(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "User alread created!";
				modelLogin = daoUserRepository.searchUser(modelLogin.getLogin().concat(msg), super.getLoggedUser(request));
			} else {
				if (!modelLogin.isNew()) {
					msg = "User sussecefully updated!";
				}
				modelLogin = daoUserRepository.recordUser(modelLogin, super.getLoggedUser(request));
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