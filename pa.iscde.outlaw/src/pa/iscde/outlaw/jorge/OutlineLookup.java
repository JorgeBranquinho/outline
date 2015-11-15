package pa.iscde.outlaw.jorge;

public interface OutlineLookup {

	public String getName();
	
	public void setName(String name);
	
	public String getParent();
	
	public void setParent(String parent);
	
	public String getVisibility();
	
	public void setVisibility(String visibility);
	
	public String toString();
	
	public boolean isStatic();
	
	public void setStatic(boolean isStatic);
	
	public boolean isFinal();
	
	public void setFinal(boolean isFinal);
	
}
