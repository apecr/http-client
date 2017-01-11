package pruebas.netstat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Netstat {

	public static void main(String[] args) throws Exception {
	    String[] cmdarray = { "netstat", "-na" };
	    String line;
	    Process process = Runtime.getRuntime().exec(cmdarray);
	    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((line = input.readLine()) != null) {
	    	if (line.contains("135")||line.contains("445"))
	    			System.out.println(line);
	    }
	    input.close();
  
	}

}
