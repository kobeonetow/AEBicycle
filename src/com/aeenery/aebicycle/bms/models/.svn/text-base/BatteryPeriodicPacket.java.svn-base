package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;

/**
 * 00A8 00A9
 * @author Jianxing
 *
 */
public class BatteryPeriodicPacket  extends BMSPacket{

	private byte[] periodic;
	public BatteryPeriodicPacket(BMSPacket packet){
		super(packet);
		periodic = body;
	}
	
	public int getPeriodic(){
		return (int)BMSUtil.convertBytesToLong(periodic);
	}
}
