package com.aeenery.aebicycle.battery;

import com.aeenery.aebicycle.R;
import com.aeenery.aebicycle.battery.BatteryDescriptionActivity.StateReceiver;
import com.aeenery.aebicycle.bms.BMSUtil;
import com.aeenery.aebicycle.bms.RequestController;
import com.aeenery.aebicycle.bms.SendPacketThread;
import com.aeenery.aebicycle.bms.models.BMSCommand;
import com.aeenery.aebicycle.entry.BicycleUtil;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BatteryVersionActivity extends Activity{

	private final static boolean D = true;
	public final static String TAG = "BatteryDescriptionActivity";
	private final int PERIOD = 5000;

	private TextView tvHardware;
	private TextView tvSoftware;
	private TextView tvDeviceSerial;
	private TextView tvBatteryInfo;
	private Button btn_version;
	
	private StateReceiver receiver = null;
	private RequestController controller = null;
	private SharedPreferences sharedPreferences = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery_version);
		
		registerReceiver();
		loadUIObjects();
	}

	private void registerReceiver() {
		receiver = new StateReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(BicycleUtil.BATTERY_STATE_UPDATE);
		this.registerReceiver(receiver, filter);
	}
	
	private void loadUIObjects() {
		tvHardware = (TextView)findViewById(R.id.tvhardware);
		tvSoftware = (TextView)findViewById(R.id.tvsoftware);
		tvDeviceSerial = (TextView)findViewById(R.id.tvdeviceserial);
		tvBatteryInfo = (TextView)findViewById(R.id.tvbatterygroupinfo);
		btn_version = (Button)findViewById(R.id.btn_version_update);
	
		controller = RequestController.getRequestController();
		sharedPreferences = this.getSharedPreferences("aebt", MODE_PRIVATE);
		
		updateAll();
	}

	public void updateAll(){
		btn_version.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.sendRequestPacket(new BMSCommand(BMSUtil.COMMAND_GET_HARDWARE_VERSION,
						BMSUtil.COMMAND_GET_HARDWARE_VERSION_REPLY), false);
				controller.sendRequestPacket(new BMSCommand(BMSUtil.COMMAND_GET_SOFTWARE_VERSION,
						BMSUtil.COMMAND_GET_SOFTWARE_VERSION_REPLY), false);
				controller.sendRequestPacket(new BMSCommand(BMSUtil.COMMAND_GET_DEVICE_SERIAL_NUMBER,
						BMSUtil.COMMAND_GET_DEVICE_SERIAL_NUMBER_REPLY), false);
				controller.sendRequestPacket(new BMSCommand(BMSUtil.COMMAND_GET_BATTERY_INFO,
						BMSUtil.COMMAND_GET_BATTERY_INFO_REPLY), false);
			}
		});
		
	}
	

	class StateReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(D) Log.i(TAG, "Action received:"+action);
			if(action.equals(BicycleUtil.BATTERY_STATE_UPDATE)){
				setHardware();
				setSoftware();
				setDeviceSerialNumber();
				setBatteryInfo();
			}
		}
    	
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	
	
	public void setBatteryInfo() {
		String capacity = sharedPreferences.getString("0030-1", "0");
		String totalvoltage = sharedPreferences.getString("0030-2", "0mv");
		String numbattery = sharedPreferences.getString("0030-3", "0个电池");
		
		tvBatteryInfo.setText("电池容量:"+capacity + "mA." + "总电压:"+totalvoltage+"mV."+"电池:"+numbattery+"节.");
	}

	public void setDeviceSerialNumber() {
		String deviceserial = sharedPreferences.getString("0007-1", "N/A");
		tvDeviceSerial.setText(deviceserial);
	}

	public void setSoftware() {
		String mainversion = sharedPreferences.getString("0004-1", "N/A");
		String subversion = sharedPreferences.getString("0004-2", "N/A");
		String numEdit = sharedPreferences.getString("0004-3","N/A");
		String name = sharedPreferences.getString("0004-4", "N/A");
		
		tvSoftware.setText("版本:"+mainversion+"-"+subversion+"." + "修改:"+numEdit+"次 ." + "设备:["+name+"]");
	}

	public void setHardware() {
		String mainversion = sharedPreferences.getString("0003-1", "N/A");
		String subversion = sharedPreferences.getString("0003-2", "N/A");
		String numEdit = sharedPreferences.getString("0003-3","N/A");
		String name = sharedPreferences.getString("0003-4", "N/A");
		
		tvHardware.setText("版本:"+mainversion+"-"+subversion+"." + "修改:"+numEdit+"次 ." + "设备:["+name+"]");
	}

	@Override
	public void onStop(){
		super.onStop();
		 if(D) Log.e(TAG, "--- ON STOP ---");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		 if(D) Log.e(TAG, "--- ON RESUME ---");
	}
	
	@Override
    public void onDestroy() {
    	if(receiver != null){
    		this.unregisterReceiver(receiver);
    	}
        super.onDestroy();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
    }
}
