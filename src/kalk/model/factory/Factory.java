package kalk.model.factory;

import kalk.model.factory.ColorFactory;
import kalk.model.factory.GenericFactory;
import kalk.model.color.Color;

public class Factory<Class T instaceof Color> implements GenericFactory{
  public Factory(String name)
  {
    ColorFactory.addColorFactory(name, this);
  }
  public Color create(){
    return new T.getNewIstance();
  }
}
