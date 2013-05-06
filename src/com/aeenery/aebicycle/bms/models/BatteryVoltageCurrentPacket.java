package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;


/**
 * 00A0 00A1
 * @author Jianxing
 *
 */
public class BatteryVoltageCurrentPacket extends BMSPacket{
	
	
	private byte[] voltage;
	private byte[] current;
	public BatteryVoltageCurrentPacket(BMSPacket packet){
		super(packet);
		voltage = BMSUtil.extractByteArray(body, 0, 4);
		current = BMSUtil.extractByteArray(body, 4, 4);
	}

	public int getVoltage(){
		return (int)BMSUtil.convertBytesToLong(voltage);
	}
	
	public int getCurrent(){
		return (int)BMSUtil.convertBytesToLong(current);
	}
}
