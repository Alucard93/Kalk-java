package model;
import java.util.Vector;
public interface Model
{
  Vector<String> availableOperations();
  Vector<String> allAvailableTypes();
  void setLeftType(String type);
  void setLeftValues(Vector<String> values);
  void setRightType(String type);
  void setRightValues(Vector<String> values);
  void setLastResultAsLeftOperand();
  void setOp(String eOperation);
  void execute();
  void getResult();
  Vector<String> getHistory();
}
