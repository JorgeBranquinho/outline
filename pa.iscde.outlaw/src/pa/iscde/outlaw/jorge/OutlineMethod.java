package pa.iscde.outlaw.jorge;

import org.eclipse.jdt.core.dom.Type;

public class OutlineMethod {

	private String name;
	private String visibility;
	private Type returnType;
	private String parent;
	private boolean isConstructor;
	
	public OutlineMethod(String name, Type type, boolean isConstructor) {
		setName(name);
		setReturnType(type);
		setConstructor(isConstructor);
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public Type getReturnType() {
		return returnType;
	}
	
	public void setReturnType(Type returnType) {
		this.returnType = returnType;
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

	public boolean isConstructor() {
		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}
	
}
