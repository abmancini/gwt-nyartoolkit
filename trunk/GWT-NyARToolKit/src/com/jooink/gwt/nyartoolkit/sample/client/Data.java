package com.jooink.gwt.nyartoolkit.sample.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Data extends ClientBundle {
	public static final Data INSTANCE  = GWT.create(Data.class);   
	@Source("patt.hiro")
	public TextResource patt_hiro();


	@Source("320x240ABGR.png")
	public ImageResource captured();


}