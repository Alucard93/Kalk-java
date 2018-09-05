/**
* @author Giuseppe Vito Bitetti
* @date 20/7/2018
* @class YUV
* @brief this class uses as base class RGB
* and stores a color in YUV rappresentation
*/

package kalk.model.color;

import java.util.Vector;
import java.util.Arrays;
import kalk.model.color.Color;
import kalk.model.factory.ColorFactory;
import kalk.model.IllegalColorException;
import kalk.model.color.RGB;

public class YUV extends RGB{
// instance variables
    private double y;
    private double u;
    private double v;
// static variables
    static final double low_y = 0.0;
    static final double max_y = 1.0;
    static final double low_uv = -0.6;
    static final double max_uv = 0.6;
    static final int componets=3;
// Costructor
  public YUV(){
    y=0;
    u=0;
    v=0;
  }

  public YUV(double _y, double _u, double _v){
    super(getCIE(_y, _u, _v));
    y=_y;
    u=_u;
    v=_v;
  }

  public YUV( Color from) throws IllegalColorException{
    super(from);
    Vector<Double> xyz=super.getComponents();
    Double ty= 0.299*xyz.elementAt(0)+0.587*xyz.elementAt(1)+0.114*xyz.elementAt(2);
    Double tu= -0.147*xyz.elementAt(0)-0.289*xyz.elementAt(1)+0.436*xyz.elementAt(2);
    Double tv= 0.615*xyz.elementAt(0)-0.515*xyz.elementAt(1)-0.100*xyz.elementAt(2);
    if(tu>max_uv || tv>max_uv || tu<low_uv || tv<low_uv || ty>max_y ||ty<low_y){
        throw new IllegalColorException("il colore non rientra nei parametri");
    }else{
        y=ty;
        u=tu;
        v=tv;
    }
  }

  public YUV(YUV from){
    super(from);
    y=from.y;
    u=from.u;
    v=from.v;
  }

  /**
   * @brief YUV::getRappresentation
   * @return QString that contains the meaning of the values contained in getComponents()
   */
  public String getRappresentation(){
      return String("YUV");
  }

  /**
 * @brief YUV::negate
 * @return Color pointer with a new color with the complementar values
 */
 public Color negate(){
    return new YUV(super.negate());
  }

  /**
 * @brief YUV::mix
 * @param a
 * @return Color pointer with a new Object color mixed
 */
  public Color mix(Color a){
    return new YUV(super.mix(a));
  }

  /**
 * @brief YUV::getRGB
 * @param _y
 * @param _u
 * @param _v
 * @return Color pointer with a clone of *this in the RGB format
 */
 private Color getRGB(Double _y, Double _u, Double _v){
    int r= (int)(_y + 1.140*_v);
    int g= (int)(_y - 0.395*_u - 0.581*_v);
    int b= (int)(_y + 2.032*_u);
    return new RGB(r,g,b);
  }

  /**
 * @brief YUV::getCIE
 * @param y
 * @param u
 * @param v
 * @return Color pointer with a clone of *this in the CIExyz format
 */
 public Color getCIE(double y, double u, double v){
    int r= (int)(y + 1.140*v);
    int g= (int)(y - 0.395*u - 0.581*v);
    int b= (int)(y + 2.032*u);
    return super.getCIE(r, g, b);
  }

  /**
 * @brief YUV::getComponent
 * @return QVector<double> with the y, u, v component of the color in YUV
 */
 public Vector<Double> getComponents(){
    Vector<Double> to_return={y,u,v};
    return to_return;
  }

  /**
 * @brief YUV::getNumberOfComponets
 * @return int componets number
 */
public int getNumberOfComponets(){
    return componets;
}

/**
 * @brief YUV::setComponents set the components inside the object
 * @param componets
 */
public void setComponents(Vector<Double> componets){
    y=componets.elementAt(0);
    u=componets.elementAt(1);
    v=componets.elementAt(2);
    super.setComponents(getRGB(componets.elementAt(0), componets.elementAt(1), componets.elementAt(2)).getComponents());
}

/**
 * @brief YUV::operator /
 * @param div
 * @return Color pointer with a new Object color
 */
 public Color division(int div){
    return new YUV(super.operator/(div));
  }

};
