package MyHelloWorld;

import java.net.URL;
import java.util.concurrent.Callable;

public class DownLoadCall implements Callable<String> {

	private URL url;
	
	public DownLoadCall(URL u) {
		url=u;
	}
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
