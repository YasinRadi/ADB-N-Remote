package com.inconso.rf.android.update;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.inconso.rf.android.actions.Actions;
import com.inconso.rf.android.device.Device;
import com.inconso.rf.android.exceptions.UnauthorizedException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Yasin Radi
 *
 */
public class Updater {

	// IP Table
	private Map<String, Device> ipTable = new HashMap<String, Device>();
	
	// Connection port
	private String port;
	
	public Updater()
	{
		this.port = "5555";
	}
	
	public Updater(String port)
	{
		this.port = port;
	}

	// Public properties
	public Map<String, Device> getIpTable() {
		return ipTable;
	}

	public void setIpTable(Map<String, Device> ipTable) {
		this.ipTable = ipTable;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	/**
	 * Sets up the system to connect remotely, through a certain port, to one or more devices.
	 * By default port 5555 is used.
	 * @return Updater
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Updater init() throws IOException, InterruptedException
	{
		Actions.perform(UpdateTable.COMMANDS.get("init"));
		return this;
	}
	
	/**
	 * Adds a new IP to the IP table.
	 * @param ip String
	 * @return Updater
	 */
	public Updater addDeviceIp(String ip)
	{
		this.getIpTable().put(ip, new Device(ip, this.getPort()));
		return this;
	}
	
	/**
	 * Disconnects all devices from host.
	 * @param destroyProcess boolean
	 * @return Updater
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Updater disconnect() throws IOException, InterruptedException
	{
		Actions.perform(UpdateTable.COMMANDS.get("disconnect"));
		return this;
	}
	
	/**
	 * Lists all connected devices.
	 * @param ipTable Map<String, Device>
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	private static void listDevices(Map<String, Device> ipTable) throws IOException, InterruptedException
	{
		Actions.perform(UpdateTable.COMMANDS.get("devices"));
	}
	
	/**
	 * Performs a connection to all listed devices.
	 * @return Updater
	 */
	public Updater connect() throws IOException, InterruptedException
	{
		for(Device d : this.getIpTable().values()) {
			d.connect();
		}
		
		return this;
	}
	
	/**
	 * 
	 * @param apk_path
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws com.inconso.rf.android.exceptions.UnauthorizedException 
	 */
	public Updater restore(String apk_path) throws InterruptedException, IOException, UnauthorizedException
	{
		for(Device d : this.getIpTable().values())
		{
			d.restore(apk_path);
		}
		
		return this;
	}
}
