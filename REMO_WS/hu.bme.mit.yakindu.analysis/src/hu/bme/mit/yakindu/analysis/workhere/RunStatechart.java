package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;

import java.util.Scanner;

// import hu.bme.mit.yakindu.analysis.RuntimeService;
// import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;


import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;




public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
//		s.runCycle();
//		print(s);
//		s.raiseStart();
//		s.runCycle();
//		System.in.read();
//		s.raiseWhite();
//		s.runCycle();
//		print(s);
//		System.exit(0);
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		
			switch(scan.nextLine()) {
				case "start": 
					s.raiseStart();
					s.runCycle();
					System.out.println(s.getSCInterface().getWhiteTime() + " - " + s.getSCInterface().getBlackTime());
					break;
					
				case "black": 
					s.raiseBlack();
					s.runCycle();
					System.out.println(s.getSCInterface().getWhiteTime() + " - " + s.getSCInterface().getBlackTime());
					break;
					
				case "white": 
					s.raiseWhite();
					s.runCycle();
					System.out.println(s.getSCInterface().getWhiteTime() + " - " + s.getSCInterface().getBlackTime());
					break;
					
				case "exit": 
					System.out.println(s.getSCInterface().getWhiteTime() + " - " + s.getSCInterface().getBlackTime());
					System.exit(0);
					break;
					
				default:
					System.out.println(s.getSCInterface().getWhiteTime() + " - " + s.getSCInterface().getBlackTime());
					break;
			
			}
		
		}
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}
