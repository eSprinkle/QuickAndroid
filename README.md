eSprinkle QuickAndroid - Boost your android development with "Quick Android".
============

Generates Java source for activity declarations in the XML layout.


Sample
============
An Android activity XML would contain following view decelaration:

    <EditText
        android:id="@+id/userUnique"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10" 
        android:padding="20sp"
        android:inputType="text" />
        
    <Button
        android:id="@+id/loginOk"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/ok"
        android:layout_below="@id/password"
        android:padding="20sp" />
    
In order to map the layout with the Java source we would be writing following code everytime:
 
       private EditText userUnique;
	  private Button loginOk;	
	  
	  userUnique = (EditText) findViewById(R.id.userUnique);
	  loginOk = (Button) findViewById(R.id.loginOk);
		loginOkListener = new OnClickListener(){
			@Override
			public void onClick(View v) {
				/* Write your code here */ 
			}
		};
		loginOk.setOnClickListener(loginOkListener);
		
This project aimed to generate the mapping source of xml.

Usage
===========
1. Copy all the compiled sources in your android project root folder
2. Verify the Views in "ViewList.txt" and add few tags which are required for your project.
3. Specify eSprinkle tags as below in your xml file inside the comments as below:
	
<?xml version="1.0" encoding="utf-8"?>
<!-- eSprinkle-quickAndroid:package=[com.esprinkle.apps.android.auth] -->
<!-- eSprinkle-quickAndroid:class=[LoginActivity] -->
<!-- eSprinkle-quickAndroid:is_new=[true] -->

4. There tags can be anywhere inside the layout file but they should exists.
5. Run the sources "QuickAndroidMain" and provide the xml file name. 


Documentation
===========

eSprinkle-quickAndroid:package 
	=> denotes under which package the class should be placed.
	=> exclude this tag for default package

eSprinkle-quickAndroid:class   
	=> denotes the class name
	
eSprinkle-quickAndroid:is_new  
	=> it is mandatory tag and for now it should be set as true, 
	=> We are building the next version in which 'false' specifies the class is already created and need to create mappings only for the missing.

eSprinkle-quickAndroid:sync_enabled  
	=> As soon as you save your changes in xml file, 
	=> The mappings will be reflected in java file, (currently not available)


Future
===========
1. Able to map the view for existing classes.
2. Realtime sync with layout file.
3. Would be available as an eclipse plugin

===========
eSprinkle Open Source
