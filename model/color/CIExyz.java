/**
*@author Gianmarco Pettinato
*/
package model.color;
import java.util.Vector;
import java.util.Arrays;
import model.IllegalColorException;
public class CIExyz implements Color{

  //instance variables
  private double x;
  private double y;
  private double z;

  //static variables
  static final double lower_limit_X=0;
  static final double upper_limit_X=0.95047;
  static final double lower_limit_Y=0;
  static final double upper_limit_Y=1.00000;
  static final double lower_limit_Z=0;
  static final double upper_limit_Z=1.08883;
  static final int componets=3;
  static final String[] implementedMethods=new String[]{"negate","mix"};

  //Costructor
  public CIExyz(double t_x, double t_y, double t_z) throws IllegalColorException{
    if(t_x<lower_limit_X || t_x>upper_limit_X ||
    t_y<lower_limit_Y || t_y>upper_limit_Y ||
    t_z<lower_limit_Z || t_z>upper_limit_Z)
      throw new IllegalColorException("values out of boundaries");
    x=t_x;
    y=t_y;
    z=t_z;
  }

  public CIExyz(){
    x=0;
    y=0;
    z=0;
  }

  public CIExyz(CIExyz color)
  {
    x=color.x;
    y=color.y;
    z=color.z;
  }

  public CIExyz(Color color) throws IllegalColorException
  {
    CIExyz c = (CIExyz)color.getCIE();
    x=c.x;
    y=c.y;
    z=c.z;
  }

  /**
  * @brief getNumberOfComponets
  * @return int componets number
  */
  public int getNumberOfComponets()
  {
    return componets;
  }

  /**
  * @brief setComponents set the components =0inside the object
  * @param componets
  */

  public void setComponents(Vector<Double> v_componets) throws IllegalColorException
  {
    Double[] componets = (Double[])v_componets.toArray();
    if(componets[0]<lower_limit_X || componets[0]>upper_limit_X ||
      componets[1]<lower_limit_Y || componets[1]>upper_limit_Y ||
      componets[2]<lower_limit_Z || componets[2]>upper_limit_Z)
          throw new IllegalColorException("values out of boundaries");
    x=componets[0].doubleValue();
    y=componets[1].doubleValue();
    z=componets[2].doubleValue();
  }

  /**
  * @brief getRappresentation
  * @return String that contains the meaning of the values contained in getComponents()
  */
  public String getRappresentation()
  {
    return new String("XYZ");
  }

  /**
  * @brief negate
  * @return Color pointer with a new color with the complementar values
*/
  public CIExyz negate() throws IllegalColorException
  {
    double nx=upper_limit_X-x;
    double ny=upper_limit_Y-y;
    double nz=upper_limit_Z-z;
    return new CIExyz(nx,ny,nz);
  }
  /**
  * @brief mix
  * @param c
  * @return Color pointer with a new Object color mixed
  */
  public CIExyz mix(Color c) throws IllegalColorException
  {
    CIExyz b = new CIExyz(c);
    double m_x= (b.x+this.x)/2;
    double m_y= (b.y+this.y)/2;
    double m_z= (b.z+this.z)/2;
    return new CIExyz(m_x,m_y,m_z);
  }

  /**
  * @brief getCIE
  * @return Color pointer with a clone of *this
  */
  public Color getCIE() throws IllegalColorException
  {
    return new CIExyz(this);
  }

  /**
  * @brief getComponent
  * @return Vector<double> with the x y z component of the color in CIE XYZ
  */

  public Vector<Double> getComponents() {
    Vector<Double> to_return=new Vector<Double>(3);
    to_return.addElement(new Double(x));
    to_return.addElement(new Double(y));
    to_return.addElement(new Double(z));
    return to_return;
  }

  public Vector<String> getInfo(){
    //TODO
    return new Vector<String>();
  }

  /**
  * @brief availableOperations
  * @return all the operation that has been implemented
  */
  public Vector<String> availableOperations() {
    Vector<String> to_return = new Vector<String>(Arrays.asList(implementedMethods));
    return to_return;
  }

  /**
  * @brief operator /
  * @throws IllegalColorException("operation not available");
  */
  public CIExyz division(int div) throws IllegalColorException {
    throw new IllegalColorException("operation not available");
  }

}
