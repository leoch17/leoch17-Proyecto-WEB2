package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import Controllers.Usuario;
/**
 * Servlet implementation class User
 */
@WebServlet("/User")
@MultipartConfig
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		String response1 = usuario.credenciales(request);
		salida.println(response1);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		String nu = request.getParameter("nu");
		String con = request.getParameter("con");
		String ce = request.getParameter("ce");
		String telef = request.getParameter("telef");
		String sex = request.getParameter("sex");
		String date = request.getParameter("date");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
        String[] obj = {nu, con, ce, telef, sex, date, country, state, city};
		String respons = usuario.actualizar(request, obj);
		salida.println(respons);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
	    String respons = usuario.eliminar(request);	
		if(respons == "{\"mensaje\": \"Se ha Eliminado satisfactoriamente\", "
   				 	 + "\"estado\": 200 }") {
			boolean r = usuario.sesionfinalizada(request, response);
			if(r == false) {
				respons = "{\"mensaje\": \"Ha ocurrido un problema con solicitud\", "
	   				 	 + "\"estado\": 503 }";
			}
		}
		salida.println(respons);
	}

}
