package com.aeenery.aebicycle.bms;

import android.util.Log;

import com.aeenery.aebicycle.bms.models.BMSCommand;
import com.aeenery.aebicycle.bms.models.BMSPacket;
import com.aeenery.aebicycle.bms.models.BMSPacketHeader;
import com.aeenery.aebicycle.bms.models.BMSRequestPacket;
import com.aeenery.aebicycle.bms.models.BatteryGroupCapacitySocPacket;
import com.aeenery.aebicycle.bms.models.BatteryGroupInfoPacket;
import com.aeenery.aebicycle.bms.models.BatteryGroupTemperaturePacket;
import com.aeenery.aebicycle.bms.models.BatteryPeriodicPacket;
import com.aeenery.aebicycle.bms.models.BatteryTemperaturePacket;
import com.aeenery.aebicycle.bms.models.BatteryVoltageCurrentPacket;
import com.aeenery.aebicycle.bms.models.ConfigurationInfoPacket;
import com.aeenery.aebicycle.bms.models.DeviceSerialNumberPacket;
import com.aeenery.aebicycle.bms.models.FlowNumber;
import com.aeenery.aebicycle.bms.models.HardwareVersionPacket;
import com.aeenery.aebicycle.bms.models.SoftwareVersionPacket;

public class PacketBuilder {

	/**
	 * 
	 * @param sender sender object 来确认哪个类建的包
	 * @param BMSPacket 定意好的packet 包
	 */
	public static byte[] buildPacket(Object sender, BMSPacket packet){
//		byte[] header = packet.getHeader().getBytes();
//		byte[] body = packet.getBody().getBytes();
//		byte[] checkCode = packet.getCheckCode().getBytes();
////		byte   index = packet.getAEIndexByte();
//		int length = header.length+body.length+checkCode.length;
//		
//		byte[] c = new byte[length];
//		c[0] = index;
//		System.arraycopy(header, 0, c, 1, header.length);
//		System.arraycopy(body, 0, c, header.length+1, body.length);
//		System.arraycopy(checkCode, 0, c, body.length+header.length+1, checkCode.length);
//		c[length-1] = index;
		return null;
	}

	public BMSPacket buildRequestPacket(BMSCommand commandId) {
		BMSPacketHeader header = new BMSPacketHeader();
		header.setCommandId(commandId);
		header.setFlowNumber(new FlowNumber(0x0000));
		header.setDataSize((byte)0x00);
		header.setPacketIndex((byte)0x00);
		byte checkCode = BMSUtil.calculatePacketCheckCode(header.getHeaderAsByte());

		BMSRequestPacket packet = new BMSRequestPacket();
		packet.setHeader(header);
		packet.setBody(null);
		packet.setCheckCode(checkCode);
		return packet;
	}
	
	public BMSPacket buildReceivedPacket(BMSPacket packet){
		return buildReplyPacket(packet);
	}
	
	public BMSPacket buildReplyPacket(BMSPacket packet){
		System.out.println("Packet building with command id:"+packet.getHeader().getCommandId().getCommandAsByteInt());
		switch(packet.getHeader().getCommandId().getCommandAsByteInt()){
		case BMSUtil.COMMAND_GET_HARDWARE_VERSION_REPLY:
			return new HardwareVersionPacket(packet);
		case BMSUtil.COMMAND_GET_SOFTWARE_VERSION_REPLY:
			return new SoftwareVersionPacket(packet);
		case BMSUtil.COMMAND_GET_DEVICE_SERIAL_NUMBER_REPLY:
			return new DeviceSerialNumberPacket(packet);
		case BMSUtil.COMMAND_GET_SETTING_INFO_REPLY:
			return new ConfigurationInfoPacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_INFO_REPLY:
			return new BatteryGroupInfoPacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_CAPACITY_AND_SOC_STATUS_REPLY:
			return new BatteryGroupCapacitySocPacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_VOLTAGE_CURRENT_REPLY:
			return new BatteryVoltageCurrentPacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_TEMPERATURE_NOW_DETAIL_REPLY:
			return new BatteryGroupTemperaturePacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_LOOP_PERIOD_INFO_REPLY:
			return new BatteryPeriodicPacket(packet);
		case BMSUtil.COMMAND_GET_BATTERY_TEMPERATURE_REPLY:
			return new BatteryTemperaturePacket(packet);
		default:
			return null;
		}
	}
}