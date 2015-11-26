package pa.iscde.outlaw.jorge;

import pa.iscde.outlaw.Visibility;

public interface OutlineLookup {

	public String getName();
	
	public void setName(String name);
	
	public OutlineClass getParent();
	
	public void setParent(OutlineClass parent);
	
	public void checkVisibility(int value);
	
	public void checkProperties(int value);
	
	public void setVisibility(Visibility visibility);
	
	public String toString();
	
	public boolean isStatic();
	
	public void setStatic(boolean isStatic);
	
	public boolean isFinal();
	
	public void setFinal(boolean isFinal);
	
	public void setImg(String imgType);
	
	public String getImg();
	
}
