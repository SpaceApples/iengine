package iengine;


public class Variable{
	private String value;
	private boolean active = false;
	
	Variable (String var){
		value = var;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setActive(boolean _active) {
		active = _active;
	}
	
	public Boolean getActive() {
		return active;
	}
}