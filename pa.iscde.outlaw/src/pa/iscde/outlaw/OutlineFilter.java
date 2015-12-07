package pa.iscde.outlaw;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineField;
import pa.iscde.outlaw.Outline.OutlineMethod;

public interface OutlineFilter {

	public OutlineMethod showMethodFilter();
	
	public OutlineField showFieldFilter();
	
	public OutlineClass showClassFilter();
}
