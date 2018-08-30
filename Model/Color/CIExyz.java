/**
*@author Gianmarco Pettinato
*/

import AbstractColor;
import IllegalColorException;
public class CIExyz implements AbstractColor{

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
  static final Vector<String> implementedMethods={"negate","mix"};

  //Costructor
  public CIExyz(double f_x=0, double f_y=0, double f_z=0) throws IllegalColorException{
    if(t_x<lower_limit_X || t_x>upper_limit_X ||
    t_y<lower_limit_Y || t_y>upper_limit_Y ||
    t_z<lower_limit_Z || t_z>upper_limit_Z)
      throw new IllegalColorException("values out of boundaries");
    x=t_x;
    y=t_y;
    z=t_z;
  }

  public CIExyz(CIExyz color){
    x=color.x;
    y=color.y;
    z=color.z;
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
  * @brief setComponents set the components inside the object
  * @param componets
  */

  public void setComponents(Vector<double> componets) throws IllegalColorIllegalColorExceptionException
  {
    if(componets[0]<lower_limit_X || componets[0]>upper_limit_X ||
      componets[1]<lower_limit_Y || componets[1]>upper_limit_Y ||
      componets[2]<lower_limit_Z || componets[2]>upper_limit_Z)
          throw new IllegalColorException("values out of boundaries");
    x=componets[0];
    y=componets[1];
    z=componets[2];
  }

  /**
  * @brief getRappresentation
  * @return String that contains the meaning of the values contained in getComponents()
  */
  public String getRappresentation()
  {
    return String("XYZ");
  }

  /**
  * @brief negate
  * @return Color pointer with a new color with the complementar values
  */
  public CIExyz negate()
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
  public CIExyz mix( CIExyz c)
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
  public CIExyz getCIE()
  {
    return new CIExyz(this);
  }

  /**
  * @brief getComponent
  * @return Vector<double> with the x y z component of the color in CIE XYZ
  */

  public Vector<double> getComponents() {
    Vector<double> to_return={x,y,z};
    return to_return;
  }

  /**
  * @brief availableOperations
  * @return all the operation that has been implemented
  */
  Vector<String> availableOperations() {
    return implementedMethods;
  }

  /**
  * @brief operator /
  * @throws IllegalColorException("operation not available");
  */
  CIExyz operator/(int div) {
    throw new IllegalColorException("operation not available");
  }

}
