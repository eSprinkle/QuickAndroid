
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
*	CreateClass is used to create a new java file with view mappings embeded in it
*	@organisation: eSprinkle
* 	@version: 1.0.0
**/

class CreateClass{

	private BufferedWriter classWriter;

	public boolean embedCode(List<ViewType> activityList,String xmlSourceFileName,String packageName,String className,Path classLocationPath) throws IOException,LocalException{
		File dir = new File(classLocationPath.getParent().toAbsolutePath().toString());
		dir.mkdirs();

		classWriter=new BufferedWriter(new FileWriter(classLocationPath.toString()));
		headerClassCodings(packageName,className);
		
		//declerations
		classWriter.newLine();
		classWriter.write("	/* eSprinkle-quickAndroid:decleration_begin=[true] */");
		classWriter.newLine();
		for(ViewType vt:activityList){
			classWriter.write(SourceGenerator.getControlDecleration(vt.getControl(),vt.getId()));
			classWriter.newLine();
		}
		classWriter.write("	/* eSprinkle-quickAndroid:decleration_end=[true] */");
		classWriter.newLine();
		
		
		intermediateClassCodings();
		//other class def

		classWriter.write("		 /* eSprinkle-quickAndroid:definition_begin=[true] */");
		classWriter.newLine();
		for(ViewType vt:activityList){
			classWriter.write(SourceGenerator.getControlDefinition(vt.getControl(),vt.getId()));
			classWriter.newLine();
		}
		classWriter.write("		 /* eSprinkle-quickAndroid:definition_end=[true]; Start your code from below */");
		classWriter.newLine();

		//other class def
		endClassCodings();
		classWriter.close();
		
		return true;
	}

	private void headerClassCodings(String pack,String className) throws IOException{
		if(pack!=null){
			classWriter.write("package "+pack+";");
			classWriter.newLine();
		}
			
			classWriter.write("import android.app.Activity;");
			classWriter.newLine();
			classWriter.write("import android.os.Bundle;");
			
			classWriter.newLine();
			classWriter.newLine();
			
			classWriter.write("/* eSprinkle-quickAndroid:generatedClass=["+className+"] */");
			classWriter.newLine();

			classWriter.write("public class "+className+" extends Activity{");
			classWriter.newLine();
	}
	
	private void intermediateClassCodings() throws IOException{
		classWriter.newLine();
		classWriter.write("	@Override");
		classWriter.newLine();
		classWriter.write("	protected void onCreate(Bundle savedInstanceState) {");
		classWriter.newLine();
		classWriter.write("		super.onCreate(savedInstanceState);");
		classWriter.newLine();
		classWriter.newLine();
	}

	private void endClassCodings() throws IOException{
		classWriter.newLine();
		classWriter.newLine();
		classWriter.newLine();
		classWriter.write("	}");
		classWriter.newLine();
		classWriter.write("}");
		classWriter.newLine();
	}
}
