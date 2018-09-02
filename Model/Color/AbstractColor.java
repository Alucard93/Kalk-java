/**
  * @author Giuseppe Bitetti & Gianmarco Pettinato
  * @class AbstractColor
  * @brief this class is the base color class for future reference
  *
  */
public abstract class AbstractColor{
  public static final String allOpts[3][3]={
    {"negate","none",""},
    {"mix","color",""},
    {"divide","int",""}
  };

  public abstract int getNumberOfComponets(); // returns how many componets the rappresentation needs
  public abstract void setComponents(Vector<double> componets);
  //Possible operations
  public abstract  Color* getCIE(); //returns CIExyz class pointer
  public abstract  Color* negate(); //returns in the current color rappresentation the negate color
  public abstract  Color* mix(const Color* c1); //returns in the current color rappresentation the result of mixing two color
  public abstract  Color* operator/(const int &div); //return in the current color rappresentation the division of its components
  //Getting current status
  public abstract  Vector<String> getInfo();
  public abstract  Vector<String> availableOperations(); //returns a vector with the name of the method available and the Types can be used with it    public abstract  QVector<double> getComponents();
  public abstract  String getRappresentation();
}
