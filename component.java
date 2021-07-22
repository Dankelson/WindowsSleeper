package com.langweenee.www;

import java.io.IOException;

public class component {
	
	public static void startTimer(int duration) {
		try{
			Process win = Runtime.getRuntime().exec("shutdown -s -t " + duration);
		}catch(IOException e) {
			System.err.println(e);
		}
	}
	
	public static void endTimer() {
		try{
			Process win = Runtime.getRuntime().exec("shutdown -a");
		}catch(IOException e) {
			System.err.println(e);
		}
	}
}
