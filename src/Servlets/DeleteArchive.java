package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.Archivos;

/**
 * Servlet implementation class DeleteArchive
 */
@WebServlet("/DeleteArchive")
public class DeleteArchive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArchive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Archivos documento = new Archivos();
		PrintWriter salida = response.getWriter();
		response.setContentType("aplication/json");
		String ce = request.getParameter("ce");
		String archivo = request.getParameter("archivo");
		if(!archivo.equals("") && !ce.equals("")) {
			salida.println(documento.EliminacionArchivos(ce, archivo));
		}else{
			return;
		}
	}

}
