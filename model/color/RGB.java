/**
 * @file rgb.h
 * @author Gianmarco Pettinato
 * @date 20/7/2018
 * @class RGB
 * @brief this class uses the as base class CIExyz
 * and stores a color in RGB rappresentation
*/
package model.color;
import java.util.Vector;
import java.util.Arrays;
import model.IllegalColorException;
public class RGB extends CIExyz{
  private int[3] sRGB;

  private static double CIE_RGB[3][3]; //contains matrix to transforma CIExyz color rappresentation to sRGB
  private static double RGB_CIE[3][3];//contains matrix to transforma sRGB color rappresentation to CIExyz
  static final int lower_limit = 0;
  static final int upper_limit = 255;
  static final int componets = 3;
}
