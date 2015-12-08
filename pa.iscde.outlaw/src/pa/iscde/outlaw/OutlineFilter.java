package pa.iscde.outlaw;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineField;
import pa.iscde.outlaw.Outline.OutlineMethod;

public interface OutlineFilter {

	public boolean showMethodFilter(OutlineMethod om);
	
	public boolean showFieldFilter(OutlineField of);
	
	public boolean showClassFilter(OutlineClass oc);
}
