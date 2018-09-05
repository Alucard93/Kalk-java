package kalk.model;
import java.util.Vector;
public interface Model
{

  Vector<String> availableOperations();
  Vector<String> allAvailableTypes();
  int setLeftType(String type);
  void setLeftValues(Vector<String> values)throws IllegalColorException;
  int setRightType(String type);
  void setRightValues(Vector<String> values)throws IllegalColorException;
  void setOp(String eOperation);
  void execute() throws IllegalColorException;
  Vector<String> getResult();
  Vector<String> getHistory();
}
