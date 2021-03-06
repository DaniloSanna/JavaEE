package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionDataBase;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//Intercepta todas as requisi??es que vierem do projeto ou mapeamento
@WebFilter(urlPatterns = { "/principal/*" })

public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
	}

	// Encerra os processos quando o servidor for interrompida.
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("DataBase -> Close Error:");
			e.printStackTrace();
		}
	}

	// Intercepta todas as requisi??es do projeto.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("login");

			// URL que esta sendo acessada.
			String urlParaAtutenticar = req.getServletPath();

			// Validar se esta logado, caso n?o, direcione para tela de login.
			if (usuarioLogado == null && !urlParaAtutenticar.contains("/principal/ServletLogin")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAtutenticar);
				request.setAttribute("msg", "Please, login first");
				redireciona.forward(request, response);

				// Para a execu??o e redireciona para login.
				return;
			} else {
				chain.doFilter(request, response);
			}

		}catch (Exception e) {
			System.out.println("Unexpected Error:");
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("RollBack Error:");
				e1.printStackTrace();
			}
		}
		
		//Se tudo funcionar.
		try {
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Commit Error:");
			e.printStackTrace();
		}
	}

	// Inicia os processos e recursos quando o servidor carrega o projeto
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionDataBase.getConnection();

	}

}
