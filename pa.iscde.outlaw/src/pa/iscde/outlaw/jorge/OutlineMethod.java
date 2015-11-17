package pa.iscde.outlaw.jorge;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Type;
import org.eclipse.swt.graphics.Image;

public class OutlineMethod implements OutlineLookup {

	private String name;
	private String visibility;
	private Type returnType;
	private OutlineClass parent;
	private boolean isConstructor;
	private List<String> arguments = new ArrayList<String>();
	private boolean isStatic;
	private boolean isFinal;
	private boolean isSynchronized;
	private boolean isMethod;
	private String imgName;

	public OutlineMethod(String name, Type type, boolean isConstructor, int modifiers, List<?> list, OutlineClass parentClass) {
		setName(name);
		setReturnType(type);
		setConstructor(isConstructor);
		checkVisibility(modifiers);
		checkProperties(modifiers);
		setArguments(list);
		setParent(parentClass);
	}

	public OutlineClass getParent() {
		return parent;
	}

	public void setParent(OutlineClass parent) {
		this.parent = parent;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	private void checkVisibility(int value){
		
		if(Modifier.isPrivate(value)){
			setVisibility("Private");
			setImg("method_private_obj.gif");
		}else if(Modifier.isProtected(value)){
			setVisibility("Protected");
			setImg("method_protected_obj.gif");
		}else{
			setVisibility("Public");
			setImg("method_public_obj.gif");
		}
		/*
		switch(value){
		case Modifier.PUBLIC: 
			setVisibility("Public");
			setImg("method_public_obj.gif");
		break;
		case Modifier.PRIVATE:
			setVisibility("Private");
			setImg("method_private_obj.gif");
		break;
		case Modifier.PROTECTED: 
			setVisibility("Protected");
			setImg("method_protected_obj.gif");
		break;
		default: 
			System.out.println("DEFV:"+ value);
			setVisibility("Package private");
		break;
		}
		*/
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

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<?> list) {
		if(!list.isEmpty()){
			for(Object str: list){
				arguments.add(str.toString().split(" ")[0]);
				System.out.println(str.toString().split(" ")[0]);
			}
		}
	}

	public String toString() {
		return getName()+"("+arguments.toString().replaceAll("[\\[\\]]", "")+")"+" : "+getReturnType();
	}

	private void checkProperties(int value){
		if(Modifier.isFinal(value))
			setFinal(true);
		if(Modifier.isStatic(value))
			setStatic(true);
		if(Modifier.isSynchronized(value))
			setSynchronized(true);
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

	public boolean isSynchronized() {
		return isSynchronized;
	}

	public void setSynchronized(boolean isSynchronized) {
		this.isSynchronized = isSynchronized;
	}

	@Override
	public void setImg(String imgName) {
		this.imgName=imgName;		
	}

	@Override
	public String getImg() {
		// TODO Auto-generated method stub
		return imgName;
	}


}
