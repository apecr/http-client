package com.pruebas.httpComponentsClient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MonitorServlet
 */
public class MonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonitorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
//http://localhost:8080/HttpClientPoolTest/MonitorServlet
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String monitor = ConnectionManager.printTotalStats();
		String netstat = ConnectionManager.printNetstat();
		//String connectionConfig = ConnectionManager.printConnectionConfig();
		
		


				
				
				
	    ServletOutputStream sos = response.getOutputStream();
	    sos.print(monitor+"\n"+netstat+"\n");
	    //sos.print(monitor+"\n"+netstat+"\n"+connectionConfig);
	    sos.flush();
	    sos.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
