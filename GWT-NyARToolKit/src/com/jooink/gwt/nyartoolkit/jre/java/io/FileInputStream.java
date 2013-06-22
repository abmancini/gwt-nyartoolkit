package java.io;

import java.io.InputStream;

import com.google.gwt.user.client.Window;

public  class FileInputStream  extends InputStream {

	
	
	public FileInputStream(String filename) {
		Window.alert("Ola, FileInputStreamCreated with filename:" + filename );
	}

	
	
	@Override
	public int read() {
		return 0;
	}



}
