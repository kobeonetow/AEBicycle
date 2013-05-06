package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;


/**
 * 00A6 00A7
 * @author Jianxing
 *
 */
public class BatteryGroupCapacitySocPacket  extends BMSPacket{

	private byte absoluteSOC; //HEX
	private byte relativeSOC; //HEX
	private byte[] capacityleft;
	private byte[] totalcapacity;
	public BatteryGroupCapacitySocPacket(BMSPacket packet){
		super(packet);
		absoluteSOC = body[0];
		relativeSOC = body[1];
		capacityleft = BMSUtil.extractByteArray(body, 2, 5);
		totalcapacity = BMSUtil.extractByteArray(body, 7, 5);
	}
	
	public int getAbsoluteSOC(){
		return (int)(absoluteSOC & 0xFF);
	}
	
	public int getRelativeSOC(){
		return (int)(relativeSOC & 0xFF);
	}
	
	public long getCapacityleft(){
		return BMSUtil.convertBytesToLong(capacityleft);
	}
	
	public long getTotalcapacity(){
		return BMSUtil.convertBytesToLong(totalcapacity);
	}
	
}
