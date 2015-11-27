package pa.iscde.outlaw.jorge;

import java.lang.reflect.Modifier;
import org.eclipse.jdt.core.dom.Type;

import pa.iscde.outlaw.Visibility;

public class OutlineField implements OutlineLookup{

	private String name;
	private Visibility vis= Visibility.PACKAGE_PRIVATE;
	private Type type;
	private OutlineClass parent;
	private boolean isStatic;
	private boolean isFinal;
	private String imgName="";
	private boolean isConstant;
	
	public OutlineField(String name, Type type, int modifiers, OutlineClass clazz) {
		setName(getFieldName(name));
		setType(type);
		setParent(clazz);
		checkVisibility(modifiers);
		checkProperties(modifiers);
		setConstant(false);
		setImg();
	}
	
	public OutlineField(String name, OutlineClass clazz){
		setName(name);
		setType(null);
		setParent(clazz);
		setStatic(true);
		setFinal(true);
		setConstant(true);
		setImg();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		if(isConstant)
			return getName();
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

	@Override
	public void checkVisibility(int value){
		
		if(Modifier.isProtected(value)){
			setVisibility(Visibility.PROTECTED);//00
		}else if(Modifier.isPrivate(value)){
			setVisibility(Visibility.PRIVATE);//01
		}else if(Modifier.isPublic(value)){
			setVisibility(Visibility.PUBLIC);//10
		}else{
			setVisibility(Visibility.PACKAGE_PRIVATE);//11
		}
	}

	@Override
	public void checkProperties(int value){
		if(Modifier.isFinal(value)){
			setFinal(true);//1xx ou 0xx
		}
		if(Modifier.isStatic(value)){
			setStatic(true);//1xxx ou 0xxx
		}
	}

	@Override
	public void setImg() {
		if(isConstant){
			this.imgName="constant_co.gif";
		}else{
			switch(vis){
			case PACKAGE_PRIVATE:
				this.imgName="field_default_obj.png";
				break;
			case PRIVATE:
				this.imgName="field_private_obj.gif";
				break;
			case PROTECTED:
				this.imgName="field_protected_obj.gif";
				break;
			case PUBLIC:
				this.imgName="field_public_obj.gif";
				break;
			}
		}
		
		
	}

	@Override
	public String getImg() {
		return imgName;
	}

	public boolean isConstant() {
		return isConstant;
	}

	public void setConstant(boolean isConstant) {
		this.isConstant = isConstant;
		//if(isConstant)
			//setImg("constant_co.gif");
	}

	@Override
	public void setVisibility(Visibility visibility) {
		// TODO Auto-generated method stub
		vis=visibility;
		
	}


}
