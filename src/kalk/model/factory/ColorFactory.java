package kalk.model.factory;

import java.util.Map;
import java.util.Vector;
import java.util.HashMap;
import java.util.Arrays;
import model.IllegalColorException;
import model.factory.GenericFactory;
import model.color.Color;

public class ColorFactory
{
  private static Map<String, GenericFactory> allColorFactories = new HashMap<String,GenericFactory>();

  public static void addColorFactory(String name,GenericFactory factory)
  {
    allColorFactories.putIfAbsent(name,factory);
  }

  public static Vector<String> getAllColorTypes()
  {
    return new Vector<String>(allColorFactories.keySet());
  }

  public static Color getNewColor(String key)
  {
    return allColorFactories.get(key).create();
  }

  public static Color Execution(Color left, int operation, Color right) throws IllegalColorException
  {
    Color result=null;
    switch (operation)
    {
      case 1:
        result = left.mix(right);
        break;
    }
    return result;
  }

  public static Color Execution(Color left, int operation) throws IllegalColorException
  {
    Color result=null;
    switch(operation)
    {
      case 0: result=left.negate();
      break;
    }
    return result;
  }
  public static Color Execution(Color left, int operation, int right) throws IllegalColorException
  {
    Color result=null;
    switch(operation)
    {
      case 2: result = left.division(right);
        break;
    }
    return result;
  }

  public static Vector<String> availableOperations()
  {
    Vector<String> toReturn = new Vector<String>();
    for(int i=0; i<3;i++)
    {
      toReturn.add(Color.allOpts[i][0]);
    }
      return toReturn;
  }

  public static Vector<String> permittedOperations(String type)
  {
      Color toTest = getNewColor(type);
      return toTest.availableOperations();
  }

  public static Vector<String> typeByOperation(int operation)
  {
    Vector<String> allColor = getAllColorTypes();
    Vector<String> toReturn = new Vector<String>(allColor.size()+1);
    toReturn.add("Select type");
    if(operation!=-1)
    {
      for(int i=1;i<3&&(!Color.allOpts[operation][i].isEmpty());i++)
      {
            if(Color.allOpts[operation][i]=="color")
                toReturn.addAll(allColor);
            else
                toReturn.add(Color.allOpts[operation][i]);
      }
    }else
    {
      toReturn.addAll(allColor);
    }
    return toReturn;
  }
}
