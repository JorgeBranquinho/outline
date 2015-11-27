package pa.iscde.outlaw.Outline;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import pa.iscde.outlaw.Visibility;

public class OutlineClass implements OutlineLookup{

	private ArrayList<OutlineMethod> method = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	private ArrayList<OutlineClass> children_classes = new ArrayList<OutlineClass>();
	private String name;
	private OutlineClass parent;
	private Visibility vis;
	private boolean isStatic;
	private boolean isFinal;
	private String imgType="class_obj.gif";
	private boolean isInterface;
	private boolean isAbstract;
	private String packagezz;
	private boolean isEnum;
	private boolean isMainClass;
	private boolean isInner;
	private boolean isAnon;
	
	
	public OutlineClass(String parentClass, String packagezz) {
		setName(parentClass);
		setPackagezz(packagezz);
	}
	
	public OutlineClass(String parentClass, String packagezz, boolean isInner, boolean isAnon) {
		setName(parentClass);
		setPackagezz(packagezz);
		setInner(isInner);
		setAnon(isAnon);
	}

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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name.replace(".java", "");
	}

	@Override
	public OutlineClass getParent() {
		return parent;
	}

	@Override
	public void setParent(OutlineClass parent) {
		this.parent=parent;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	@Override
	public void setStatic(boolean isStatic) {
		this.isStatic=isStatic;
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	@Override
	public void setFinal(boolean isFinal) {
		this.isFinal=isFinal;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void setImg() {
		if(isEnum){
			this.imgType="enum_obj.gif";
		}
		else if(isInterface){
			this.imgType="int_obj.gif";
		}else{
			switch(vis){
			case PACKAGE_PRIVATE:
				this.imgType="class_default_obj.png";
				break;
			case PRIVATE:
				this.imgType="innerclass_private_obj.png";
				break;
			case PROTECTED:
				this.imgType="innerclass_protected_obj.png";
				break;
			case PUBLIC:
				this.imgType="class_obj.gif";
				break;
				
			}
		}
	}

	@Override
	public String getImg() {
		return imgType;
	}

	public boolean isInterface() {

		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
		//if(isInterface){
		//	setImg("int_obj.gif");
		//}
	}

	public String getPackagezz() {
		return packagezz;
	}

	public void setPackagezz(String packagezz) {
		this.packagezz = packagezz;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
		//if(isEnum)
		//	setImg("enum_obj.gif");
	}

	
	public void setMainClass(){
		isMainClass=true;
	}
	
	public boolean isMainClass(){
		return isMainClass;
	}

	@Override
	public void checkVisibility(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkProperties(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisibility(Visibility visibility) {
		this.vis=visibility;		
	}

	public boolean isInner() {
		return isInner;
	}

	public void setInner(boolean isInner) {
		this.isInner = isInner;
	}

	public boolean isAnon() {
		return isAnon;
	}

	public void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}

	public ArrayList<OutlineClass> getChildren_classes() {
		return children_classes;
	}

	public void setChildren_classes(ArrayList<OutlineClass> children_classes) {
		this.children_classes = children_classes;
	}
	
	
}
