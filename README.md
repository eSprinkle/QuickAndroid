QuickAndroid
============

Generates Java source for activity declarations in the XML layout.

For example:
An Android activity XML may contain following view:

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
		
The use this project is to generate the above source.

===========
eSprinkle Open Source
