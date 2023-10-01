package gr.accepted.gamematch.model;

public enum Sport {

	FOOTBALL(1), BASKETBALL(2);

	private Integer code;

	private Sport(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
