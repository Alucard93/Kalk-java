/**
* @author Giuseppe Vito Bitetti
* @date 20/7/2018
* @class CYMK
* @brief this class uses the as base class CIExyz
* and stores a color in CYMK rappresentation
*/

package kalk.model.color;

import java.util.Vector;
import java.util.Arrays;
import kalk.model.color.Color;
import kalk.model.factory.ColorFactory;
import kalk.model.IllegalColorException;
import kalk.model.color.CIExyz;
public class CYMK extends CIExyz {

	// instance variables
	private int cyan;
	private int magenta;
	private int yellow;
	private int key_black;

	// static variables
	static final int upper_limit_cymk = 100;
	static final int lower_limit_cymk = 0;
	static final int componets = 4;

	// Costructor
	public CYMK(){
		cyan=0;
		yellow=0;
		magenta=0;
		key_black=0;
	}

	public CYMK(int c, int y, int m, int k) {
		super(getCIE(c, y, m, k));
		cyan = c;
		yellow = y;
		magenta = m;
		key_black = k;

	}

	public CYMK(Color from) throws IllegalColorException{
      Vector<Double> xyz=super.getComponents();
			Double _r=3.063219*xyz.elementAt(0) -1.393326*xyz.elementAt(1) -0.475801*xyz.elementAt(2);
		  Double _g=-0.969245*xyz.elementAt(0) +1.875968*xyz.elementAt(1) +0.041555*xyz.elementAt(2);
		  Double _b=0.067872*xyz.elementAt(0) -0.228833*xyz.elementAt(1) +1.069251*xyz.elementAt(2);
		  Double _c=1-_r;
		  Double _y=1-_g;
		  Double _m=1-_b;
			Double _k=_c;
			if(_k<_y)
				_k=_y;
			if(_k<_m)
				_k=_m;
			_k=1-_k;
		  Double k=_k*100;
		      Double c=((_c-_k)/(1-_k))*100;
		      Double m=((_m-_k)/(1-_k))*100;
		      Double y=((_y-_k)/(1-_k))*100;
		      if((c>upper_limit_cymk || y>upper_limit_cymk || m>upper_limit_cymk || k>upper_limit_cymk))
		          throw new IllegalColorException("il colore non rientra nei parametri");
		      else{
		          cyan=c.intValue();
		          yellow=y.intValue();
		          magenta=m.intValue();
		          key_black=k.intValue();
		      }
		}

	public CYMK(CYMK from) {
		super(from);
		cyan = from.cyan;
		yellow = from.yellow;
		magenta = from.magenta;
		key_black = from.key_black;
	}

	/**
	 * @brief getRappresentation
	 * @return String that contains the meaning of the values contained in
	 *         getComponents()
	 */
	public String getRappresentation() {
		return new String("CYMK");
	}

	/**
	 * @brief negate
	 * @return Color pointer with a new color with the complementar values
	 */
	public CYMK negate() {
		return new CYMK(super.negate());
	}

	/**
	 * @brief mix
	 * @param a
	 * @return Color pointer with a new Object color mixed
	 */
	public CYMK mix(CYMK a) {
		return new CYMK(super.mix(a));
	}

	/**
	 * @brief getCIE
	 * @param c
	 * @param y
	 * @param m
	 * @param k
	 * @return Color pointer with a clone of *this
	 * @throws IllegalColorException
	 */
	public CIExyz getCIE(int c, int y, int m, int k) throws IllegalColorException {
		if ((c > upper_limit_cymk || y > upper_limit_cymk || m > upper_limit_cymk || k > upper_limit_cymk))
			throw new IllegalColorException("il colore non rientra nei parametri");
		else {
			Double tx = 0.41245 * ((1 - k / 100) * (1 - c / 100)) + 0.35757 * ((1 - k / 100) * (1 - m / 100))
					+ 0.18043 * ((1 - k / 100) * (1 - y / 100));
			Double ty = 0.21267 * ((1 - k / 100) * (1 - c / 100)) + 0.71515 * ((1 - k / 100) * (1 - m / 100))
					+ 0.07217 * ((1 - k / 100) * (1 - y / 100));
			Double tz = 0.01933 * ((1 - k / 100) * (1 - c / 100)) + 0.11919 * ((1 - k / 100) * (1 - m / 100))
					+ 0.95030 * ((1 - k / 100) * (1 - y / 100));

			return new CIExyz(tx, ty, tz);
		}
	}

	/**
	 * @brief getComponent
	 * @return Vector<Double> with the cyan, yellow, magenta, key black component of
	 *         the color in CIE XYZ
	 */
	public Vector<Double> getComponents() {
		Vector<Double> to_return = { (Double) cyan, (Double) yellow, (Double) magenta, (Double) key_black };
		return to_return;
	}

	/**
	 * @brief getNumberOfComponets
	 * @return int componets number
	 */
	public int getNumberOfComponets() {
		return componets;
	}

	/**
  * @brief setComponents set the components inside the object
  * @param componets
  */
  public void setComponents(Vector<Double> componets){
    Vector<Double> tcie;
    tcie.elementAt(0)=0.41245 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(0)/100)) + 0.35757 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(2)/100)) + 0.18043 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(1)/100));
    tcie.elementAt(1)=0.21267 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(0)/100)) + 0.71515 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(2)/100)) + 0.07217 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(1)/100));
    tcie.elementAt(2)=0.01933 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(0)/100)) + 0.11919 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(2)/100)) + 0.95030 * ((1-componets.elementAt(3)/100)*(1-componets.elementAt(1)/100));
    super.setComponents(tcie);
    cyan=componets.elementAt(0).intValue();
    yellow=componets.elementAt(1).intValue();
    magenta=componets.elementAt(2).intValue();
    key_black=componets.elementAt(3).intValue();
  }

}
