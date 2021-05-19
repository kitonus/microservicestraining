package com.jatis.training.classroom.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

	private static final ThreadLocal<HttpSession> sessionLocal = new ThreadLocal<>();
	
	public static void set(HttpSession session) {
		sessionLocal.set(session);
	}
	
	public static void remove() {
		sessionLocal.remove();
	}
	
	public static Object getSessionAttribute(String key) {
		HttpSession session = sessionLocal.get();
		if (session == null) {
			return null;
		}
		return session.getAttribute(key);
	}
}
