package kalk;

import java.util.*;
import kalk.model.ColorModel;
import kalk.model.IllegalColorException;

public class Use {
	static boolean firstrun=false;
	static Scanner inTerminal = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean ans = false;
		ColorModel current = null;
		boolean exit = false;
		while(!exit) {
			switch(choseOptions()) {
			case "History":
				printHistory();
				break;
			case "Close":
				exit = true;
				inTerminal.close();
				break;
			case "ANS":
				ans=true;
			default:
				current = newOperation(current,ans);
			}
		}
	}
	
	public static String choseOptions() {
		System.out.println("Come si vuole procedere? [Nuovo,Close] default=(New)");
		return inTerminal.nextLine();
	}
	
	public static ColorModel newOperation(ColorModel current, boolean ans) {
		ColorModel model = new ColorModel(current);
		if(!ans) {
			System.out.println("This calc can do this operations");
			System.out.println(model.availableOperations());
			System.out.println("This are the availables representation");
			System.out.println(model.allAvailableTypes());
			String type = inTerminal.next();
			int n = model.setLeftType(type);
			try {
				model.setLeftValues(getInput(n));
			} catch (IllegalColorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			//TODO
		}
		System.out.println(model.availableOperations());
		model.setOp(inTerminal.nextLine());
		System.out.println(model.rightTypesAvailables());
		int n = model.setRightType(inTerminal.nextLine());
		System.out.println("reuquires "+n+"parameters");
		try {
			model.setRightValues(getInput(n));
		} catch (IllegalColorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			model.execute();
		} catch (IllegalColorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<String> result = model.getResult();
		for(String line: result)
			System.out.println(line);
		return model;
		
	}
	
	public static void printHistory() {
		
	}
	
	public static Vector<String> getInput(int n){
		System.out.println("reuquires "+n+" parameters");
		int i = n; 
		Vector<String> toSet = new Vector<String>();
		while(i>=0) {
			toSet.add(inTerminal.nextLine());
			i--;
		}
		toSet.remove(0);
		return toSet;
	}

}
