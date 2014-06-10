import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	ActivityReader would browse the xml file and list all the view in it
*	@organisation: eSprinkle
* 	@version: 1.0.3
**/

class ActivityReader{
	private BufferedReader br;
	private List<ViewType> list;
	private File file;
	private KnownViews knownViews;
	private String foundView="", foundId="";
	private Map<String,String> xmlMeta;

	ActivityReader(String fileName) throws IOException, LocalException{
		validateActivityFile(fileName);
		list = new ArrayList<ViewType>();
		br=new BufferedReader(new FileReader(file));
		knownViews = new KnownViews();
		xmlMeta=new HashMap<String,String>();
		
		String line;
		while((line=br.readLine())!=null){
			line=line.trim();
			//taking class inputs
			if(line.indexOf("eSprinkle-quickAndroid")>-1){
				String eSprinkleMeta=line.replaceAll("\\s", "");		
				String key=line.substring(eSprinkleMeta.indexOf(":")+2,eSprinkleMeta.indexOf("=")+1);
				String value=line.substring(eSprinkleMeta.indexOf("[")+2,eSprinkleMeta.indexOf("]")+1);
				xmlMeta.put(key, value);
			}
			
			
			if((line.indexOf("<")>-1)==true && knownViews.contains(line.substring(1)) && foundView.equals("") ){
				foundView=line.substring(1);
				continue;
			}

			if(!foundView.equals("")){
				line=line.replaceAll("\\s","");
				if(line.indexOf("android:id")>-1==true){
					foundId=line.substring(17,line.length()-1);
					continue;
				}

				//if((line.indexOf("/>")>-1)==true){ @ changed 
				if((line.indexOf(">")>-1)==true){
					if(!foundId.equals("")){
						list.add(new ViewType(foundView,foundId));
					}
					foundId="";
					foundView="";
				}
			}
		}
	}

	public List<ViewType> getActivityList(){
		return list;
	}
	public Map<String,String> getActivityMeta(){
		return xmlMeta;
	}
	
	private void validateActivityFile(String fileName) throws LocalException{
		if(fileName==null){
			throw new LocalException("ActivityReader.validateActivityFile: Activity File is null");
		}

		file=new File(fileName);
		if(!file.exists())
			throw new LocalException("ActivityReader.validateActivityFile: Activity File not found");
	}

}
