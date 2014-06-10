
/**
*	ViewType is reference type of View Tags
*	@organisation: eSprinkle
* 	@version: 1.0.0
**/

class ViewType{

	private String control;
	private String id;

	ViewType(String _control, String _id){
		control=_control;
		id=_id;
	}

	public String getControl(){
		return control;
	}
	public void setControl(String _control){
		this.control=_control;
	}
	public String getId(){
		return id;
	}
	public void setId(String _id){
		this.id=_id;
	}
}
