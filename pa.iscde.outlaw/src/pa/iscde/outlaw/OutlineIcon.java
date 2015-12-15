package pa.iscde.outlaw;

import pa.iscde.outlaw.Outline.OutlineLookup;

public interface OutlineIcon {

	public OutlineType getType();
	
	public boolean isVisible(OutlineLookup o);
	
}
