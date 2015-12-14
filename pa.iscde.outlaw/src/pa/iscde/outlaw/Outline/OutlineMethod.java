package pa.iscde.outlaw.Outline;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.swt.graphics.Image;

import pa.iscde.outlaw.Visibility;

public class OutlineMethod implements OutlineLookup {

	private String path = "C:\\Users\\Mr.Ivo\\git\\outline\\pa.iscde.outlaw\\images\\";
	private String name;
	private Visibility vis= Visibility.PACKAGE_PRIVATE;
	private Type returnType;
	private OutlineClass parent;
	private boolean isConstructor;
	private List<String> arguments = new ArrayList<String>();
	private boolean isStatic;
	private boolean isFinal;
	private boolean isSynchronized;
	private boolean isPublic;
	private boolean isProtected;
	private boolean isPrivate;
	private boolean isPckgprivate;
	private String imgName;
	private int argsNumber;
	private Image image;
	private IconMerger im= new IconMerger();
	private boolean flag=false;
	private String newPath="";


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

	@Override
	public OutlineClass getParent() {
		return parent;
	}

	void setParent(OutlineClass parent) {
		this.parent = parent;
		if(name.equals("main")){
			parent.setMainClass();
			parent.setImg();
		}
	}

	
	Type getReturnType() {
		return returnType;
	}

	void setReturnType(Type returnType) {
		this.returnType = returnType;
	}
	
	@Override
	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isConstructor() {
		return isConstructor;
	}

	void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	List<String> getArguments() {
		return arguments;
	}

	void setArguments(List<?> list) {
		if(!list.isEmpty()){
			for(Object str: list){
				arguments.add(str.toString().split(" ")[0]);
			}
		}
	}

	@Override
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


	@Override
	public boolean isStatic() {
		return isStatic;
	}

	void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Override
	public boolean isSynchronized() {
		return isSynchronized;
	}

	void setSynchronized(boolean isSynchronized) {
		this.isSynchronized = isSynchronized;
	}

	 void setImg() {
		int count=0;
		String[] result = new String[argsNumber];
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
		result[count]=imgName;
		count++;
		
		if(isFinal){
			result[count]="final_co.png";
			count++;
		}
		
		if(isStatic){
			result[count]="static_co.png";
			count++;
		}
		
		if(isSynchronized){
			result[count]="synch_co.png";
			count++;
		}
		
		if(isConstructor){
			result[count]="constr_ovr.png";
			count++;
		}
		
		if(!newPath.equals("")){
			result[0]=newPath;
			flag=true;
		}
		
		image=im.merge(result, 10, 0,flag);
		
	}

	 @Override
	 public void setImgPath(String newPath){
			this.newPath=newPath;
			setImg();
		}
	 
	@Override
	public void checkVisibility(int value) {
		if(Modifier.isPrivate(value)){
			setVisibility(Visibility.PRIVATE);
			setPrivate(true);
		}else if(Modifier.isProtected(value)){
			setVisibility(Visibility.PROTECTED);
			setProtected(true);
		}else if(Modifier.isPublic(value)){
			setVisibility(Visibility.PUBLIC);
			setPublic(true);
		}else{
			setVisibility(Visibility.PACKAGE_PRIVATE);
			setPckgprivate(true);
		}
		argsNumber++;
	}

	@Override
	public void checkProperties(int value){
		if(Modifier.isFinal(value)){
			setFinal(true);
			argsNumber++;
		}
		if(Modifier.isStatic(value)){
			setStatic(true);
			argsNumber++;
		}
		if(Modifier.isSynchronized(value)){
			setSynchronized(true);
			argsNumber++;
		}
		
		if(isConstructor){
			argsNumber++;
		}
	}

	void setVisibility(Visibility visibility) {
		vis=visibility;
	}

	@Override
	public Image getImg() {
		return image;
	}

	@Override
	public boolean isInterface() {
		return false;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isEnum() {
		return false;
	}

	@Override
	public boolean isInner() {
		return false;
	}

	@Override
	public boolean isAnon() {
		return false;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	void setPckgprivate(boolean isPckgprivate){
		this.isPckgprivate=isPckgprivate;
	}
	
	@Override
	public boolean isPckgprivate() {
		return isPckgprivate;
	}

	void setPrivate(boolean isPrivate){
		this.isPrivate=isPrivate;
	}
	
	@Override
	public boolean isPrivate() {
		return isPrivate;
	}

	void setProtected(boolean isProtected){
		this.isProtected=isProtected;
	}
	
	@Override
	public boolean isProtected() {
		return isProtected;
	}

	void setPublic(boolean isPublic){
		this.isPublic=isPublic;
	}
	
	@Override
	public boolean isPublic() {
		return isPublic;
	}

}
