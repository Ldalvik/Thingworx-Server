package tufts;

import android.content.*;
import android.content.res.*;
import android.net.wifi.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import fi.iki.elonen.*;
import java.io.*;
import thingworx.tufts.*;

public class MainActivity extends AppCompatActivity {
	RelativeLayout canvas;
	Server s;
	String fileTxt;
	Creator c = null;
	TextView[] textviews = new TextView[100];
	ImageView[] images = new ImageView[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	    canvas = (RelativeLayout) findViewById(R.id.canvas);
		s = new Server();
		fileTxt = openFile();
		c = new Creator(canvas, MainActivity.this);
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Enable Web Server?");
		dialog.setMessage("This will deploy a WebServer at http://"+phoneIp()+":8080/");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				try {
					s.start();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		});
		
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					System.exit(0);
				}
			});
			dialog.setCancelable(false);
			dialog.show();
    }
	
	private class Server extends NanoHTTPD {
		public Server(){
            super(8080);
        }

        @Override
        public Response serve(final IHTTPSession session) {
			final Request r = new Request(session);
			if(!r.isControl()){
				//fileTxt = "Success.";
				final String text = r.getText();
				final Float x = r.getX();
				final Float y = r.getY();
				final int id = r.getId();
				final Float size = r.getSize();
				final int[] color = r.getColor();
				final String imageUrl = r.getImageUrl();
				runOnUiThread(new Runnable() {
					@Override
					public void run(){
						if(imageUrl != null){
							if(r.isAdd()){
								c.addImage(imageUrl, x, y, id);
							}
							if(r.isUpdate()){
								c.updateImage(imageUrl, x, y, id);
							}
							if(r.isDelete()){
								c.deleteImage(id);
							}
						} else {
							if(r.isAdd()){
								c.addText(text, x, y, id, size, color);
							}
							if(r.isUpdate()){
								c.updateText(text, x, y, id, size, color);
							}
							if(r.isDelete()){
								c.deleteText(id);
							}
						}
					}
				});
			} else if(r.isControl()){
				
			}
			return newFixedLengthResponse(fileTxt);
		}
	}
	
	
	public String phoneIp(){
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
		final String formattedIpAddress = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
		return formattedIpAddress;
	}
	
	public String openFile () {
		System.out.println("Starting openFile now");
		StringBuilder str = new StringBuilder();
		AssetManager am = getAssets();
		try {
			InputStream is = am.open("webserver.html");
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				str.append(inputLine);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
}

