package com.pruebas.httpComponentsClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpHost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.log4j.Logger;

public class ConnectionManager {
	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	//http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/impl/conn/PoolingHttpClientConnectionManager.html
	public static PoolingHttpClientConnectionManager cm = null;
	
    public static String printTotalStats(){
     	 logger.debug("////////////////printTotalStats/////INICIO///////////////////////////////////////");
       PoolStats poolStats = cm.getTotalStats();
       StringBuffer sf = new StringBuffer("");
       sf.append(" - poolStats.toString() = "+poolStats.toString());
       sf.append(" - poolStats.getAvailable() = "+poolStats.getAvailable());
       sf.append(" - poolStats.getLeased() = "+poolStats.getLeased());
       sf.append(" - poolStats.getMax() = "+poolStats.getMax());
       sf.append(" - poolStats.getPending() = "+poolStats.getPending());
       logger.debug("-->stats = "+sf.toString());
    	 logger.debug("////////////////printTotalStats/////FIN///////////////////////////////////////");
    	 return poolStats.toString();
   }
    
    
    public static String printConnectionConfig(){
    	 logger.debug("////////////////printConnectionConfig/////INICIO///////////////////////////////////////");
    	 HttpHost httpHost = new HttpHost("127.0.0.1");
      ConnectionConfig connectionConfig = cm.getDefaultConnectionConfig();
      StringBuffer sf = new StringBuffer("");
      sf.append(" - connectionConfig.toString() = "+connectionConfig.toString());
      /*
      sf.append(" - poolStats.getAvailable() = "+poolStats.getAvailable());
      sf.append(" - poolStats.getLeased() = "+poolStats.getLeased());
      sf.append(" - poolStats.getMax() = "+poolStats.getMax());
      sf.append(" - poolStats.getPending() = "+poolStats.getPending());
      */
      logger.debug("-->connectionConfig = "+sf.toString());
   	 logger.debug("////////////////printConnectionConfig/////FIN///////////////////////////////////////");
   	 return sf.toString();
  }
    
    
    
    public static String printNetstat(){
	    String[] cmdarray = { "netstat", "-na" };
	    String line;
	    Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmdarray);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    StringBuffer retorno = new StringBuffer("");
	    try {
			while ((line = input.readLine()) != null) {
				if (line.contains("4321")||line.contains("1111")||line.contains("2222")){
						System.out.println(line);
				retorno.append(line).append("\n");
			}	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return retorno.toString();
    }
    
}
