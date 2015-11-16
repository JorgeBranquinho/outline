package pa.iscde.outlaw.jorge;

import java.lang.reflect.Modifier;
import org.eclipse.jdt.core.dom.Type;

public class OutlineField implements OutlineLookup{

	private String name;
	private String visibility;
	private Type type;
	private OutlineClass parent;
	private boolean isStatic;
	private boolean isFinal;
	private boolean isMethod;

	public OutlineField(String name, Type type, int modifiers, OutlineClass clazz) {
		setName(getFieldName(name));
		setType(type);
		setParent(clazz);
		checkVisibility(modifiers);
		checkProperties(modifiers);
	}

	private String getFieldName(String name) {
		return name.split(" ")[3];
	}

	public OutlineClass getParent() {
		return parent;
	}

	public void setParent(OutlineClass parent) {
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

	public String toString() {
		return getName()+":"+getType();
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	private void checkVisibility(int value){
		switch(value){
		case Modifier.PUBLIC: setVisibility("Public");break;
		case Modifier.PRIVATE: setVisibility("Private");break;
		case Modifier.PROTECTED: setVisibility("Protected");break;
		default: setVisibility("Package private");break;
		}
	}

	private void checkProperties(int value){
		if(Modifier.isFinal(value))
			setFinal(true);
		if(Modifier.isStatic(value))
			setStatic(true);
	}

	@Override
	public boolean isMethod() {
		return false;
	}

	@Override
	public void setMethod(boolean isMethod) {
		this.isMethod=isMethod;
	}

}
