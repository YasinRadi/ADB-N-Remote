/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inconso.rf.android.exceptions;

/**
 *
 * @author YRadi
 */
public class UnauthorizedException extends Exception {
	
	public final String MSG = "Device unauthorized. Please check the confirmation dialog on your device.";
	
	public UnauthorizedException() {
		
	}
}
