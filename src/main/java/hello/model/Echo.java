package hello.model;

public class Echo {

	private String message;
	
	public Echo(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
}
