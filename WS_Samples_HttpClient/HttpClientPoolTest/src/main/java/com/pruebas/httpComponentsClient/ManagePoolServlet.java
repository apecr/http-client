package com.pruebas.httpComponentsClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MonitorServlet
 */
public class ManagePoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagePoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
//http://localhost:8080/HttpClientPoolTest/ManagePoolServlet?operation=close
//http://localhost:8080/HttpClientPoolTest/ManagePoolServlet?operation=closeExpired
//http://localhost:8080/HttpClientPoolTest/ManagePoolServlet?operation=closeIdle&param1=30
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = (String)request.getParameter("operation");
		String param1    = (String)request.getParameter("param1");
		if (operation.equals("close")){
			ConnectionManager.cm.close();
		} else 	if (operation.equals("closeExpired")){
			ConnectionManager.cm.closeExpiredConnections();;
		} else 	if (operation.equals("closeIdle")){
			long idleTimeout = new Long(param1).longValue();
			ConnectionManager.cm.closeIdleConnections(idleTimeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
