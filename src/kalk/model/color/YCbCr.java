/**
* @author Giuseppe Vito Bitetti
* @date 20/7/2018
* @class YCbCr
* @brief this class uses as base class RGB
* and stores a color in YCbCr representation
*/

package kalk.model.color;

import java.util.Vector;
import java.util.Arrays;
import kalk.model.color.Color;
import kalk.model.factory.ColorFactory;
import kalk.model.IllegalColorException;
import kalk.model.color.RGB;

public class YCbCr extends RGB{
  // instance variables
  private Double y;
  private Double cb;
  private Double cr;
  @SuppressWarnings("unused")
  private static final boolean factory = ColorFactory.addColorFactory("YCbCr", new YCbCr());
  // static variables
  static final int componets=3;
  static final Double max_y= 235.0;
  static final Double min_y= 16.0;
  static final Double max_cbcr= 240.0;
  static final Double min_cbcr= 16.0;

  // Costructor
  public YCbCr(){
    y=0.0;
    cb=0.0;
    cr=0.0;
  }

  public YCbCr(Double _y, Double _cb, Double _cr){
    super(getCIE(_y, _cb, _cr));
    y=_y;
    cb=_cb;
    cr=_cr;
  }

  public YCbCr(Color from) throws IllegalColorException{
    super(from);
    RGB temp= new RGB(from);
    Vector<Double> rgb= temp.getComponents();
    Double ty= 16  + 1/255* (65.738*rgb.elementAt(0) + 129.057*rgb.elementAt(1) + 25.064*rgb.elementAt(2));
    Double tcb= 128 + 1/255* (-37.945*rgb.elementAt(0) - 74.494*rgb.elementAt(1) + 112.439*rgb.elementAt(2));
    Double tcr= 128 + 1/255* (112.439*rgb.elementAt(0) - 94.154*rgb.elementAt(1) - 18.285*rgb.elementAt(2));
    if(tcb>max_cbcr || tcb>max_cbcr || tcr<min_cbcr || tcr<min_cbcr || ty>max_y ||ty<min_y){
        throw new IllegalColorException("il colore non rientra nei parametri");
    }else{
        y=ty;
        cb=tcb;
        cr=tcr;
    }
  }

  public YCbCr(YCbCr from) throws IllegalColorException{
    super(from);
    y=from.y;
    cb=from.cb;
    cr=from.cr;
  }

  /**
   * @brief YCbCr::getRappresentation
   * @return QString that contains the meaning of the values contained in getComponents()
   */
  public String getRappresentation(){
      return new String("YCbCr");
  }

  /**
   * @brief YCbCr::negate
   * @return Color pointer with a new color with the complementary values
 * @throws IllegalColorException 
   */
  public Color negate() throws IllegalColorException{
      return new YCbCr(super.negate());
  }

  /**
   * @brief YCbCr::mix
   * @param a
   * @return Color pointer with a new Object color mixed
 * @throws IllegalColorException 
   */
  public Color mix(Color a) throws IllegalColorException{
      return new YCbCr(super.mix(a));
  }

  /**
   * @brief YCbCr::getCIE
   * @param y
   * @param cb
   * @param cr
   * @return Color pointer with a clone of *this in the CIExyz format
 * @throws IllegalColorException 
   */
  public Color getCIE(double y, double cb, double cr) throws IllegalColorException{
      int r= (int)((298.082*y + 408.583*cr) / 256 - 222.921);
      int g= (int)((298.082*y - 100.291*cb - 208.120*cr ) / 256 + 135.576);
      int b= (int)((298.082*y + 516.412*cb) / 256 - 276.836);
      return super.getCIE(r,g,b);
  }

  /**
   * @brief YCbCr::getRGB
   * @param y
   * @param cb
   * @param cr
   * @return Color pointer with a clone of *this in the RGB format
 * @throws IllegalColorException 
   */
  private Color getRGB(double y, double cb, double cr) throws IllegalColorException{
      int r= (int)((298.082*y + 408.583*cr) / 256 - 222.921);
      int g= (int)((298.082*y - 100.291*cb - 208.120*cr ) / 256 + 135.576);
      int b= (int)((298.082*y + 516.412*cb) / 256 - 276.836);
      return new RGB(r,g,b);
  }

  /**
   * @brief YCbCr::getComponent
   * @return QVector<double> with the y, cb, cr component of the color in YCbCr
   */
  public Vector<Double> getComponents(){
      Vector<Double> to_return = new Vector<Double>();
      to_return.add(y);
      to_return.add(cb);
      to_return.add(cr);
      return to_return;
  }

  /**
   * @brief YCbCr::getNumberOfComponets
   * @return int components number
   */
  public int getNumberOfComponets(){
      return componets;
  }

  /**
   * @brief YCbCr::setComponents set the components inside the object
   * @param componets
 * @throws IllegalColorException 
   */
  public void setComponents(Vector<Double> componets) throws IllegalColorException{
      y=componets.elementAt(0);
      cb=componets.elementAt(1);
      cr=componets.elementAt(2);
      super.setComponents(getRGB(componets.elementAt(0), componets.elementAt(1), componets.elementAt(2)).getComponents());
  }

  /**
   * @brief YCbCr::operator /
   * @param div
   * @return Color pointer with a new Object color
 * @throws IllegalColorException 
   */
  public Color division(int div) throws IllegalColorException{
      return new YCbCr(super.division(div));
  }
  
  public Color getNewIstance() {
	  return new YCbCr();
  }


}
