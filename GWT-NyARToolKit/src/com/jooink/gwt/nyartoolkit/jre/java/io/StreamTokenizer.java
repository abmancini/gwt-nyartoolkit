package java.io;

import com.google.gwt.user.client.Window;

public class StreamTokenizer {
	public StreamTokenizer(InputStreamReader ISR)  {
		Window.alert("StreamTokenizer created");
	}


	public static final int TT_NUMBER = 42;

	public int nval = 43;

	public int nextToken() {
		return 0;
	}
}
