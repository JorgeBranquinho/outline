package pa.iscde.outlaw.Outline;

import java.lang.reflect.Modifier;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.swt.graphics.Image;

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
	private IconMerger im = new IconMerger();
	private Image image;
	private int argsNumber=0;
	
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
		
		argsNumber++;
	}

	@Override
	public void checkProperties(int value){
		if(Modifier.isFinal(value)){
			setFinal(true);//1xx ou 0xx
			argsNumber++;
		}
		if(Modifier.isStatic(value)){
			setStatic(true);//1xxx ou 0xxx
			argsNumber++;
		}
	}

	@Override
	public void setImg() {
		int count=0;
		//System.out.println("Args Num: "+argsNumber);
		String[] result = new String[argsNumber];
		if(isConstant){
			this.imgName="constant_co.gif";
			result[count]=imgName;
			count++;
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
			result[count]=imgName;
			count++;
			
			if(isFinal){
				result[count]="final_co.png";
				count++;
			}
			
			if(isStatic){
				result[count]="static_co.png";
			}
		}
		image=im.merge(result, 10, 0);
			
	}

//	@Override
//	public String getImg() {
//		return imgName;
//	}

	public boolean isConstant() {
		return isConstant;
	}

	public void setConstant(boolean isConstant) {
		this.isConstant = isConstant;
		if(isConstant)
			argsNumber++;
	}

	@Override
	public void setVisibility(Visibility visibility) {
		// TODO Auto-generated method stub
		vis=visibility;
		
	}

	@Override
	public Image getImg() {
		// TODO Auto-generated method stub
		return image;
	}


}
