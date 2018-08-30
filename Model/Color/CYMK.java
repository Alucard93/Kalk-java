/**
*@author Giuseppe Vito Bitetti
*/

import CIExyz;
import IllegalColorException;
public class CYMK extends CIExyz{

  //instance variables
  private unsigned int cyan;
  private unsigned int magenta;
  private unsigned int yellow;
  private unsigned int key_black;

  //static variables
  static final unsigned int upper_limit_cymk=100;
  static final unsigned int lower_limit_cymk=0;
  static final int componets=4;

  //Costructor
  public CYMK(unsigned int c , unsigned int y, unsigned int m, unsigned int k){
    cyan=c;
    yellow=y;
    magenta=m;
    key_black=k;
    super(getCIE(c, y, m, k));
  }
/*
  CYMK(Color from){
      QVector<double> xyz=CIExyz::getComponents();
      double cp=1 -(3.063219*xyz[0] -1.393326*xyz[1] -0.475801*xyz[2]);
      double mp=1 -(-0.969245*xyz[0] +1.875968*xyz[1] +0.041555*xyz[2]);
      double yp=1 -(0.067872*xyz[0] -0.228833*xyz[1] +1.069251*xyz[2]);
      double kp=min({cp,mp,yp});
      double t=1-kp;
      if(t==0.0){
          cyan=0;
          yellow=0;
          magenta=0;
          key_black=0;
      }else{
          unsigned int c=static_cast<unsigned int>(((cp-kp)/t)*100);
          unsigned int y=static_cast<unsigned int>(((yp-kp)/t)*100);
          unsigned int m=static_cast<unsigned int>(((mp-kp)/t)*100);
          unsigned int k=static_cast<unsigned int>(kp);
          if((c>upper_limit_cymk || y>upper_limit_cymk || m>upper_limit_cymk || k>upper_limit_cymk))
              throw IllegalColorException("il colore non rientra nei parametri");
          else{
              cyan=c;
              yellow=y;
              magenta=m;
              key_black=k;
          }
      }
  }*/
  public CYMK(CYMK from){
      cyan=from.cyan;
      yellow=from.yellow;
      magenta=from.magenta;
      key_black=from.key_black;
      super(from);
  }

  /**
  * @brief getRappresentation
  * @return String that contains the meaning of the values contained in getComponents()
  */
  public String getRappresentation(){
    return String("CYMK");
  }

  /**
  * @brief negate
  * @return Color pointer with a new color with the complementar values
  */
  public CYMK negate(){
    return new CYMK(super.negate());
  }

  /**
  * @brief mix
  * @param a
  * @return Color pointer with a new Object color mixed
  */
  public CYMK mix(CYMK* a){
    return new CYMK(super.mix(a));
  }

  /**
  * @brief getCIE
  * @param c
  * @param y
  * @param m
  * @param k
  * @return Color pointer with a clone of *this
  */
  public CYMK getCIE(unsigned int c, unsigned int y, unsigned int m, unsigned int k){
    if((c>upper_limit_cymk || y>upper_limit_cymk || m>upper_limit_cymk || k>upper_limit_cymk))
        throw IllegalColorException("il colore non rientra nei parametri");
    else{
        double tx=0.41245 * ((1-k/100)*(1-c/100)) + 0.35757 * ((1-k/100)*(1-m/100)) + 0.18043 * ((1-k/100)*(1-y/100));
        double ty=0.21267 * ((1-k/100)*(1-c/100)) + 0.71515 * ((1-k/100)*(1-m/100)) + 0.07217 * ((1-k/100)*(1-y/100));
        double tz=0.01933 * ((1-k/100)*(1-c/100)) + 0.11919 * ((1-k/100)*(1-m/100)) + 0.95030 * ((1-k/100)*(1-y/100));

        return new CIExyz(tx, ty, tz);
    }
  }

  /**
  * @brief getComponent
  * @return Vector<double> with the cyan, yellow, magenta, key black component of the color in CIE XYZ
  */
  public Vector<double> getComponents(){
    Vector<double> to_return={(double)cyan, (double)yellow, (double)magenta, (double)key_black};
    return to_return;
  }

  /**
  * @brief getNumberOfComponets
  * @return int componets number
  */
  public int getNumberOfComponets(){
    return componets;
  }

  /**
  * @brief setComponents set the components inside the object
  * @param componets
  */
  public void setComponents(Vector<double> componets){
    QVector<double> tcie;
    tcie[0]=0.41245 * ((1-componets[3]/100)*(1-componets[0]/100)) + 0.35757 * ((1-componets[3]/100)*(1-componets[2]/100)) + 0.18043 * ((1-componets[3]/100)*(1-componets[1]/100));
    tcie[1]=0.21267 * ((1-componets[3]/100)*(1-componets[0]/100)) + 0.71515 * ((1-componets[3]/100)*(1-componets[2]/100)) + 0.07217 * ((1-componets[3]/100)*(1-componets[1]/100));
    tcie[2]=0.01933 * ((1-componets[3]/100)*(1-componets[0]/100)) + 0.11919 * ((1-componets[3]/100)*(1-componets[2]/100)) + 0.95030 * ((1-componets[3]/100)*(1-componets[1]/100));
    super.setComponents(tcie);
    cyan=static_cast<unsigned int>(componets[0]);
    yellow=static_cast<unsigned int>(componets[1]);
    magenta=static_cast<unsigned int>(componets[2]);
    key_black=static_cast<unsigned int>(componets[3]);
  }

}
