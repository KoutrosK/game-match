package gr.accepted.gamematch.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private String requestUri;
	private HttpStatus httpStatus;

}
