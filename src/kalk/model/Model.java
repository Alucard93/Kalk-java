package kalk.model;
import java.util.Vector;
public interface Model
{

  Vector<String> availableOperations();
  Vector<String> allAvailableTypes();
  void setLeftType(String type);
  void setLeftValues(Vector<String> values)throws IllegalColorException;
  void setRightType(String type);
  void setRightValues(Vector<String> values)throws IllegalColorException;
  void setLastResultAsLeftOperand();
  void setOp(String eOperation);
  void execute() throws IllegalColorException;
  void getResult();
  Vector<String> getHistory();
}
