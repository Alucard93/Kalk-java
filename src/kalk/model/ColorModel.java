/**
 * 
 */
package kalk.model;

import java.util.ArrayList;
import java.util.Vector;
import kalk.model.color.Color;
import kalk.model.factory.*;
import kalk.model.IllegalColorException;

/**
 * @author Gianmarco Pettinato
 *
 */
public class ColorModel implements Model  {
	private static String defaultType = "none";
	private static ArrayList<ColorModel> localHistory = new ArrayList<ColorModel>();
	private int alternativeRight=-1;
	private Color left;
	private String leftType;
	private int operation = -1;
	private Color result;
	private Color right; 
	private String rightType;
	
	public ColorModel(){
		ColorFactory.setFactoryReady();
		left = null;
		right = null;
		result = null;
		leftType = defaultType;
		rightType = defaultType;
	}
	
	private void addHistory() throws CloneNotSupportedException{
		localHistory.add((ColorModel) this.clone());
	}
	
	public Vector<String> allAvailableTypes() {
		return ColorFactory.typeByOperation(-1);
	}
	
	public Vector<String> availableOperations() {
		return ColorFactory.availableOperations();
	}
	
	private Vector<String> double2string(Vector<Double> values){
		Vector<String> toReturn = new Vector<String>();
		for(double value : values) {
			toReturn.add(String.valueOf(value));
		}
		return toReturn;
	}
	
	public void execute() throws IllegalColorException, CloneNotSupportedException {
		if(rightType.equals("intero"))
			result = ColorFactory.Execution(left, operation, alternativeRight);
		else
			result = ColorFactory.Execution(left, operation, right);
		addHistory();
	}
	
	public Vector<String> getHistory(){
		int size = localHistory.size();
		Vector<String> history = new Vector<String>(localHistory.size());
		for(ColorModel model:localHistory) {
			history.add("operazione n."+String.valueOf(size)+'\n'+model.toString());
			size--;
		}			
		return history;
	}
	
	public Vector<String> getResult(){
		return double2string(result.getComponents());
	}
	
	public Vector<String> permittedOperations() {
		return ColorFactory.permittedOperations(leftType);
	}
	
	public Vector<String> permittedTypes(){
		return ColorFactory.typeByOperation(operation);
	}
	
	public int setLeftType(String type) {
		if(left!=null)
			left=null;
		left=ColorFactory.getNewColor(type);
		leftType=type;
		return left.getNumberOfComponets();
	}
	
	public void setLeftValues(Vector<String> values) throws IllegalColorException 
	{
		Vector<Double> toSet = string2double(values);
		left.setComponents(toSet);
	}
	
	
	
	public void setOp(String operation) {
		this.operation = ColorFactory.availableOperations().indexOf(operation);
	}
	
	public int setRightType(String type) {
		int size = 1 ;
		if(right!=null)
			right=null;
		if(!type.equals("intero"))
		{
			right=ColorFactory.getNewColor(type);
			size = right.getNumberOfComponets();
		}
		rightType=type;
		
		return size;
	}

	public void setRightValues(Vector<String> values) throws IllegalColorException 
	{
		Vector<Double> toSet = string2double(values);
		if(rightType!="intero")
			right.setComponents(toSet);
		else
			alternativeRight = toSet.firstElement().intValue();
	}
	
	private Vector<Double> string2double(Vector<String> values){
		Vector<Double> toReturn = new Vector<Double>();
		for(String value : values) {
			if(!value.isEmpty())
				toReturn.add((double)Double.valueOf(value));
		}
		return toReturn;
	}
	@Override
	public String toString() {
		String toReturn ="";
		if(left!=null) {
			toReturn+=leftType;
			toReturn+=" "+double2string(left.getComponents());
		}
		if(operation!=-1)
			toReturn+=" "+ColorFactory.availableOperations().elementAt(operation);
		if(rightType!=defaultType) {
			toReturn+=" "+rightType;
			if(right!=null && rightType!="intero") {
				toReturn+=" "+double2string(right.getComponents());
			}
			if(rightType.equals("intero")) {
				toReturn+=" "+String.valueOf(alternativeRight);
			}
		}
		if(result!=null)
			toReturn+=" "+double2string(result.getComponents());
		return toReturn;
			
	}

}
