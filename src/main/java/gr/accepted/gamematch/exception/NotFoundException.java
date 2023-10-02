package gr.accepted.gamematch.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String message) {
		super(message);
	}

}
