package service.bean;

import domain.exception.ToolongException;
import domain.value.Message;

public class Bean {
	private Message message;
	public Bean()
	{
		try {
			message = new Message("");
		} catch (ToolongException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}

