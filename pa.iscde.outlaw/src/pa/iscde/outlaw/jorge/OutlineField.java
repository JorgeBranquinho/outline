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
	private String imgName="";
	
	public OutlineField(String name, Type type, int modifiers, OutlineClass clazz) {
		setName(getFieldName(name));
		setType(type);
		setParent(clazz);
		checkVisibility(modifiers);
		checkProperties(modifiers);
	}

	private String getFieldName(String name) {
		int argNumber=(name.length() - name.replaceAll(" ", "").length());
		return name.split(" ")[argNumber];
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
		return getName()+" : "+getType();
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
		
		if(Modifier.isProtected(value)){
			setVisibility("Protected");
			setImg("field_protected_obj.gif");
		}else if(Modifier.isPrivate(value)){
			setVisibility("Private");
			setImg("field_private_obj.gif");
		}else{
			setVisibility("Public");
			setImg("field_public_obj.gif");
		}
	}

	private void checkProperties(int value){
		if(Modifier.isFinal(value))
			setFinal(true);
		if(Modifier.isStatic(value))
			setStatic(true);
	}

	@Override
	public void setImg(String imgName) {
		this.imgName=imgName;
	}

	@Override
	public String getImg() {
		return imgName;
	}


}
