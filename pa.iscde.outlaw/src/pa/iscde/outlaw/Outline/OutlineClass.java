package pa.iscde.outlaw.Outline;

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
	private boolean isPckgprivate;
	private boolean isPrivate;
	private boolean isProtected;
	private boolean isPublic;
	private int argsNumber;
	private Image image;
	private boolean flag=false;
	private String newPath="";
	private int modifiers;

	
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

	public ArrayList<OutlineMethod> getMethods() {
		return method;
	}
	
	void setMethod(ArrayList<OutlineMethod> method) {
		this.method = method;
	}
	
	public ArrayList<OutlineField> getFields() {
		return fields;
	}
	
	void setFields(ArrayList<OutlineField> fields) {
		this.fields = fields;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name.replace(".java", "");
	}

	@Override
	public OutlineClass getParent() {
		return parent;
	}

	void setParent(OutlineClass parent) {
		this.parent=parent;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	void setStatic(boolean isStatic) {
		this.isStatic=isStatic;
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	void setFinal(boolean isFinal) {
		this.isFinal=isFinal;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	void setImg() {
		int count=0;
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
		if(isMainClass){
			result[count]="run_co.png";
			count++;
		}
		
		if(!newPath.equals("")){
			result[0]=newPath;
			flag=true;
		}
		image=new IconMerger().merge(result, 10, 0,flag);
	}

	public void setImgPath(String newPath){
		this.newPath=newPath;
		setImg();
	}
	
	@Override
	public boolean isInterface() {

		return isInterface;
	}

	void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public String getPackagezz() {
		return packagezz;
	}

	void setPackagezz(String packagezz) {
		this.packagezz = packagezz;
	}

	@Override
	public boolean isAbstract() {
		return isAbstract;
	}

	void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	@Override
	public boolean isEnum() {
		return isEnum;
	}

	void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}

	
	void setMainClass(){
		isMainClass=true;
		argsNumber++;
	}
	
	boolean isMainClass(){
		return isMainClass;
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
		if(Modifier.isAbstract(value)){
			setAbstract(true);
			argsNumber++;
		}
	}

	void setVisibility(Visibility visibility) {
		this.vis=visibility;
		argsNumber++;
	}

	@Override
	public boolean isInner() {
		return isInner;
	}

	void setInner(boolean isInner) {
		this.isInner = isInner;
	}

	@Override
	public boolean isAnon() {
		return isAnon;
	}

	void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}

	public ArrayList<OutlineClass> getChildren_classes() {
		return children_classes;
	}

	void setChildren_classes(ArrayList<OutlineClass> children_classes) {
		this.children_classes = children_classes;
	}

	@Override
	public Image getImg() {
		return image;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isConstructor() {
		return false;
	}

	@Override
	public boolean isSynchronized() {
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

	void setModifiers(int modifiers) {
		this.modifiers=modifiers;
	}

	void performChecks() {
		if(modifiers!=0){
			checkProperties(modifiers);
			checkVisibility(modifiers);
		}
		else{
			setVisibility(Visibility.PUBLIC);
			setPublic(true);
		}
		setImg();		
	}
	
}
