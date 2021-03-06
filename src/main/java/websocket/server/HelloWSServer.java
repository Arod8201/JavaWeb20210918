package websocket.server;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hello/ws/server")
public class HelloWSServer {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Client 要求建立連線 session is: " + session.getId());
		// 回報給 client
		session.getAsyncRemote().sendText("Server 說: 建立成功, session id: " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("Client 傳送訊息: " + message);
		if (session.isOpen()) {
			String data = "Server 說: %s 有 %d 個字 <span style='font-size:9px;color: gray;'>(%s)</span>";
			data = String.format(data, message, message.length(), new Date());
			session.getAsyncRemote().sendText(data);
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("Client 要求關閉連線: " + session.getId());
	}
}