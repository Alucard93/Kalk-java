package kalk.model;
public class IllegalColorException extends Exception
{
  private String what;
  public IllegalColorException(String what){
    this.what=what;
  }
  public String what(){
    return what;
  }
}
