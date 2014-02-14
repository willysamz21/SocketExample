package io.socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class CustomCallback implements IOCallback{

	private TextView phText;
	private TextView humidityText;
	public TextView getPhText() {
		return phText;
	}

	public void setPhText(TextView phText) {
		this.phText = phText;
	}

	public TextView getHumidityText() {
		return humidityText;
	}

	public void setHumidityText(TextView humidityText) {
		this.humidityText = humidityText;
	}

	public TextView getWaterTempText() {
		return waterTempText;
	}

	public void setWaterTempText(TextView waterTempText) {
		this.waterTempText = waterTempText;
	}

	public TextView getAirTempText() {
		return airTempText;
	}

	public void setAirTempText(TextView airTempText) {
		this.airTempText = airTempText;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	private TextView waterTempText;
	private TextView airTempText;
	private Handler handler;
	private String s;
	private String air;
	private String water;
	private String humidity;
	private String ph;
	@Override
    public void onMessage(JSONObject json, IOAcknowledge ack) {
        try {
            Log.v("OK","Server said:" + json.toString(2));
            //json.get("name");
            Log.v("MSG",json.get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(String data, IOAcknowledge ack) {
        Log.v("OK","Server said: " + data);
    }

    @Override
    public void onError(SocketIOException socketIOException) {
        Log.v("OK","an Error occured");
        socketIOException.printStackTrace();
    }

    @Override
    public void onDisconnect() {
        Log.v("OK","Connection terminated.");
    }

    @Override
    public void onConnect() {
        Log.v("OK","Connection established");
    }
    @Override
    public void on(String event, IOAcknowledge ack, Object... args) {
    	Log.v("OK","Server triggered event '" + event + "'");
        JSONObject hm;
        if(!event.equals("wizard:testSensors")){
		try {
			hm = (JSONObject)args[0];
			 s = hm.getString("data");
			Log.v("DATA",s);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
        if(event.equals("wizard:current:phSensor")){
    		ph = new String(s);
        }
        else if(event.equals("wizard:current:tempSensor")){
			air = new String(s);
        }
        else if(event.equals("wizard:current:humiditySensor")){
			humidity = new String(s);
        }
        else if(event.equals("wizard:current:waterTempSensor")){
			water = new String(s);
        }
        handler.post(new Runnable(){
			@Override
			public void run() {
				airTempText.setText(air+"deg");
				waterTempText.setText(water+"deg");
				humidityText.setText(humidity+"%");
				phText.setText(ph);
				
			}
		});
        }
    }

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
