/**
  * @author Giuseppe Bitetti & Gianmarco Pettinato
  * @class Color
  * @brief this class is the base color class for future reference
  *
  */
package model.color;
import model.IllegalColorException;
import java.util.Vector;
public interface Color{
  public static final String[][] allOpts= new String[][]{
                                        {"negate","none",""},
                                        {"mix","color",""},
                                        {"divide","int",""}};

  public int getNumberOfComponets(); // returns how many componets the rappresentation needs
  public void setComponents(Vector<Double> componets) throws IllegalColorException;
  //Possible operations
  public Color getCIE() throws IllegalColorException; //returns CIExyz class pointer
  public Color negate() throws IllegalColorException; //returns in the current color rappresentation the negate color
  public Color mix(Color c1) throws IllegalColorException; //returns in the current color rappresentation the result of mixing two color
  public Color division(int div) throws IllegalColorException; //return in the current color rappresentation the division of its components
  //Getting current status
  public Vector<String> getInfo();
  public Vector<String> availableOperations(); //returns a vector with the name of the method available and the Types can be used with it    public abstract  QVector<double> getComponents();
  public String getRappresentation();
}
