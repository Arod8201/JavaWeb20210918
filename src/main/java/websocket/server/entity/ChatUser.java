package websocket.server.entity;

import java.util.Objects;

import javax.websocket.Session;

public class ChatUser {
	private String nickname;
	private String data;
	private Session session;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname, session);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatUser other = (ChatUser) obj;
		return Objects.equals(nickname, other.nickname) && Objects.equals(session, other.session);
	}

	@Override
	public String toString() {
		return "ChatUser [nickname=" + nickname + ", data=" + data + ", session=" + session + "]";
	}

}
