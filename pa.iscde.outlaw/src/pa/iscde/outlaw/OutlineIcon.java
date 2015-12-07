package pa.iscde.outlaw;

import pa.iscde.outlaw.Outline.OutlineLookup;

public interface OutlineIcon {

	public String setImgPath(String path);
	
	public OutlineType getType();
	
	public boolean isVisible(OutlineLookup o);
	
}
