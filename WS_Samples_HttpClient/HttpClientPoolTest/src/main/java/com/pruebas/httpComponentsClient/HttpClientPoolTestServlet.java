package com.pruebas.httpComponentsClient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class HttpClientPoolTestServlet
 */
public class HttpClientPoolTestServlet extends HttpServlet {
    
    
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger( HttpClientPoolTestServlet.class );
    
    /**
     * Para la version 4.5.2
     */
    //public CloseableHttpClient httpclient = null;
    
    public HttpClient httpclient = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpClientPoolTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
        init452();
        init426();
        super.init();
    }
    
    private void init452() {
        //        //C:\CARLOS\programacion\MockServers\wiremock\software\wiremock-standalone-2.2.2\documentacion\ResumenWiremock.docx
        //        
        //        /*
        //         * cd C:\CARLOS\programacion\MockServers\wiremock\software\wiremock-standalone-2.2.2 java -jar
        //         * wiremock-standalone-2.2.2.jar --port 9999
        //         */
        //        //http://localhost:8080/HttpClientPoolTest/HttpClientPoolTestServlet
        //        
        //        // Create an HttpClient with the ThreadSafeClientConnManager.
        //        // This connection manager must be used if more than one thread will
        //        // be using the HttpClient.
        //        ConnectionManager.cm = new PoolingHttpClientConnectionManager();
        //        
        //        //cm.setMaxTotal(100);
        //        ConnectionManager.cm.setMaxTotal( 15 );
        //        /*
        //         * poolStats.toString() = [leased: 2; conexiones que han sido sacadas del pool y que estan ejecutando una
        //         * request pending: 0; solicitudes de conexiones del pool pendientes a la espera de que se liberen conexiones
        //         * available: 0; conexiones creadas disponibles en el pool max: 2] numero maximo de conexiones en el el pool
        //         */
        //        ConnectionManager.cm.setDefaultMaxPerRoute( 4 );
        //        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "test1.server.es", 1111 ) ), 1 );
        //        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "test2.server.es", 2222 ) ), 2 );
        //        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "127.0.0.1", 4231 ) ), 3 );
        //        /*
        //         * PerRoute podria ser util si tenemos varias urls/f2e cm.setDefaultMaxPerRoute(3);
        //         * C:\Windows\System32\drivers\etc\hosts 127.0.0.1 test1.server.es cm.setMaxPerRoute(new HttpRoute(new
        //         * HttpHost("test1.server.es", 1111)), 1); netstat -na|find "1111" cm.setMaxPerRoute(new HttpRoute(new
        //         * HttpHost("test2.server.es", 2222)), 2); netstat -na|find "2222"
        //         */
        //        // Create socket configuration
        //        SocketConfig socketConfig = SocketConfig.custom()
        //                //.setSoTimeout(100)//read timeout
        //                .build();
        //        /*
        //         * 2016/11/04 22:07:44:852 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route:
        //         * {}->http://test1.server.es:1111][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
        //         * Thread-6 22:07:44,979 DEBUG [ ClientMultiThreadedExecution] 6 - error: java.net.SocketTimeoutException: Read
        //         * timed out
        //         */
        //        
        //        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        //        //requestBuilder = requestBuilder.setConnectTimeout(1);//connection timeout to an HTTP server
        //        //error: org.apache.http.conn.ConnectTimeoutException: Connect to test2.server.es:2222 [test2.server.es/127.0.0.1] failed: connect timed out
        //        
        //        //requestBuilder = requestBuilder.setConnectionRequestTimeout(100);//waiting for an available connection from an HttpConnectionManager.
        //        /*
        //         * 2016/11/04 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route:
        //         * {}->http://localhost:4321][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2] 2016/11/04
        //         * 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 1][route:
        //         * {}->http://test2.server.es:2222][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
        //         * Thread-7 22:10:19,236 DEBUG [ ClientMultiThreadedExecution] 7 - error:
        //         * org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
        //         */
        //        
        //        // Configure the connection manager to use socket configuration either
        //        // by default or for a specific host.
        //        ConnectionManager.cm.setDefaultSocketConfig( socketConfig );
        //        
        //        HttpClientBuilder builder = HttpClientBuilder.create();
        //        builder.setDefaultRequestConfig( requestBuilder.build() );
        //        //HttpClient httpclient = builder.build();
        //        httpclient = (CloseableHttpClient) builder.setConnectionManager( ConnectionManager.cm ).build();
        
        /*
         * CloseableHttpClient httpclient = HttpClients.custom() .setConnectionManager(cm) .build();
         */
        
    }
    
    /**
     * @throws ServletException
     */
    private void init426() throws ServletException {
        /*
         * wiremock-standalone-2.2.2.jar --port 9999
         */
        //http://localhost:8080/HttpClientPoolTest/HttpClientPoolTestServlet
        
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        ConnectionManager.cm = new PoolingClientConnectionManager();
        
        //cm.setMaxTotal(100);
        ConnectionManager.cm.setMaxTotal( 15 );
        /*
         * poolStats.toString() = [leased: 2; conexiones que han sido sacadas del pool y que estan ejecutando una
         * request pending: 0; solicitudes de conexiones del pool pendientes a la espera de que se liberen conexiones
         * available: 0; conexiones creadas disponibles en el pool max: 2] numero maximo de conexiones en el el pool
         */
        ConnectionManager.cm.setDefaultMaxPerRoute( 4 );
        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "test1.server.es", 1111 ) ), 1 );
        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "test2.server.es", 2222 ) ), 2 );
        ConnectionManager.cm.setMaxPerRoute( new HttpRoute( new HttpHost( "127.0.0.1", 4321 ) ), 3 );
        /*
         * PerRoute podria ser util si tenemos varias urls/f2e cm.setDefaultMaxPerRoute(3);
         * C:\Windows\System32\drivers\etc\hosts 127.0.0.1 test1.server.es cm.setMaxPerRoute(new HttpRoute(new
         * HttpHost("test1.server.es", 1111)), 1); netstat -na|find "1111" cm.setMaxPerRoute(new HttpRoute(new
         * HttpHost("test2.server.es", 2222)), 2); netstat -na|find "2222"
         */
        // Create socket configuration
        //SocketConfig socketConfig = SocketConfig.custom()
        //.setSoTimeout(100)//read timeout
        //.build();
        /*
         * 2016/11/04 22:07:44:852 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route:
         * {}->http://test1.server.es:1111][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
         * Thread-6 22:07:44,979 DEBUG [ ClientMultiThreadedExecution] 6 - error: java.net.SocketTimeoutException: Read
         * timed out
         */
        
        //RequestConfig.Builder requestBuilder = RequestConfig.custom();
        //requestBuilder = requestBuilder.setConnectTimeout(1);//connection timeout to an HTTP server
        //error: org.apache.http.conn.ConnectTimeoutException: Connect to test2.server.es:2222 [test2.server.es/127.0.0.1] failed: connect timed out
        
        //requestBuilder = requestBuilder.setConnectionRequestTimeout(100);//waiting for an available connection from an HttpConnectionManager.
        /*
         * 2016/11/04 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 0][route:
         * {}->http://localhost:4321][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2] 2016/11/04
         * 22:10:19:135 CET [DEBUG] PoolingHttpClientConnectionManager - Connection leased: [id: 1][route:
         * {}->http://test2.server.es:2222][total kept alive: 0; route allocated: 1 of 2; total allocated: 2 of 2]
         * Thread-7 22:10:19,236 DEBUG [ ClientMultiThreadedExecution] 7 - error:
         * org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
         */
        
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        //ConnectionManager.cm.setDefaultSocketConfig( socketConfig );
        
        //HttpClientBuilder builder = new HttpClientBuilder();
        //builder.setDefaultRequestConfig( requestBuilder.build() );
        HttpParams params = new BasicHttpParams();
        params.setParameter( CoreConnectionPNames.CONNECTION_TIMEOUT, 40000 );
        params.setParameter( CoreConnectionPNames.SO_TIMEOUT, 40000 );
        httpclient = new DefaultHttpClient( ConnectionManager.cm, params );
        //HttpClient httpclient = builder.build();
        //httpclient = (CloseableHttpClient) builder.setConnectionManager( ConnectionManager.cm ).build();
        
        /*
         * CloseableHttpClient httpclient = HttpClients.custom() .setConnectionManager(cm) .build();
         */
        
        // TODO Auto-generated method stub
    }
    
    String[] urisToGet = {
            "http://127.0.0.1:4321/",
            "http://127.0.0.1:4321/httpcomponents-core-ga/",
            "http://127.0.0.1:4321/httpcomponents-client-ga/",
            "http://test1.server.es:1111/",
            "http://test1.server.es:1111/httpcomponents-core-ga/",
            "http://test1.server.es:1111/httpcomponents-client-ga/",
            "http://test2.server.es:2222/",
            "http://test2.server.es:2222/httpcomponents-core-ga/",
            "http://test2.server.es:2222/httpcomponents-client-ga/",
    };
    
    public static int i = 0;
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //http://localhost:8080/HttpClientPoolTest/HttpClientPoolTestServlet
        System.out.println( "-->HttpClientPoolTestServlet-->doGet -->i =" + i );
        if (i == 9) {
            i = 0;
        }
        HttpGet httpget = new HttpGet( urisToGet[i] );
        i++;
        // TODO Auto-generated method stub
        byte[] bytes = null;
        try {
            //printTotalStats(cm);
            long time1 = System.currentTimeMillis();
            logger.debug( i + " - about to get something from " + httpget.getURI() + " - time1 = " + time1 );
            BasicHttpContext context = new BasicHttpContext();
            HttpResponse response2 = httpclient.execute( httpget, context );
            long time2 = System.currentTimeMillis();
            logger.debug( i + " - about to get something from " + httpget.getURI() + " - time2 = " + time2
                    + " - time2-time1 = " + ( time2 - time1 ) );
            //printTotalStats(cm);
            
            try {
                logger.debug( i + " - get executed" );
                // get the response body as an array of bytes
                HttpEntity entity = response2.getEntity();
                if (entity != null) {
                    /* byte[] */ bytes = EntityUtils.toByteArray( entity );
                    logger.debug( i + " - " + bytes.length + " bytes read" );
                }
            } finally {
                //4.5.2
                //response2.close();
            }
        } catch (Exception e) {
            logger.debug( i + " - error: " + e );
        }
        ServletOutputStream sos = response.getOutputStream();
        sos.print( httpget.getURI().toString() );
        sos.flush();
        sos.close();
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }
    
}
