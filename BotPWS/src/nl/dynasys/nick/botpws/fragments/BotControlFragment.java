package nl.dynasys.nick.botpws.fragments;

import nl.dynasys.nick.botpws.R;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BotControlFragment extends Fragment implements SensorEventListener, OnClickListener {

	// Sensor Manager
    private SensorManager deviceSensorManager;  

    // Sensor To Access
    private Sensor mSensor;
	
    // Phone Angle
    private float deviceAngle = 0f;
    
    // Angle Correction Indicator
    private float angleCorrection = 0f;
    
    // Control Views
    private TextView deviceAngleView;
    private Button zeroAngleButton;
    
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewParent, Bundle savedInstanceState){
		
		// Create View
		View fragmentView = layoutInflater.inflate(R.layout.fragment_mode_control, viewParent, false);

		// Hook Into Sensors
		deviceSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);  
		mSensor = deviceSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		deviceSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME); 

		// Initialize Views
		this.deviceAngleView = (TextView) fragmentView.findViewById(R.id.textView1);
		this.zeroAngleButton = (Button) fragmentView.findViewById(R.id.BOTCONTROL_BUTTON_ZERO);
		
		this.zeroAngleButton.setOnClickListener(this);
		
		// Return View
		return fragmentView;
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		
		// Get Device Angle
		float[] sensorData = event.values;
		
		// Save Device Angle
		BotControlFragment.this.deviceAngle = sensorData[0] - this.angleCorrection;
		          
		// Display Current Angle
		this.deviceAngleView.setText(Float.toString(BotControlFragment.this.deviceAngle));
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		// Do Nothing
		
	}

	@Override
	public void onClick(View clickedView) {

		switch(clickedView.getId()){
		
			case R.id.BOTCONTROL_BUTTON_ZERO :
				
				this.angleCorrection = this.deviceAngle;
				Toast.makeText(getActivity(), "Geijkt op : " + Float.toString(this.angleCorrection), Toast.LENGTH_LONG).show();
				
			break;
		
		}
		
	}

}
