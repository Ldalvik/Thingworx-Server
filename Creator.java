package tufts;
import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;

public class Creator
{
	TextView[] textviews = new TextView[100];
	ImageView[] images = new ImageView[100];
	RelativeLayout canvas;
	Context ctx;
	
	public Creator(RelativeLayout canvas, Context ctx){
		this.canvas = canvas;
		this.ctx = ctx;
	}
	
	public TextView createTextView(String text, Float x, Float y, Float size, int[] rgb){
		TextView tv = new TextView(ctx);
		tv.setText(text);
		tv.setX(x);
		tv.setY(y);
		tv.setTextSize(size);
		tv.setTextColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
		return tv;
	}

	public void deleteText(int id){
		textviews[id].setVisibility(View.GONE);
	}

	public void updateText(String text, Float x, Float y, int id, Float size, int[] rgb){
		TextView tv = textviews[id];
		tv.setText(text);
		tv.setTextColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
		tv.setTextSize(size);
		tv.setX(x);
		tv.setY(y);
	}

	public void addText(String text, Float x, Float y, int id, Float size, int[] rgb){
		TextView tv = createTextView(text, x, y, size, rgb);
		textviews[id] = tv;
		canvas.addView(tv);
	}

	public ImageView createImageView(String url, Float x, Float y){
		ImageView i = new ImageView(ctx);
		i.setX(x);
		i.setY(y);
		new DownloadImage(i).execute(url);
		return i;
	}

	public void addImage(String url, Float x, Float y, int id){
		ImageView image = createImageView(url, x, y);
		canvas.addView(image);
		images[id] = image;
	}

	public void deleteImage(int id){
		images[id].setVisibility(View.GONE);
	}

	public void updateImage(String url, Float x, Float y, int id){
		ImageView i = images[id];
		new DownloadImage(i).execute(url);
		i.setX(x);
		i.setY(y);
	}
}
