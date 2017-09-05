package com.inconso.rf.android.device;

import java.io.IOException;

import com.inconso.rf.android.actions.Actions;
import com.inconso.rf.android.exceptions.UnauthorizedException;
import com.inconso.rf.android.update.IP;
import com.inconso.rf.android.update.UpdateTable;

public class Device {

	// Device IP
	private IP ip;
	
	// Device ID
	private String id;
	
	// Connection port
	private String port;

	
	public Device(String ip, String port)
	{
		this.ip 	= new IP(ip);
		this.setPort(port);
	}

	// Public properties

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
		this.setId(this.getIp() + ":" + port);
	}

	@Override
	public String toString() {
		return "Device{" + "ip=" + ip + ", id=" + id + ", port=" + port + '}';
	}

	/**
	 * Performs a remote connection to the device through its IP.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void connect() throws IOException, InterruptedException 
	{
		Actions.perform(UpdateTable.COMMANDS.get("connect") + this.getIp());
	}
	
	/**
	 * Performs a remote installation to the device using an apk path.
	 * @param apk_path String
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void install(String apk_path) throws InterruptedException, IOException
	{
		Actions.perform(UpdateTable.COMMANDS.get("base") + "-s " + this.getId() + " install " + apk_path);
	}
	
	/**
	 * Performs a system restoration to the device using a backup path.
	 * @param bk_path String
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws com.inconso.rf.android.exceptions.UnauthorizedException 
	 */
	public void restore(String bk_path) throws IOException, InterruptedException, UnauthorizedException
	{
		Actions.perform(UpdateTable.COMMANDS.get("base") + "-s " + this.getId() + " restore " + bk_path);
	}
}
