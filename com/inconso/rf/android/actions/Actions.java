package com.inconso.rf.android.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Actions {
	
	/**
	 * Reads the output stream of a command execution.
	 * @param r BufferedReader
	 * @throws IOException 
	 */
	public static String readOutput(BufferedReader r) throws IOException {
		String line = "", output = "";
		System.out.println("Started");
        while ((line = r.readLine()) != null) {
            output += line + "\n";
        }
        System.out.println(output);
		System.out.println("finished");
        return output;
	}
	
	/**
	 * Performs a command using a cmd command string.
	 * @param command String
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void perform(String command) throws IOException, InterruptedException {
		
		Runnable rn = () -> {
			ProcessBuilder proc_build = new ProcessBuilder().inheritIO().command("cmd.exe", "/c", command);
			System.out.println(command);
			proc_build.redirectErrorStream(true);
			Process p;
			try {
				p = proc_build.start();
				p.waitFor();
			} catch (IOException ex) {
				Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
		
		new Thread(rn).start();
	}
}
