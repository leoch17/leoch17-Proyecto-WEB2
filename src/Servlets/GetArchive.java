package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.Archivos;


@WebServlet("/GetArchive")
public class GetArchive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetArchive() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Archivos fichero = new Archivos();
		PrintWriter salida = response.getWriter();
		response.setContentType("application/json");
		String ce = request.getParameter("ce");
		if(!ce.equals("")) {
			salida.println(fichero.ObtencionDatos(ce));
		}else{
			return;
		}
	}
}
