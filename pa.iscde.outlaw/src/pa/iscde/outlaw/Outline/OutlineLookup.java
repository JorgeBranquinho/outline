package pa.iscde.outlaw.Outline;

import org.eclipse.swt.graphics.Image;

public interface OutlineLookup {

	public String getName();
	
	//public void setName(String name);
	
	public OutlineClass getParent();
	
	//public void setParent(OutlineClass parent);
	
	public void checkVisibility(int value);
	
	public void checkProperties(int value);
	
	//public void setVisibility(Visibility visibility);
	
	public String toString();
	
	public boolean isStatic();
	
	//public void setStatic(boolean isStatic);
	
	public boolean isFinal();
	
	public boolean isInterface();
	
	public boolean isAbstract();
	
	public boolean isEnum();
	
	public boolean isInner();
	
	public boolean isAnon();
	
	public boolean isConstant();
	
	public boolean isConstructor();
	
	public boolean isSynchronized();
	
	//public void setFinal(boolean isFinal);
	
	//public void setImg();
	
	public Image getImg();
	
}
