package com.jooink.gwt.nyartoolkit.client;

import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.core.pixeldriver.INyARRgbPixelDriver;
import jp.nyatla.nyartoolkit.core.raster.INyARRaster;
import jp.nyatla.nyartoolkit.core.raster.rgb.INyARRgbRaster;
import jp.nyatla.nyartoolkit.core.rasterdriver.NyARPerspectiveCopy_Base;
import jp.nyatla.nyartoolkit.core.types.NyARBufferType;

import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.ImageData;

public class  ImageDataINyARPerspectiveCopy extends NyARPerspectiveCopy_Base {
	
	private ImageData id;
	public ImageDataINyARPerspectiveCopy(ImageData tid) {
		this.id = tid;
	}

	protected boolean onePixel(int pk_l,int pk_t,double[] cpara,INyARRaster o_out)throws NyARException
	{		

		int in_w=id.getWidth();
		int in_h=id.getHeight();
		//byte[] i_in_buf=(byte[])this._ref_raster.getBuffer();
		 CanvasPixelArray i_in_buf = id.getData();

		//ピクセルリーダーを取得
		double cp0=cpara[0];
		double cp3=cpara[3];
		double cp6=cpara[6];
		double cp1=cpara[1];
		double cp4=cpara[4];
		double cp7=cpara[7];
		
		int out_w=o_out.getWidth();
		int out_h=o_out.getHeight();
		double cp7_cy_1  =cp7*pk_t+1.0+cp6*pk_l;
		double cp1_cy_cp2=cp1*pk_t+cpara[2]+cp0*pk_l;
		double cp4_cy_cp5=cp4*pk_t+cpara[5]+cp3*pk_l;
		int r,g,b,p;
		switch(o_out.getBufferType())
		{
		case NyARBufferType.INT1D_X8R8G8B8_32:
			int[] pat_data=(int[])o_out.getBuffer();
			p=0;
			for(int iy=0;iy<out_h;iy++){
				//解像度分の点を取る。
				double cp7_cy_1_cp6_cx  =cp7_cy_1;
				double cp1_cy_cp2_cp0_cx=cp1_cy_cp2;
				double cp4_cy_cp5_cp3_cx=cp4_cy_cp5;
				
				for(int ix=0;ix<out_w;ix++){
					//1ピクセルを作成
					final double d=1/(cp7_cy_1_cp6_cx);
					int x=(int)((cp1_cy_cp2_cp0_cx)*d);
					int y=(int)((cp4_cy_cp5_cp3_cx)*d);
					if(x<0){x=0;}else if(x>=in_w){x=in_w-1;}
					if(y<0){y=0;}else if(y>=in_h){y=in_h-1;}
							
					final int bp = (x + y * in_w) * 4;
					r=(i_in_buf.get(bp + 2) & 0xff);
					g=(i_in_buf.get(bp + 1) & 0xff);
					b=(i_in_buf.get(bp + 0) & 0xff);
					cp7_cy_1_cp6_cx+=cp6;
					cp1_cy_cp2_cp0_cx+=cp0;
					cp4_cy_cp5_cp3_cx+=cp3;
					pat_data[p]=(r<<16)|(g<<8)|((b&0xff));
					p++;
				}
				cp7_cy_1+=cp7;
				cp1_cy_cp2+=cp1;
				cp4_cy_cp5+=cp4;
			}
			return true;
		default:
			//ANY to RGBx
			if(o_out instanceof INyARRgbRaster){
				INyARRgbPixelDriver out_reader=((INyARRgbRaster)o_out).getRgbPixelDriver();
				for(int iy=0;iy<out_h;iy++){
					//解像度分の点を取る。
					double cp7_cy_1_cp6_cx  =cp7_cy_1;
					double cp1_cy_cp2_cp0_cx=cp1_cy_cp2;
					double cp4_cy_cp5_cp3_cx=cp4_cy_cp5;
					
					for(int ix=0;ix<out_w;ix++){
						//1ピクセルを作成
						final double d=1/(cp7_cy_1_cp6_cx);
						int x=(int)((cp1_cy_cp2_cp0_cx)*d);
						int y=(int)((cp4_cy_cp5_cp3_cx)*d);
						if(x<0){x=0;}else if(x>=in_w){x=in_w-1;}
						if(y<0){y=0;}else if(y>=in_h){y=in_h-1;}
								
						final int bp = (x + y * in_w) * 4;
						r=(i_in_buf.get(bp + 2) & 0xff);
						g=(i_in_buf.get(bp + 1) & 0xff);
						b=(i_in_buf.get(bp + 0) & 0xff);
						cp7_cy_1_cp6_cx+=cp6;
						cp1_cy_cp2_cp0_cx+=cp0;
						cp4_cy_cp5_cp3_cx+=cp3;
						out_reader.setPixel(ix,iy,r,g,b);
					}
					cp7_cy_1+=cp7;
					cp1_cy_cp2+=cp1;
					cp4_cy_cp5+=cp4;
				}
				return true;
			}
			break;
		}
		return false;
	}
	protected boolean multiPixel(int pk_l,int pk_t,double[] cpara,int i_resolution,INyARRaster o_out)throws NyARException
	{
		
		int in_w=id.getWidth();
		int in_h=id.getHeight();
		//byte[] i_in_buf=(byte[])this._ref_raster.getBuffer();
		 CanvasPixelArray i_in_buf = id.getData();
		final int res_pix=i_resolution*i_resolution;

		//ピクセルリーダーを取得
		double cp0=cpara[0];
		double cp3=cpara[3];
		double cp6=cpara[6];
		double cp1=cpara[1];
		double cp4=cpara[4];
		double cp7=cpara[7];
		double cp2=cpara[2];
		double cp5=cpara[5];
		
		int out_w=o_out.getWidth();
		int out_h=o_out.getHeight();
		if(o_out instanceof INyARRgbRaster){
			INyARRgbPixelDriver out_reader=((INyARRgbRaster)o_out).getRgbPixelDriver();
			for(int iy=out_h-1;iy>=0;iy--){
				//解像度分の点を取る。
				for(int ix=out_w-1;ix>=0;ix--){
					int r,g,b;
					r=g=b=0;
					int cy=pk_t+iy*i_resolution;
					int cx=pk_l+ix*i_resolution;
					double cp7_cy_1_cp6_cx_b  =cp7*cy+1.0+cp6*cx;
					double cp1_cy_cp2_cp0_cx_b=cp1*cy+cp2+cp0*cx;
					double cp4_cy_cp5_cp3_cx_b=cp4*cy+cp5+cp3*cx;
					for(int i2y=i_resolution-1;i2y>=0;i2y--){
						double cp7_cy_1_cp6_cx  =cp7_cy_1_cp6_cx_b;
						double cp1_cy_cp2_cp0_cx=cp1_cy_cp2_cp0_cx_b;
						double cp4_cy_cp5_cp3_cx=cp4_cy_cp5_cp3_cx_b;
						for(int i2x=i_resolution-1;i2x>=0;i2x--){
							//1ピクセルを作成
							final double d=1/(cp7_cy_1_cp6_cx);
							int x=(int)((cp1_cy_cp2_cp0_cx)*d);
							int y=(int)((cp4_cy_cp5_cp3_cx)*d);
							if(x<0){x=0;}else if(x>=in_w){x=in_w-1;}
							if(y<0){y=0;}else if(y>=in_h){y=in_h-1;}
							
							final int bp = (x + y * in_w) * 4;
							r+=(i_in_buf.get(bp + 2) & 0xff);
							g+=(i_in_buf.get(bp + 1) & 0xff);
							b+=(i_in_buf.get(bp + 0) & 0xff);
							cp7_cy_1_cp6_cx+=cp6;
							cp1_cy_cp2_cp0_cx+=cp0;
							cp4_cy_cp5_cp3_cx+=cp3;
						}
						cp7_cy_1_cp6_cx_b+=cp7;
						cp1_cy_cp2_cp0_cx_b+=cp1;
						cp4_cy_cp5_cp3_cx_b+=cp4;
					}
					out_reader.setPixel(ix,iy,r/res_pix,g/res_pix,b/res_pix);
				}
			}
			return true;
		}
		return false;
	}	
}