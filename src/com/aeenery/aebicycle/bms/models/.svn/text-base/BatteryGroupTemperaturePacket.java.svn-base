package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;

/**
 * 00A2 00A3
 * @author Jianxing
 *
 */
public class BatteryGroupTemperaturePacket extends BMSPacket{
	
	private byte[] groupIndex;
	private byte groupMemberIndex;
	private byte requestNumber;
	private byte status;
	private byte[] tempGroup;
	
	public BatteryGroupTemperaturePacket(BMSPacket packet, int gIndex, int gMemberIndex, int request){
		super(packet);
		requestNumber = (byte)request;
		groupIndex = new byte[]{
				(byte)(gIndex >>> 8),
				(byte)(gIndex & 0xff)
		};
		groupMemberIndex = (byte)gMemberIndex;
		body = new byte[]{groupIndex[0],groupIndex[1],groupMemberIndex,requestNumber};
		this.getHeader().setDataSize(((byte)this.body.length));
	}
	public  BatteryGroupTemperaturePacket(BMSPacket packet){
		super(packet);
		status = body[0];
		groupIndex = new byte[]{body[1],body[2]};
		groupMemberIndex = body[3];
		requestNumber = body[4];
		tempGroup = BMSUtil.extractByteArray(body, 5, body.length-5);
		
	}

	public byte getStatus(){
		return status;
	}
	
	public int getGoupIndex(){
		return (int)BMSUtil.convertTwoBytesToInt2(groupIndex[0], groupIndex[1]);
	}
	
	public int getGroupMemberIndex(){
		return (int)(groupMemberIndex & 0xff);
	}
	
	public int getRequestNumber(){
		return (int)(requestNumber & 0xff);
	}
	
	/**
	 * Index from 0
	 * @param position
	 * @return
	 */
	public float getTemperature(int position){
		if(position < getRequestNumber()){
			int pos = 2*position;
			byte[] array  = new byte[]{tempGroup[pos],tempGroup[pos+1]};
			return (float)(BMSUtil.calculateHalfByteBCDFromByteArrayAtBaseTen(array))*BMSUtil.getSign(array[0]);
		}else
			return 0;
	}
}
