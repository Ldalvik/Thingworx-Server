package tufts;

import android.graphics.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import java.io.*;

public class DownloadImage extends AsyncTask<String, Void, Bitmap>
 {
    ImageView imageView;

    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
