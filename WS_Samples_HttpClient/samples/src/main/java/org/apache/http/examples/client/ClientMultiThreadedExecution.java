/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.examples.client;

import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * An example that performs GETs from multiple threads.
 *
 */
public class ClientMultiThreadedExecution {
	private static Logger logger = Logger.getLogger(ClientMultiThreadedExecution.class);
	private static PoolingHttpClientConnectionManager cm = null;
    public static void main(String[] args) throws Exception {
    	
    	
    	//C:\CARLOS\programacion\MockServers\wiremock\software\wiremock-standalone-2.2.2\documentacion\ResumenWiremock.docx
    	
    	/*
			cd C:\CARLOS\programacion\MockServers\wiremock\software\wiremock-standalone-2.2.2
			java -jar wiremock-standalone-2.2.2.jar
    	 */
    	
    	
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        cm = new PoolingHttpClientConnectionManager();
        
        


        
        
        //cm.setMaxTotal(100);
        cm.setMaxTotal(2);
        /*
        poolStats.toString() = [leased: 2; conexiones que han sido sacadas del pool y que stan ejecutando una request
        						pending: 0; solicitudes de conexiones del pool pendientes a la espera de que se liberen conexiones
        						available: 0; conexiones creadas disponibles en el pool
        						max: 2]  numero máximo de conexiones en el el pool
        */        						
//        cm.setDefaultMaxPerRoute(3);
        //C:\Windows\System32\drivers\etc\hosts
        //127.0.0.1   test1.server.es
//        cm.setMaxPerRoute(new HttpRoute(new HttpHost("test1.server.es", 1111)), 1);
        //netstat -na|find "1111"
//        cm.setMaxPerRoute(new HttpRoute(new HttpHost("test2.server.es", 2222)), 2);
        //netstat -na|find "2222"
        
        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom()
            //.setSoTimeout(100)//read timeout
            .build();
/*
2016/11/04 22:07:44:852 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route: {}->http://test1.server.es:1111][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
 Thread-6 22:07:44,979 DEBUG [       ClientMultiThreadedExecution] 6 - error: java.net.SocketTimeoutException: Read timed out 
 * */        
        
        
        
        
        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        //requestBuilder = requestBuilder.setConnectTimeout(1);//connecting to an HTTP server
        //error: org.apache.http.conn.ConnectTimeoutException: Connect to test2.server.es:2222 [test2.server.es/127.0.0.1] failed: connect timed out
        
        
        
        
        //requestBuilder = requestBuilder.setConnectionRequestTimeout(100);//waiting for an available connection from an HttpConnectionManager.
/*
2016/11/04 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route: {}->http://localhost:4321][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
2016/11/04 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 1][route: {}->http://test2.server.es:2222][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
Thread-7 22:10:19,236 DEBUG [       ClientMultiThreadedExecution] 7 - error: org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
 * */        
        
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        cm.setDefaultSocketConfig(socketConfig);
        
        HttpClientBuilder builder = HttpClientBuilder.create();     
        builder.setDefaultRequestConfig(requestBuilder.build());
        //HttpClient httpclient = builder.build();
        CloseableHttpClient httpclient = (CloseableHttpClient)builder.setConnectionManager(cm).build();
        
        
        
/*        
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
*/
        
        printRoutes(cm);
        printTotalStats(cm);


        

        try {
// create an array of URIs to perform GETs on
/*        	
            String[] urisToGet = {
                "http://hc.apache.org/",
                "http://hc.apache.org/httpcomponents-core-ga/",
                "http://hc.apache.org/httpcomponents-client-ga/",
            };
*/
        	
//URLS para apuntar al proxy de WireMock y que este mokee las peticiones        	
/*
            String[] urisToGet = {
                    "http://localhost:4321/",
                    "http://localhost:4321/httpcomponents-core-ga/",
                    "http://localhost:4321/httpcomponents-client-ga/",
                };
*/ 
//URLS para apuntar a WireMock y que este responda con las peticiones mokeadas 
/*        	
            String[] urisToGet = {
                    "http://localhost:8080/",
                    "http://localhost:8080/httpcomponents-core-ga/",
                    "http://localhost:8080/httpcomponents-client-ga/",
                };
*/
//URLS para apuntar a proxy eclipese por delante de WireMock y que este responda con las peticiones mokeadas   	
/*        	
            String[] urisToGet = {
                    "http://localhost:4321/",
                    "http://localhost:4321/httpcomponents-core-ga/",
                    "http://localhost:4321/httpcomponents-client-ga/",
                };
*/                
        	
            String[] urisToGet = {
                    "http://localhost:4321/",
                    "http://localhost:4321/httpcomponents-core-ga/",
                    "http://localhost:4321/httpcomponents-client-ga/",
                    "http://test1.server.es:1111/",
                    "http://test1.server.es:1111/httpcomponents-core-ga/",
                    "http://test1.server.es:1111/httpcomponents-client-ga/",
                    "http://test2.server.es:2222/",
                    "http://test2.server.es:2222/httpcomponents-core-ga/",
                    "http://test2.server.es:2222/httpcomponents-client-ga/",                    
                };
        	
        	Monitoring monitoring = new Monitoring();
        	monitoring.start();
            // create a thread for each URI
            GetThread[] threads = new GetThread[urisToGet.length];
            for (int i = 0; i < threads.length; i++) {
                HttpGet httpget = new HttpGet(urisToGet[i]);
                
                threads[i] = new GetThread(httpclient, httpget, i + 1);
            }

            // start the threads
            for (int j = 0; j < threads.length; j++) {

            	logger.debug("-->Antes de start j = "+j);
                threads[j].start();
Thread.sleep(2000);
            	logger.debug("-->Despues de start j = "+j);
            }

            // join the threads
            for (int j = 0; j < threads.length; j++) {
            	logger.debug("-->Antes de join j = "+j);
                threads[j].join();
            	logger.debug("-->Despues de join j = "+j);
            }

        } finally {
        	logger.debug("////////////////////////////////////////////////////////////");
        	logger.debug("////////////////////////////////////////////////////////////");
        	logger.debug("////////////////////////////////////////////////////////////");
        	logger.debug("////////////////////////////////////////////////////////////");
        	logger.debug("-->Antes de httpclient.close()");
        	Thread.sleep(300000);
            httpclient.close();
            logger.debug("-->Despues de httpclient.close()");
        }
    }

    /**
     * A thread that performs a GET.
     */
    static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;
        private final int id;

        public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
            this.id = id;
        }

        /**
         * Executes the GetMethod and prints some status information.
         */
        @Override
        public void run() {
            try {
            	//printTotalStats(cm);
            	long time1 = System.currentTimeMillis();
                logger.debug(id + " - about to get something from " + httpget.getURI() + " - time1 = "+time1);
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                long time2 = System.currentTimeMillis();
                logger.debug(id + " - about to get something from " + httpget.getURI() + " - time2 = "+time2+ " - time2-time1 = "+(time2-time1));
                //printTotalStats(cm);
                try {
                    logger.debug(id + " - get executed");
                    // get the response body as an array of bytes
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        byte[] bytes = EntityUtils.toByteArray(entity);
                        logger.debug(id + " - " + bytes.length + " bytes read");
                    }
                } finally {
                    response.close();
                }
            } catch (Exception e) {
                logger.debug(id + " - error: " + e);
            }
        }

    }
    
    
    
    
    static class Monitoring extends Thread {



        public Monitoring() {

        }

        /**
         * Executes the GetMethod and prints some status information.
         */
        @Override
        public void run() {
            try {
            	while(true){
            		printTotalStats(cm);
            		Thread.sleep(1000);
            	}
 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    
    
    public static void printRoutes(PoolingHttpClientConnectionManager cm){
        Set<HttpRoute> routes =cm.getRoutes();
        Iterator iter = routes.iterator();
        HttpRoute route;
        
        logger.debug("-->routes.isEmpty() = "+routes.isEmpty());
        while (iter.hasNext()) {
            route = (HttpRoute)iter.next();
            logger.debug("-->route.getTargetHost().getHostName() = "+route.getTargetHost().getHostName());
        }
    }
    
    public static void printTotalStats(PoolingHttpClientConnectionManager cm){
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
    }
    

}
