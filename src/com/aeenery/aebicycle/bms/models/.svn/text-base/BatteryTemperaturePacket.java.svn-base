package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;


/**
 * 0080 0081
 * @author Jianxing
 *
 */
public class BatteryTemperaturePacket extends BMSPacket{
	
	public byte[] highest;
	public byte[] lowest;
	
	public BatteryTemperaturePacket(BMSPacket packet){
		super(packet);
		highest = new byte[]{body[0],body[1]};
		lowest = new byte[]{body[2],body[3]};
	}
	
	public int getHighest(){
		return (int)(BMSUtil.calculateHalfByteBCDFromByteArrayAtBaseTen(highest) * getSign(highest[0]));
	}
	
	public int getLowest(){
		return (int)(BMSUtil.calculatePacketCheckCode(lowest)* getSign(lowest[0]));
	}
	
	public int getSign(byte b){
		int sign = (int)((b >> 7) & 1);
		if(sign == 1)
			return 1;
		else
			return -1;
	}

}
