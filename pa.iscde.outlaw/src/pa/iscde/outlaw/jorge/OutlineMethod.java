package pa.iscde.outlaw.jorge;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.Type;

import pa.iscde.outlaw.Visibility;

public class OutlineMethod implements OutlineLookup {

	private String name;
	private Visibility vis= Visibility.PACKAGE_PRIVATE;
	private Type returnType;
	private OutlineClass parent;
	private boolean isConstructor;
	private List<String> arguments = new ArrayList<String>();
	private boolean isStatic;
	private boolean isFinal;
	private boolean isSynchronized;
	private String imgName;

	public OutlineMethod(String name, Type type, boolean isConstructor, int modifiers, List<?> list, OutlineClass parentClass) {
		setName(name);
		setReturnType(type);
		setConstructor(isConstructor);
		checkVisibility(modifiers);
		checkProperties(modifiers);
		setArguments(list);
		setParent(parentClass);
		setImg();
	}

	public OutlineClass getParent() {
		return parent;
	}

	public void setParent(OutlineClass parent) {
		this.parent = parent;
		if(name.equals("main")){
			parent.setMainClass();
		}
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
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
			}
		}
	}

	public String toString() {
		String result=getName()+"(";
		for(int i=0;i<arguments.size();i++){
			result+= arguments.get(i);
			if(i!=arguments.size()-1)
				result+=", ";
		}
		result+=")";
		if(isConstructor())
			return result;
		return result+" : "+getReturnType();
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
	public void setImg() {
		switch(vis){
		case PACKAGE_PRIVATE:
			this.imgName="methdef_obj.png";
			break;
		case PRIVATE:
			this.imgName="method_private_obj.gif";
			break;
		case PROTECTED:
			this.imgName="method_protected_obj.gif";
			break;
		case PUBLIC:
			this.imgName="method_public_obj.gif";
			break;
			
		}
	}

	@Override
	public String getImg() {
		return imgName;
	}

	@Override
	public void checkVisibility(int value) {
		if(Modifier.isPrivate(value)){
			setVisibility(Visibility.PRIVATE);
		}else if(Modifier.isProtected(value)){
			setVisibility(Visibility.PROTECTED);
		}else if(Modifier.isPublic(value)){
			setVisibility(Visibility.PUBLIC);
		}else{
			setVisibility(Visibility.PACKAGE_PRIVATE);
		}
	}

	@Override
	public void checkProperties(int value){
		if(Modifier.isFinal(value))
			setFinal(true);
		if(Modifier.isStatic(value))
			setStatic(true);
		if(Modifier.isSynchronized(value))
			setSynchronized(true);
	}

	@Override
	public void setVisibility(Visibility visibility) {
		vis=visibility;
	}



}
