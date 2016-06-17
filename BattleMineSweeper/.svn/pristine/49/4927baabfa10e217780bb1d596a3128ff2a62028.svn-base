package domain.value;

import domain.exception.ToolongException;

public class Message  extends Value
{
	public Message(String message) throws ToolongException {
		super();
		if( message.length() > 200 )
			throw new ToolongException();
		this.message = message;
	}
	private String message;

	public String getName() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}


}
