package com.jatis.training.classroom.exception;

public class ServiceClassroomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceClassroomException() {
		super();
	}

	public ServiceClassroomException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceClassroomException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceClassroomException(String message) {
		super(message);
	}

	public ServiceClassroomException(Throwable cause) {
		super(cause);
	}

}
