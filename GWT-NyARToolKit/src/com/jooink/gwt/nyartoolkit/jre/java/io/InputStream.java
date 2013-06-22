package java.io;

import com.google.gwt.user.client.Window;

public abstract  class InputStream {

	public InputStream() {
		Window.alert("Ola, InputStreamCreated");

	}



	public int 	available() {
		return 0;
	}
	public void	 	close() {

	}
	public void 	mark(int readlimit) {

	}
	public boolean 	markSupported() {
		return false;
	}
	public int 	read(byte[] b) {
		return 0;
	}
	public int 	read(byte[] b, int off, int len) {
		return 0;
	}
	public void 	reset() {

	}
	public long 	skip(long n) {
		return 0;
	}
	
	
	public abstract  int 	read();



}
