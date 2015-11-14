package pa.iscde.outlaw.jorge;

import org.eclipse.jdt.core.dom.Type;

public class OutlineField implements OutlineLookup{

	private String name;
	private String visibility;
	private Type type;
	private String parent;
	
	public OutlineField(String name, Type type) {
		setName(name);
		setType(type);
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getOutline() {
		return getName()+":"+getType();
	}
	
	
}
