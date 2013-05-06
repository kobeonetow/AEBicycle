package com.aeenery.aebicycle.bms.models;

public class BMSCommand {

	private byte[] command = new byte[2];
	private int responseID = 0xFFFA;

	/**
	 * Must be a two byte integer
	 * will take value of 4 hex value 0xYYYY
	 * @param command
	 */
	public BMSCommand(int command, int responseId){
		byte tail = (byte)(command & 0xFF);
		byte head = (byte)((command >> 8 ) & 0xFF);
		this.command[0] = head;
		this.command[1] = tail;
		this.setResponseID(responseId);
	}
	
	public BMSCommand(byte head, byte tail, int responseId){
		this.command[0] = head;
		this.command[1] = tail;
		this.setResponseID(responseId);
	}
	
	public byte[] getCommand() {
		return command;
	}

	public void setCommand(byte[] command) {
		this.command = command;
	}
	
	public int getCommandAsByteInt(){
		long result = ((command[0] << 8) + (command[1] & 0xff)) & 0xffffffff;
		Integer intresult = (int)result;
		return intresult;
	}

	public int getResponseID() {
		return responseID;
	}

	public void setResponseID(int responseID) {
		this.responseID = responseID;
	}
	
}
