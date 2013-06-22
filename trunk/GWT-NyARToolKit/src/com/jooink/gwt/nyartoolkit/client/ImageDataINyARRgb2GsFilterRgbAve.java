package com.jooink.gwt.nyartoolkit.client;

import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.core.pixeldriver.INyARGsPixelDriver;
import jp.nyatla.nyartoolkit.core.raster.INyARGrayscaleRaster;
import jp.nyatla.nyartoolkit.core.rasterfilter.rgb2gs.INyARRgb2GsFilterRgbAve;
import jp.nyatla.nyartoolkit.core.types.NyARBufferType;
import jp.nyatla.nyartoolkit.core.types.NyARIntSize;

import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.ImageData;

public class ImageDataINyARRgb2GsFilterRgbAve implements INyARRgb2GsFilterRgbAve {
	private ImageData id;
	public ImageDataINyARRgb2GsFilterRgbAve(ImageData tid) {
		this.id = tid;
	}

	@Override
	public void convert(INyARGrayscaleRaster i_raster) throws NyARException {			
		this.convertRect(0,0,id.getWidth(),id.getHeight(),i_raster);
	}


	@Override
	public void convertRect(int l,int t,int w,int h,INyARGrayscaleRaster o_raster) throws NyARException {
		
		NyARIntSize size=new NyARIntSize(id.getWidth(),id.getHeight());
		int bp = (l+t*size.w)*4;
		final int b=t+h;
		final int row_padding_dst=(size.w-w);
		final int row_padding_src=row_padding_dst*4;
		final int pix_count=w;
		final int pix_mod_part=pix_count-(pix_count%8);
		int dst_ptr=t*size.w+l;
		//byte[] in_buf = (byte[]) this._ref_raster.getBuffer();
		CanvasPixelArray in_buf = id.getData();
		switch(o_raster.getBufferType()){
		case NyARBufferType.INT1D_GRAY_8:
			int[] out_buf=(int[])o_raster.getBuffer();
			for (int y = t; y < b; y++) {

				int x=0;
				for (x = pix_count-1; x >=pix_mod_part; x--){
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
				}
				for (;x>=0;x-=8){
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
					out_buf[dst_ptr++] = ((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3;					
					bp+=4;
				}
				bp+=row_padding_src;
				dst_ptr+=row_padding_dst;
			}
			return;
		default:
			INyARGsPixelDriver out_drv=o_raster.getGsPixelDriver();
			for (int y = t; y < b; y++) {
				for (int x = 0; x<pix_count; x++){
					out_drv.setPixel(x,y,((in_buf.get(bp) & 0xff) + (in_buf.get(bp+1) & 0xff) + (in_buf.get(bp+2) & 0xff)) /3);
					bp+=4;
				}
				bp+=row_padding_src;
			}
			return;
		}
	}

}