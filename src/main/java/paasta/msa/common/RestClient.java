package paasta.msa.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	private static String call(HttpUriRequest request) throws Exception {
        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer response = null;
        
        try {
            //get 요청
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(statusCode == 200) {
                BufferedReader reader = null;
         
                try {
                	reader = new BufferedReader(new InputStreamReader(
                            httpResponse.getEntity().getContent()));
                    String inputLine;
                    response = new StringBuffer();
             
                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }
                } catch (Exception e) {
                	throw e;
                } finally {
                	try {reader.close();} catch (Exception e) {}
                }
                
            } else {
            	throw new Exception();
            }
        } catch (Exception e) {
        	throw e;
        } finally {
            httpClient.close();
        }
 
		return response.toString();
	}

	public static String get(String url, Map<String, String> headerMap, Map<String, String> paramMap) throws Exception {
 
        Set<String> keySet = paramMap.keySet();
        Iterator<String> it = keySet.iterator();
        StringBuffer paramStringBf = new StringBuffer();

        while(it.hasNext()) {
        	String key = it.next();
        	paramStringBf.append(key);
        	paramStringBf.append("=");
        	paramStringBf.append(paramMap.get(key));
        	paramStringBf.append("&");
        }
		String paramString = paramStringBf.toString();
		if(!"".equals(paramString)) {
			paramString = "?" + paramString.substring(0, paramString.length() -1);
		}
        //get 메서드와 URL 설정
        HttpGet httpGet = new HttpGet(url + paramString);
 
        keySet = headerMap.keySet();
        it = keySet.iterator();

        while(it.hasNext()) {
        	String key = it.next();
            httpGet.addHeader(key, headerMap.get(key));
        }
        
        
        return call(httpGet);
	}
}
