/**
*	LocalException
*	@organisation: eSprinkle
* 	@version: 1.0.1
**/

@SuppressWarnings("serial")
class LocalException extends Exception{
	public LocalException (){}
	public LocalException (String message){
		super (message);
	}
}
