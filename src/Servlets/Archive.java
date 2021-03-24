package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.Archivos;


@WebServlet("/Archive")
public class Archive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Archive() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Archivos formato = new Archivos();
		PrintWriter salida = response.getWriter();
		response.setContentType("application/json");
		try {
			String ce = request.getParameter("ce");
			if(!ce.equals("")) {
				salida.println(formato.SubidaArchivos(ce, request.getPart("filenombre")));
			}else{
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
