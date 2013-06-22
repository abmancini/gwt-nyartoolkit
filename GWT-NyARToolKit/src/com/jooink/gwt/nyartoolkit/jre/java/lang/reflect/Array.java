package java.lang.reflect;

import jp.nyatla.nyartoolkit.core.labeling.rlelabeling.NyARRleLabelFragmentInfo;
import jp.nyatla.nyartoolkit.markersystem.INyARSingleCameraSystemObserver;
import jp.nyatla.nyartoolkit.markersystem.utils.SquareStack;

import com.google.gwt.user.client.Window;

public class Array {
	
	public static <T> Object newInstance(Class<T> c, int n) {

		
		if( NyARRleLabelFragmentInfo.class.equals(c))
			return new NyARRleLabelFragmentInfo[n];
		else if(SquareStack.Item.class.equals(c))
			return new SquareStack.Item[n];
		else if(INyARSingleCameraSystemObserver.class.equals(c)) {
			return new INyARSingleCameraSystemObserver[n];
		} else
			Window.alert("Creating array of size " + n  + " of " + c.toString());
		return null;
	}
}
