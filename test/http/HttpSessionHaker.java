package http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class HttpSessionHaker {
	public static void main(String[] args) throws Exception {
		String path = "http://192.168.1.157:8080/JavaWeb20210918/servlet/session/result";
		// String path = "http://localhost:8080/JavaWeb20210918/servlet/session/result";
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 加入 cookie
		connection.setRequestProperty("cookie", "JSESSIONID=9AC3983166985B713FF34AFB3B0D015D");
		connection.setRequestProperty("User-Agent", "Mozilla");
		connection.connect();
		InputStream is = connection.getInputStream();
		System.out.println(IOUtils.toString(is, "UTF-8"));

	}
}
