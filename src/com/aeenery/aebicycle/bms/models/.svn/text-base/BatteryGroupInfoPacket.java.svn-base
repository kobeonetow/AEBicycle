package com.aeenery.aebicycle.bms.models;

import android.util.Log;

import com.aeenery.aebicycle.bms.BMSUtil;

public class BatteryGroupInfoPacket extends BMSPacket {

	private byte[] batteryGroupStandardCapacity;
	private byte[] batteryGroupStandardVoltage;
	private int batteryGroupBatteryNumber;
	public BatteryGroupInfoPacket(BMSPacket packet){
		super(packet);
		setBatteryGroupStandardCapacity(BMSUtil.extractByteArray(body, 0, 5));
		setBatteryGroupStandardVoltage(BMSUtil.extractByteArray(body, 5, 5));
		setBatteryGroupBatteryNumber(BMSUtil.convertTwoBytesToInt2(body[10], body[11]));
		Log.i("asdasd",BMSUtil.convertByteToInt(body[10])+"");
		Log.i("asdasd",BMSUtil.convertByteToInt(body[11])+"");
		Log.i("asdasd",BMSUtil.convertTwoBytesToInt2(body[10], body[11])+"");
	}
	
	/**
	 * At unit uAh  = 0.1mAh
	 * @return
	 */
	public double getBatteryGroupStandardCapacity() {
		return BMSUtil.calculateHalfByteBCDFromByteArrayAtBaseTen(batteryGroupStandardCapacity);
	}
	public void setBatteryGroupStandardCapacity(byte[] batteryGroupStandardCapacity) {
		this.batteryGroupStandardCapacity = batteryGroupStandardCapacity;
	}
	
	
	/**
	 * At Unit  100 uV = 0.1mV
	 * @return
	 */
	public double getBatteryGroupStandardVoltage() {
		return BMSUtil.calculateHalfByteBCDFromByteArrayAtBaseTen(batteryGroupStandardVoltage);
	}
	public void setBatteryGroupStandardVoltage(byte[] batteryGroupStandardVoltage) {
		this.batteryGroupStandardVoltage = batteryGroupStandardVoltage;
	}
	public int getBatteryGroupBatteryNumber() {
		return batteryGroupBatteryNumber;
	}
	public void setBatteryGroupBatteryNumber(int batteryGroupBatteryNumber) {
		this.batteryGroupBatteryNumber = batteryGroupBatteryNumber;
	}
	
	
}
