package gr.accepted.gamematch.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String INVALID_REQUEST_ERROR = "INVALID_REQUEST_ERROR";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String CODE_ERROR = "ERROR";
	public static final String CODE_WARNING = "WARNING";

	private final ExceptionData exceptionData = new ExceptionData();

	public CustomException(String message) {
		super(message);
		this.exceptionData.setMessage(message);
	}

	public CustomException(String message, String code) {
		super(message);
		this.exceptionData.setCode(code);
		this.exceptionData.setMessage(message);
	}

	public CustomException(String message, String code, HttpStatus httpStatus) {
		super(message);
		this.exceptionData.setCode(code);
		this.exceptionData.setMessage(message);
		this.exceptionData.setHttpStatus(httpStatus);
	}

}
