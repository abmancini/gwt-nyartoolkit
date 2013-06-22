package java.nio;

import com.google.gwt.user.client.Window;

public  class ByteBuffer {

	public static ByteBuffer wrap(byte[] bs) {
		Window.alert("ByteBuffer created");

		return new ByteBuffer();
	}
	
	public ByteBuffer order(ByteOrder b) {
		return this;
	}
	
	public  int getInt() {
		return 0;
	}
	public  Double getDouble() {
		return 0.0;
	}
	
	public ByteBuffer putInt(int val) {
		return new ByteBuffer();
	}
}
