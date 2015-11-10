package pa.iscde.outlaw.jorge;

import java.util.List;

public interface OutlineLookup {

	public String toString();
	
	public List<String> getChild();
	
	public List<String> getVisibility();
	
	public List<String> getType();
	
}