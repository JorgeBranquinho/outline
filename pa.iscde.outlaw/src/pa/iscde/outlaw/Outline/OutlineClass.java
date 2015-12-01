package pa.iscde.outlaw.Outline;

import java.awt.image.BufferedImage;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Image;

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
	private String imgName="class_obj.gif";
	private boolean isInterface;
	private boolean isAbstract;
	private String packagezz;
	private boolean isEnum;
	private boolean isMainClass;
	private boolean isInner;
	private boolean isAnon;
	private int argsNumber;
	private Image image;
	private IconMerger im= new IconMerger();
	
	
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
		int count=0;
		//System.out.println("Args Num: "+argsNumber);
		String[] result = new String[argsNumber];
		if(isEnum){
			this.imgName="enum_obj.gif";
			result[count]=imgName;
			count++;
		}
		else if(isInterface){
			this.imgName="int_obj.gif";
			result[count]=imgName;
			count++;
		}else{
			switch(vis){
			case PACKAGE_PRIVATE:
				this.imgName="class_default_obj.png";
				break;
			case PRIVATE:
				this.imgName="innerclass_private_obj.png";
				break;
			case PROTECTED:
				this.imgName="innerclass_protected_obj.png";
				break;
			case PUBLIC:
				this.imgName="class_obj.gif";
				break;
				
			}
			result[count]=imgName;
			count++;
		}
		
		image=im.merge(result);
	}

//	@Override
//	public String getImg() {
//		return imgType;
//	}

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
		if(Modifier.isPrivate(value)){
			setVisibility(Visibility.PRIVATE);
		}else if(Modifier.isProtected(value)){
			setVisibility(Visibility.PROTECTED);
		}else if(Modifier.isPublic(value)){
			setVisibility(Visibility.PUBLIC);
		}else{
			setVisibility(Visibility.PACKAGE_PRIVATE);
		}
		argsNumber++;
	}

	@Override
	public void checkProperties(int value) {
		if(Modifier.isFinal(value)){
			setFinal(true);
			argsNumber++;
		}
		if(Modifier.isStatic(value)){
			setStatic(true);
			argsNumber++;
		}
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

	@Override
	public Image getImg() {
		// TODO Auto-generated method stub
		return image;
	}
	
	
}
