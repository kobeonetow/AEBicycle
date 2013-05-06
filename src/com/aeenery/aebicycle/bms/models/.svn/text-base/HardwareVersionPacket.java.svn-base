package com.aeenery.aebicycle.bms.models;

import com.aeenery.aebicycle.bms.BMSUtil;

public class HardwareVersionPacket extends BMSPacket{

	private int mainVersionNumber;
	private int subVersionNumber;
	private int numberOfEdit;
	private String name;
	
	public HardwareVersionPacket(BMSPacket packet){
		super(packet);
		mainVersionNumber = BMSUtil.convertByteToInt(body[0]);
		subVersionNumber = BMSUtil.convertByteToInt(body[1]);
		numberOfEdit = BMSUtil.convertByteToInt(body[2]);
		name = BMSUtil.convertByteArrayToString(body, 3);
	}
	
	@Override
	public byte[] getPacketAsByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMainVersionNumber() {
		return mainVersionNumber;
	}

	public void setMainVersionNumber(int mainVersionNumber) {
		this.mainVersionNumber = mainVersionNumber;
	}

	public int getSubVersionNumber() {
		return subVersionNumber;
	}

	public void setSubVersionNumber(int subVersionNumber) {
		this.subVersionNumber = subVersionNumber;
	}

	public int getNumberOfEdit() {
		return numberOfEdit;
	}

	public void setNumberOfEdit(int numberOfEdit) {
		this.numberOfEdit = numberOfEdit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
