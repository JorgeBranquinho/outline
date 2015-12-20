/**
 * 
 * @author Jorge Branquinho
 *
 */
package pa.iscde.outlaw;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineField;
import pa.iscde.outlaw.Outline.OutlineMethod;

public interface OutlineFilter {

	/**
	 * This method allows filtering which OutlineMethods will be represented in the outline, 
	 * by applying all sorts of test to the parameter (e.g. return type, arguments, etc)
	 * @param om - the OutlineMethod to be tested.
	 * @return a boolean value: true if the parameter should be represented in the outline 
	 * and false if it shouldn't.
	 */
	public boolean showMethodFilter(OutlineMethod om);
	
	/**
	 * This method allows filtering which OutlineField will be represented in the outline, 
	 * by applying all sorts of test to the parameter (e.g. type, visibility, etc)
	 * @param om - the OutlineField to be tested.
	 * @return a boolean value: true if the parameter should be represented in the outline 
	 * and false if it shouldn't.
	 */
	public boolean showFieldFilter(OutlineField of);
	
	/**
	 * This method allows filtering which OutlineClass will be represented in the outline, 
	 * by applying all sorts of test to the parameter (e.g. visibility, check if is Enum, etc)
	 * @param om - the OutlineClass to be tested.
	 * @return a boolean value: true if the parameter should be represented in the outline 
	 * and false if it shouldn't.
	 */
	public boolean showClassFilter(OutlineClass oc);
}
