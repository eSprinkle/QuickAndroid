import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
*	QuickAndroidMain
*	@organisation: eSprinkle
* 	@version: 1.0.1
**/

public class QuickAndroidMain {
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner s=new Scanner(System.in);
		try{
			
			System.out.println("Please input Layout File Name [ex: Login.xml]");
			String layoutFileName=s.next().toString();
			if(layoutFileName==null||layoutFileName.trim().equals("")){
				System.out.println("Exiting program ");
				return;
			}
			
			Path thisFolder=Paths.get(System.getProperty("user.dir"));
	
			ActivityReader ar=new ActivityReader(thisFolder.resolve(Paths.get("res/layout/"+layoutFileName)).toAbsolutePath().toString());
			List<ViewType> viewList=ar.getActivityList();
			Map<String,String> meta=ar.getActivityMeta();

			if(meta.get("is_new").equals("true")){
				CreateClass javaClass=new CreateClass();
				String className=(meta.get("class")!=null?meta.get("class"):layoutFileName.substring(0,layoutFileName.length()-4));
				className=className.substring(0,1).toUpperCase()+className.substring(1).toLowerCase();
				Path classLocationPath=thisFolder.resolve(Paths.get("src/"+(meta.get("package")!=null?meta.get("package").replaceAll("\\.", "/"):"")+"/"+className+".java").toAbsolutePath());
				
				//Override warning
				if(Files.exists(classLocationPath)){
					System.out.println("You can also change the class name in layout file. ");
					System.out.println("The "+className+" already exists, Do you want to override? [Y/N]");
					String overrideConfirmation=s.next().toString();
					if(overrideConfirmation==null||overrideConfirmation.trim().equals("")||!overrideConfirmation.trim().equals("Y")){
						System.out.println("Exiting program ");
						return;
					}
				}
				if(javaClass.embedCode(viewList, layoutFileName,meta.get("package"),className,classLocationPath)){
					System.out.println("Class file created successfully, refresh src folder");
				}
			}
		}
		catch(Exception ex){
			System.err.print(ex.toString());
			System.out.println("Press enter to exit ");
			s.next();
		}
	}
}
