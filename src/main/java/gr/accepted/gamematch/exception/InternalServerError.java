package gr.accepted.gamematch.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InternalServerError extends CustomException {

	private static final long serialVersionUID = 1L;

	public InternalServerError(String message) {
		super(message);
	}

	public InternalServerError(String message, String code) {
		super(message, code);
	}

	public InternalServerError(String message, String code, HttpStatus httpStatus) {
		super(message, code, httpStatus);
	}

}
