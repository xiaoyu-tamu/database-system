package simpledb.file;


/**
 * Version 2, I use Java Boolean wrapper to represent boolean type in SimpleDB.
 * 
 * Note: there is another file named BOOLEAN1 under the same directory, 
 * 		 which implement similar functionality using string.
 * 
 * @author Michael
 *
 */
public class BOOLEAN2 {
	
	private Boolean value;
	
	public BOOLEAN2() {
		value = null;
	}
	
	public BOOLEAN2(String val) {
		if(val.toLowerCase().equals("t")) {
			value = true;
		} else if(val.toLowerCase().equals("f")){
			value = false;
		} else {
			value = null;
		}
	}
	
	public void setValue(String[] val) {
		if(val[0].toLowerCase() == "t"){
			value = true;
		}else if(val[0].toLowerCase() == "f") {
			value = false;
		}else {
			value = null;
		}
	}
	
	public Boolean getValue() {
		return value;
	}
	
	public boolean isBoolean(Object obj) {
		BOOLEAN2 bool = (BOOLEAN2) obj;
		if(bool.getValue() != null) {
			return true;
		} else {
			return false;
		}
	}
}
