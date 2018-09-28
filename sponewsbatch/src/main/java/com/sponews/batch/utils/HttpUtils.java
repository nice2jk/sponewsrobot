package com.sponews.batch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpUtils {

private static HttpUtils httpUtils;
	
	public static HttpUtils getInstance() {
		if(httpUtils == null) {
			httpUtils = new HttpUtils();
		}
		
		return httpUtils;
	}
	
	public String fetch(String address) {
		
		HttpURLConnection con;		
		
		try {
			URL url = new URL(address);
			
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(false);
			
			System.out.println("Http Status : " + con.getResponseCode());
			
			return getBufferdString(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getBufferdString(InputStream ips) {
		BufferedReader br;
		
		StringBuffer sb = new StringBuffer();
		String str = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(ips, "euc-kr"));
			
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getHttpHTML(String urlToRead) {
	    try {

	        /*conn.setRequestMethod("GET");*/
	        
	        int count = 0;
	        
			URL url = new URL(urlToRead);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
			connection.setRequestProperty("Content-Type", "text/html;charset=euc-kr");
			connection.setRequestProperty("Cache-Control", "no-cache");
			
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "euc-kr"));

		    StringBuffer sb = new StringBuffer();
		    String str = "";
		    		    
			while((str = br.readLine())!=null){				
				sb.append(str).append("\n");
			}
			
			br.close();

			/*System.out.println(sb.toString());*/
			return sb.toString();	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public String getjsoup(String url) {
		String data ="";
		System.out.println(data + "d");
		try {
			Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();
Document google3 = response.parse();
System.out.println(google3.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
	}
	
	public String getClient(String url) {
		String data = "";
		
		try {
			
		
		//http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();



		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
		httpGet.addHeader("cache-control", "no-cache");
		//get 요청
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        
        System.out.println("::GET Response Status::");
        
        //response의 status 코드 출력
        System.out.println(httpResponse.getStatusLine().getStatusCode());
 
        
        try {
            System.out.println(httpResponse.getStatusLine());
            HttpEntity entity1 = httpResponse.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
            
            BufferedReader rd = new BufferedReader(
            		new InputStreamReader(httpResponse.getEntity().getContent()));

            	StringBuffer result = new StringBuffer();
            	String line = "";
            	while ((line = rd.readLine()) != null) {
            		result.append(line);
            	}
            
            	System.out.println(result.toString());
            
        } finally {
        	httpResponse.close();
        }
/*        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
 
        String inputLine;
        StringBuffer response = new StringBuffer();
 
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        
        reader.close();
 
        //Print result
        System.out.println(response.toString());
        httpClient.close();*/

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return data;
	}
}
