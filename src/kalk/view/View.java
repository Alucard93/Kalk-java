package kalk.view;

import java.util.*;

public interface View {
	
	
	/**
	 * Sets all types available for the operations
	 * @param types
	 * 
	 */
	public void setAllTypes(Vector<String> types);
	
	/**
	 * Sets all operation that can be done in the Kalk program
	 * @param opts
	 * 
	 */
	public void setAllOperationAvailable(Vector<String> opts);
	
	/**
	 * Sets all operation that can be done using the specified type
	 * @param opts
	 */
	public void setAllOperationPermitted(Vector<String> permitted_opts);
	
	/**
	 * Shows all types available
	 */
	public void showAllTypes();
	
	/**
	 * ask to the user what type wants to use
	 * @param isLeft
	 * @return what type has been chosen
	 */
	public String getType(boolean isLeft);
	
	/**
	 * ask to the user what values wants in the representation
	 * @param isLeft
	 * @return all the values asked to the user
	 */
	public Vector<String> getValues(boolean isLeft);
	
	/**
	 * ask to the user what operation wants to do
	 * @return what operation has been chosen
	 */
	public String getOperation ();
	
	/**
	 * runs the view
	 */
	public void show();
}
