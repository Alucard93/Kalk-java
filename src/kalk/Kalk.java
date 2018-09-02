package kalk;

import java.io.IOException;
import java.util.*;
import kalk.model.ColorModel;

public class Kalk{
	
  public static void main(String[] args) {
	  ColorModel model = new ColorModel();
      Vector<String> toPrint = model.allAvailableTypes();
      for(String print: toPrint)
    	  System.out.println(print);
      try {
		System.in.read();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
}
