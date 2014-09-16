package MyHelloWorld;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;

public class MyHelloWorld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
/*		System.out.print("Hello World£¡");
		URL pageUrl = new URL("http://www.baidu.com");
		InputStream stream = pageUrl.openStream();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.baidu.com");
		CloseableHttpResponse response = httpClient.execute(get);
		URI uri = new URIBuilder()
		.setScheme("http")
		.setHost("www.baidu.com")
		.build();*/
		
		 Parser parser = new Parser ("http://whatever");
		
		
		ITest myInterface = new ITest()
		{
			public String MyTestInterface(int iInt)
			{
				return "1";
			}
		};
		

		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com");
            HttpPost httppost = new HttpPost();
            
            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
		
        int threads = 4;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        Set<Future<String>> set = new HashSet<Future<String>>();
        Set<URL> urls = new HashSet<URL>();
        for(final URL url:urls){
        	DownLoadCall taskCall = new DownLoadCall(url);
        	Future<String> future = pool.submit(taskCall);
        	set.add(future);
        }
        
	}

}
