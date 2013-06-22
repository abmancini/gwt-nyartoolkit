package com.jooink.gwt.nyartoolkit.client;

import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.core.pixeldriver.INyARRgbPixelDriver;
import jp.nyatla.nyartoolkit.core.raster.rgb.INyARRgbRaster;
import jp.nyatla.nyartoolkit.core.rasterdriver.INyARPerspectiveCopy;
import jp.nyatla.nyartoolkit.core.rasterfilter.rgb2gs.INyARRgb2GsFilter;
import jp.nyatla.nyartoolkit.core.types.NyARIntSize;

import com.google.gwt.canvas.dom.client.ImageData;

public class ImageDataRaster implements INyARRgbRaster, INyARRgbPixelDriver {

	private ImageData id;

	public ImageDataRaster(ImageData id) {
		this.id = id;
	}

	@Override
	public int getWidth() {
		return id.getWidth();
	}

	@Override
	public int getHeight() {
		return id.getHeight();
	}

	@Override
	public NyARIntSize getSize() {
		return new NyARIntSize(getWidth(), getHeight());
	}

	@Override
	public Object getBuffer() {
		//Window.alert("getBuffer Called");
		throw new NotImplementedException();
		//return null;
	}

	@Override
	public int getBufferType() {
		//Window.alert("getBufferType Called");
		throw new NotImplementedException();
		//return NyARBufferType.BYTE1D_X8R8G8B8_32;
	}

	@Override
	public boolean isEqualBufferType(int i_type_value) {
		//Window.alert("isEqualBufferType Called");
		throw new NotImplementedException();
		//return false;
	}

	@Override
	public boolean hasBuffer() {
		//Window.alert("hasBuffer Called");
		throw new NotImplementedException();
		//return false;
	}

	@Override
	public void wrapBuffer(Object i_ref_buf) throws NyARException {
		throw new NotImplementedException();
		//Window.alert("wrapBuffer Called");
	}



	@Override
	public Object createInterface(Class<?> iIid) throws NyARException
	{
		if(iIid==INyARPerspectiveCopy.class){
			return new ImageDataINyARPerspectiveCopy(id);
			//return NyARPerspectiveCopyFactory.createDriver(this);
		}

		if(iIid==INyARRgb2GsFilter.class){
			return new ImageDataINyARRgb2GsFilterRgbAve(id);
			//return NyARRgb2GsFilterFactory.createRgbAveDriver(this);
		}

		//Window.alert("CreateInterface for Class: " + iIid.getName());
		throw new NotImplementedException();
		//
		//		if(iIid==NyARMatchPattDeviationColorData.IRasterDriver.class){
		//			return NyARMatchPattDeviationColorData.RasterDriverFactory.createDriver(this);
		//		}
		//
		//		if(iIid==INyARRgb2GsFilterRgbAve.class){
		//			return NyARRgb2GsFilterFactory.createRgbAveDriver(this);
		//		}else if(iIid==INyARRgb2GsFilterRgbCube.class){
		//			return NyARRgb2GsFilterFactory.createRgbCubeDriver(this);
		//		}else if(iIid==INyARRgb2GsFilterYCbCr.class){
		//			return NyARRgb2GsFilterFactory.createYCbCrDriver(this);
		//		}
		//		if(iIid==INyARRgb2GsFilterArtkTh.class){
		//			return NyARRgb2GsFilterArtkThFactory.createDriver(this);
		//		}
		//		throw new NyARException();
	}

	@Override
	public INyARRgbPixelDriver getRgbPixelDriver() throws NyARException {
		return this;
	}

	@Override
	public void getPixel(int i_x, int i_y, int[] i_rgb) throws NyARException {
		i_rgb[0] = id.getRedAt(i_x, i_y);
		i_rgb[1] = id.getGreenAt(i_x, i_y);
		i_rgb[2] = id.getBlueAt(i_x, i_y);
	}

	@Override
	public void getPixelSet(int[] i_x, int[] i_y, int i_num, int[] i_intrgb) throws NyARException {
		throw new NotImplementedException();
		//Window.alert("getPixelSet");
	}

	@Override
	public void setPixel(int i_x, int i_y, int i_r, int i_g, int i_b) throws NyARException {
		throw new NotImplementedException();
		//		Window.alert("setPixel");
	}

	@Override
	public void setPixel(int i_x, int i_y, int[] i_rgb) throws NyARException {
		throw new NotImplementedException();
		//Window.alert("setPixel 2");
	}

	@Override
	public void setPixels(int[] i_x, int[] i_y, int i_num, int[] i_intrgb) throws NyARException {
		throw new NotImplementedException();
		//Window.alert("setPixelS");
	}

	@Override
	public void switchRaster(INyARRgbRaster i_raster) throws NyARException {
		throw new NotImplementedException();
		//Window.alert("switchRaster");
	}



}


