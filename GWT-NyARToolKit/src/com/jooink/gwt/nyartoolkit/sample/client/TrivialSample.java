package com.jooink.gwt.nyartoolkit.sample.client;

import jp.nyatla.nyartoolkit.core.NyARCode;
import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.core.raster.rgb.NyARRgbRaster;
import jp.nyatla.nyartoolkit.core.types.NyARBufferType;
import jp.nyatla.nyartoolkit.core.types.NyARIntSize;
import jp.nyatla.nyartoolkit.markersystem.NyARMarkerSystem;
import jp.nyatla.nyartoolkit.markersystem.NyARMarkerSystemConfig;
import jp.nyatla.nyartoolkit.markersystem.NyARSensor;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.jooink.gwt.nyartoolkit.client.ImageDataRaster;

public class TrivialSample implements EntryPoint, LoadHandler  {




	private  NyARMarkerSystemConfig config ;
	private  NyARMarkerSystem nyar;
	private  NyARSensor i_sensor;
	private  Context2d ctx;
	private int marker_id;
	private Image img;
	private Canvas canvas;

	@Override
	public void onModuleLoad() {
		try {

			config = new NyARMarkerSystemConfig(320,240);
			nyar=new NyARMarkerSystem(config);
			i_sensor = new NyARSensor(new NyARIntSize(320,240));

			//creare a marker
			int i_patt_resolution = 16;
			int i_patt_edge_percentage = 25;
			double i_marker_size =2;
			NyARCode arCode=new NyARCode(i_patt_resolution,i_patt_resolution);
			loadFromARToolKitFormString(Data.INSTANCE.patt_hiro().getText(),arCode);
			marker_id = nyar.addARMarker(arCode, i_patt_edge_percentage, i_marker_size);	

			//load the sample image
			SafeUri uri= Data.INSTANCE.captured().getSafeUri();  
			img = new Image(uri);


			
			
			canvas = Canvas.createIfSupported();
			if(canvas==null)
				Window.alert("Your browser does not support HTML5's <canvas/> tag :(.");



			canvas.setCoordinateSpaceWidth(320);
			canvas.setCoordinateSpaceHeight(240);
			canvas.setWidth(320 +"px");
			canvas.setHeight(240+"px");

			ctx = canvas.getContext2d();



			RootPanel.get().add(canvas);
			RootPanel.get().add(img);

			img.addLoadHandler(this);


		} catch (NyARException e) {
			e.printStackTrace();
		}



	}



	@Override
	public void onLoad(LoadEvent event) {

		try {

			ImageElement imgElement = ImageElement.as(img.getElement());
			ctx.drawImage(imgElement, 0, 0,canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());	

			ImageData capt = ctx.getImageData(0, 0, 320, 240);

			ImageDataRaster input = new ImageDataRaster(capt);
			i_sensor.update(input);
			
			nyar.update(i_sensor);

			if(nyar.isExistMarker(marker_id)) {
				RootPanel.get().add(new HTML("Marker detected with confidence " + nyar.getConfidence(marker_id)));
			} else {
				RootPanel.get().add(new HTML("Marker NOT detected"));
			}

		} catch (NyARException e) {
			e.printStackTrace();
		}

	}  


	//QnD code to load a marker from a string, strongly inspired on  nyar.addARMarker(i_stream, i_patt_resolution, i_patt_edge_percentage, i_marker_size)
	private static void loadFromARToolKitFormString(String i_string,NyARCode o_code) throws NyARException {

		int width=o_code.getWidth();
		int height=o_code.getHeight();
		NyARRgbRaster tmp_raster=new NyARRgbRaster(width,height, NyARBufferType.INT1D_X8R8G8B8_32);

		i_string = i_string.replaceAll("\\r\\n|\\r|\\n", " ");
		try {

			String[] a = i_string.split(" ");

			int POSITION=0;

			int[] buf=(int[])tmp_raster.getBuffer();
			//GBRAで一度読みだす。
			for (int h = 0; h < 4; h++){

				POSITION=readBlock(a,POSITION,width,height,buf);
				//ARCodeにセット(カラー)
				o_code.getColorData(h).setRaster(tmp_raster);
				o_code.getBlackWhiteData(h).setRaster(tmp_raster);
			}
		} catch (Exception e) {
			throw new NyARException(e);
		}

		tmp_raster=null;//ポイ
		return;
	}


	private static int readBlock(String[] a, int POSITION,int i_width,int i_height,int[] o_buf) throws NyARException
	{
		try {
			final int pixels=i_width*i_height;
			for (int i3 = 0; i3 < 3; i3++) {
				for (int i2 = 0; i2 < pixels; i2++){
					// 数値のみ読み出す
					//					switch (i_st.nextToken()){
					//					case StreamTokenizer.TT_NUMBER:
					//						break;
					//					default:
					//						throw new NyARException();
					//					}


					//Browser.getWindow().getConsole().log("reading positon: " + POSITION );
					String tok_o = a[POSITION];
					POSITION++;
					String tok = tok_o.trim();
					int tok_i = isInteger(tok);
					while(tok_i <0) {
						//Browser.getWindow().getConsole().log("Skipping: '" + tok + "'");
						//Browser.getWindow().getConsole().log("reading positon: " + POSITION );
						tok = a[POSITION];
						POSITION++;
						tok_i = isInteger(tok);
					}
					//Browser.getWindow().getConsole().log("using: " + tok + " = " + tok_i);

					o_buf[i2]=(o_buf[i2]<<8)|((0x000000ff&(int)tok_i));
					//Browser.getWindow().getConsole().log("o_buf["+i2+"]="+o_buf[i2]);
				}
			}
			//GBR→RGB
			for(int i3=0;i3<pixels;i3++){
				o_buf[i3]=((o_buf[i3]<<16)&0xff0000)|(o_buf[i3]&0x00ff00)|((o_buf[i3]>>16)&0x0000ff);
				//Browser.getWindow().getConsole().log("+++ o_buf["+i3+"]="+o_buf[i3]);

			}
		} catch (Exception e) {
			throw new NyARException(e);
		}		
		return POSITION;
	}
	//I need only positives
	private static int isInteger(String str) {
		try {
			int i= Integer.parseInt(str);
			return i;
		} catch (NumberFormatException nfe) {}
		return -1;
	}






}
