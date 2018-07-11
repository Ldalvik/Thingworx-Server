package tufts;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import fi.iki.elonen.*;
import java.lang.reflect.*;
import java.util.*;
import thingworx.*;
import thingworx.tufts.*;

import thingworx.Properties;
import java.io.*;

public class MainActivity extends AppCompatActivity {
 TextView resultTextView;
 Server s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     resultTextView = (TextView) findViewById(R.id.result);
  s = new Server();
    }
 
 private class Server extends NanoHTTPD {
        public Server(){
            super(8080);
        }

        @Override
        public Response serve(String uri, Method method, Map<String, String> header, final Map<String, String> parameters, Map<String, String> files) {
   if(parameters.get("value") != null){
    runOnUiThread(new Runnable() {
     @Override
     public void run(){
      resultTextView.setText("value=" + parameters.get("value"));
     }
    });
   }
      return newFixedLengthResponse("value=" + parameters.get("value"));
  }
 }
 
 public void start(View v) throws IOException{
  s.start();
  Toast.makeText(this, "Webserver running", Toast.LENGTH_SHORT).show();
 }
 
 public void stop(View v){
  s.stop();
  Toast.makeText(this, "Webserver stopped", Toast.LENGTH_SHORT).show();
 }
}
