import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
*	KnownViews will filter views as specified in ViewList.txt
*	@organisation: eSprinkle
* 	@version: 1.0.1
**/

class KnownViews{
	private BufferedReader br;
	private List<String> list;

	KnownViews() throws IOException{
		br=new BufferedReader(new FileReader("ViewList.txt"));
		list = new ArrayList<String>();
		String line=null;
		while((line=br.readLine())!=null){
			list.add(line.trim());
		}
	}
	public boolean contains(String str){
		return list.contains(str);
	}

}
