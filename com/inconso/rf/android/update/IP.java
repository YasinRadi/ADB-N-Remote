/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inconso.rf.android.update;

import java.util.Arrays;

/**
 *
 * @author YRadi
 */
public class IP {

	private String[] ipFrags;
	
	public IP(String ip)
	{
		this.ipFrags = processIp(ip);
	}

	@Override
	public String toString() {
		String s = Arrays.stream(this.ipFrags)
				.reduce("", (a, b) -> a + "." + b);
		return s.substring(1, s.length());
	}
	
	/**
	 * Processes a dot seprated full ip string into an array.
	 * @param fullIp String
	 * @return String[]
	 * @throws NumberFormatException 
	 */
	private String[] processIp(String fullIp) throws NumberFormatException
	{
		String[] ip = fullIp.split("\\."), fragIp = new String[4];
		if(fullIp.charAt(fullIp.length() - 1) == '.') throw new NumberFormatException();
		for(int i = 0; i < fragIp.length; i++)
		{
			if((Integer.parseInt(ip[i]) > 255 || Integer.parseInt(ip[i]) < 1) || ip.length > 4) 
				throw new NumberFormatException();
			fragIp[i] = ip[i];
		}
		return fragIp;
	}
}
