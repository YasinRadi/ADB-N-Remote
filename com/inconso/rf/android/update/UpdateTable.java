package com.inconso.rf.android.update;

import java.util.HashMap;
import java.util.Map;

public abstract class UpdateTable {

	public static final Map<String, String> COMMANDS = new HashMap<>();
	static
	{
		// Change to tools directory
		COMMANDS.put("changeDir", "cd ");
		
		// Command root
		COMMANDS.put("base", "adb\\adb ");
		
		// Initial remote setup
		COMMANDS.put("init", "adb\\adb kill-server && adb\\adb start-server");
		
		// Execute remote connection
		COMMANDS.put("connect", "adb\\adb connect ");
		
		// Install apk
		COMMANDS.put("install", "adb\\adb install ");
		
		// Uninstall apk
		COMMANDS.put("uninstall", "adb\\adb uninstall ");
		
		// Restore system through back-up
		COMMANDS.put("restore", "adb\\adb restore ");
		
		// Disconnect 
		COMMANDS.put("disconnect", "adb\\adb kill-server");
		
		// List connected devices
		COMMANDS.put("devices", "adb\\adb devices -l");
	}
}
