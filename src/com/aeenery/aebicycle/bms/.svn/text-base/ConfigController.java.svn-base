package com.aeenery.aebicycle.bms;

import com.aeenery.aebicycle.entry.BicycleUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ConfigController extends BMSController{

	private static ConfigController controller;
	
	public ConfigController(Context _context) {
		super(_context);
	}

	public static ConfigController getController(Context _context){
		if(_context == null)
			return null;
		if(controller == null){
			controller = new ConfigController(_context);
		}
		return controller;
	}

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case BicycleUtil.MESSAGE_STATE_CHANGE:
				break;
			}
		}
	};
}
