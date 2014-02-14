package com.example.socket2test;

import io.socket.CustomCallback;
import io.socket.SocketIO;

import java.net.MalformedURLException;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	SocketIO socket;
	Handler handler = new Handler();
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t1 = (TextView)findViewById(R.id.TV1);
        TextView t2 = (TextView)findViewById(R.id.T2);
        TextView t3 = (TextView)findViewById(R.id.T3);
        TextView t4 = (TextView)findViewById(R.id.T4);
        
        
        
        try {
	//		socket = new SocketIO("http://10.0.2.2:3000");
    //    	socket = new SocketIO("http://192.168.0.102:3000");
        	socket = new SocketIO();
			CustomCallback c = new CustomCallback();
			c.setAirTempText(t2);
			c.setHumidityText(t4);
			c.setPhText(t1);
			c.setWaterTempText(t3);

			c.setHandler(handler);
			socket.connect("http://ec2-50-112-185-131.us-west-2.compute.amazonaws.com:3000",c);
		//	socket.emit("ready");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
				Log.v("ERRRRRRROR", e.toString());
				}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
