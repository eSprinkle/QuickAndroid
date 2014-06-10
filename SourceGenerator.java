
/**
*	SourceGenerator generates java source for the view and id.
*	@organisation: eSprinkle
* 	@version: 1.0.4
**/

class SourceGenerator{

	private static void stringVerifier(String str,String type) throws LocalException{
		if(str==null){
			throw new LocalException("SourceGenerator.stringVerifier: "+type+" is null");
		}
	}

	private static String getClickListenerControlDecleration(String id){
		return "	private OnClickListener "+id+"Listener;";
	}

	private static String getClickListenerControlDefinition(String id){
		String str="";

		 str+="		 "+id+"Listener = new OnClickListener(){"+System.getProperty("line.separator");
		 str+="			@Override"+System.getProperty("line.separator");
		 str+="			public void onClick(View v) {"+System.getProperty("line.separator");
		 //str+="				"+id+"_click();"+" /* create a new function "+id+"_click() or remove this line and write your code here */ "+System.getProperty("line.separator");
		 str+="					/* eSprinkle-quickAndroid:click_event=["+id+"];  Write your code here */ "+System.getProperty("line.separator");
		 str+="			}"+System.getProperty("line.separator");
		 str+="		 };"+System.getProperty("line.separator");
		 str+="		 "+id+".setOnClickListener("+id+"Listener);"+System.getProperty("line.separator");

		 return str;
	}

	public static String getControlDefinition(String control, String id) throws LocalException{
		stringVerifier(id,"Id");
		stringVerifier(control,"Control");
		String definitionCode="		 "+id+" = ("+control+") findViewById(R.id."+id+");";

			if(control.equals("Button")==true){
				return definitionCode+System.getProperty("line.separator")+getClickListenerControlDefinition(id);
			}
		return definitionCode;
	}

	public static String getControlDecleration(String control, String id) throws LocalException{
		stringVerifier(id,"Id");
		stringVerifier(control,"Control");
		String declerationCode="	private "+control+" "+id+";";

		if(control.equals("Button")==true){
			return declerationCode+System.getProperty("line.separator")+getClickListenerControlDecleration(id);
		}
		return declerationCode;
	}
}
