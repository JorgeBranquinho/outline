package pa.iscde.outlaw.Outline;

import org.eclipse.swt.graphics.Image;

public interface OutlineLookup {

	public String getName();
	
	public OutlineClass getParent();
	
	public void checkVisibility(int value);
	
	public void checkProperties(int value);
	
	public String toString();
	
	public boolean isStatic();
	
	public boolean isFinal();
	
	public boolean isInterface();
	
	public boolean isAbstract();
	
	public boolean isEnum();
	
	public boolean isInner();
	
	public boolean isAnon();
	
	public boolean isConstant();
	
	public boolean isConstructor();
	
	public boolean isSynchronized();
	
	public boolean isPckgprivate();
	
	public boolean isPrivate();
	
	public boolean isProtected();
	
	public boolean isPublic();
	
	public Image getImg();
	
	public void setImgPath(String path);
	
}
