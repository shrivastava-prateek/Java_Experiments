package com.psl.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RESTClient {
	
	
	// http://localhost:8080/RESTfulExample/json/product/get
		public static JSONObject  restServiceCall(String strURL,String httpMethod,String body) throws ParseException {

		  try {

			URL url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty("Accept", "application/json");
			
			if(httpMethod.equals("POST")){
				conn.setRequestProperty("Content-Type","application/json");
				conn.setDoOutput(true);
				OutputStream os = conn.getOutputStream();
				os.write(body.getBytes());
				os.flush();
				os.close();
			}
			
			

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			StringBuffer sb = new StringBuffer();
			String output = null;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(sb.append(output));
			}

			JSONParser parse = new JSONParser(); 
			JSONObject jobj = (JSONObject)parse.parse(sb.toString()); 
			
			conn.disconnect();
			
			return jobj;

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  return null;

		}


}
