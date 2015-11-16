package pa.iscde.outlaw.jorge;

import java.util.ArrayList;

public class OutlineClass {

	private ArrayList<OutlineMethod> method = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	
	
	public ArrayList<OutlineMethod> getMethod() {
		return method;
	}
	
	public void setMethod(ArrayList<OutlineMethod> method) {
		this.method = method;
	}
	
	public ArrayList<OutlineField> getFields() {
		return fields;
	}
	
	public void setFields(ArrayList<OutlineField> fields) {
		this.fields = fields;
	}
	
}
