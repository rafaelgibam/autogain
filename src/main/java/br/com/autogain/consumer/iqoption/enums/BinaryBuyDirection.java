package br.com.autogain.consumer.iqoption.enums;

public enum BinaryBuyDirection {
	
	CALL("call"),
	PUT("put");
	
	private String direction;
	
	private BinaryBuyDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return direction;
	}

}
