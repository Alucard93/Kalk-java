package model.factory;
import model.factory.ColorFactory;
import model.factory.GenericFactory;
import model.color.Color;
public class Factory<T> implements GenericFactory{
  public Factory(String name)
  {
    ColorFactory.addColorFactory(name, this);
  }
  public Color create(){
    return new T.getNewIstance();
  }
}
