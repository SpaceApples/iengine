package iengine;


public class Variable {
	private String value;
	private boolean active;
	
	Variable (String var) {
		value = var;
		active = false;
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