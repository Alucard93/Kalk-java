/**
 * 
 */
package kalk.model;

import java.util.Vector;
import kalk.model.color.Color;
import kalk.model.factory.*;
import kalk.model.IllegalColorException;

/**
 * @author Gianmarco Pettinato
 *
 */
public class ColorModel implements Model {
	private final ColorFactory factory = new ColorFactory();
	private final ColorModel old;
	private Color l_color;
	private Color r_color;
	private Color result;
	private String l_type;
	private String r_type;
	private int alternative_right;
	public Vector<String> opts;
	private int op;
	
	public ColorModel() {
		old=null;
	}
	
	public ColorModel(ColorModel old) {
		this.old=old;
	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#availableOperations()
	 */
	@Override
	public Vector<String> availableOperations() {
		opts = ColorFactory.availableOperations();
		return opts;
	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#allAvailableTypes()
	 */
	@Override
	public Vector<String> allAvailableTypes() {
		return ColorFactory.getAllColorTypes();
	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setLeftType(java.lang.String)
	 */
	@Override
	public void setLeftType(String type) {
		l_type=type;
		l_color = ColorFactory.getNewColor(type);
	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setLeftValues(java.util.Vector)
	 */
	@Override
	public void setLeftValues(Vector<String> values) throws IllegalColorException{
		Vector<Double> toSet = str2double(values);
		l_color.setComponents(toSet);

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setRightType(java.lang.String)
	 */
	@Override
	public void setRightType(String type) {
		r_type= type;
		if(!type.equals("int"))
			r_color = ColorFactory.getNewColor(type);

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setRightValues(java.util.Vector)
	 */
	@Override
	public void setRightValues(Vector<String> values) throws IllegalColorException {
		Vector<Double> toSet = str2double(values);
		if(!r_type.equals("int"))
			r_color.setComponents(toSet);
		else
			alternative_right=toSet.firstElement().intValue();

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setLastResultAsLeftOperand()
	 */
	@Override
	public void setLastResultAsLeftOperand() {
		//l_type = old.l_type;
		//TODO

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#setOp(java.lang.String)
	 */
	@Override
	public void setOp(String eOperation) {
		op = opts.indexOf(eOperation);

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#execute()
	 */
	@Override
	public void execute() throws IllegalColorException
	{
		if(r_type.equals("int"))
			result = ColorFactory.Execution(l_color, op, alternative_right);
		else if(r_color!=null)
			result = ColorFactory.Execution(l_color, op, r_color);
		else
			result = ColorFactory.Execution(l_color, op);
			
	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#getResult()
	 */
	@Override
	public void getResult() {
		// Vector<String> Result

	}

	/* (non-Javadoc)
	 * @see kalk.model.Model#getHistory()
	 */
	@Override
	public Vector<String> getHistory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Vector<Double> str2double(Vector<String> values){
		Vector<Double> toReturn = new Vector<Double>();
		for(String value:values) {
			toReturn.add(new Double(value));
		}
		return toReturn;
	}

}
