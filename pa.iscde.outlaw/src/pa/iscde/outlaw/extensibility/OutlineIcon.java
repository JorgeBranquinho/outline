/**
 * 
 * @author Mr.Ivo
 *
 */
package pa.iscde.outlaw.extensibility;

import pa.iscde.outlaw.Outline.OutlineLookup;


public interface OutlineIcon {
	/**
	 * Description: By using this method there is no need to cast the object because,
	 * we will already know what type it is going to be (e.g. Class, Method, Field).
	 *  The Enum "OutlineType" has three choices, Class, Method and Field.
	 *  WARNING: This Method cannot return null. It must return one of the OutlineType types.
	 * @return The Object type that is going to have its icon changed.
	 */
	public OutlineType getType();
	/**
	 * Description: The user can check any type of visibility or property before applying the change.
	 *  WARNING: This Method cannot return null. It must return one of the OutlineType types.
	 * @param o Object of type OutlineLookup that can be used to do any additional checks. 
	 * @return The image name of the new image to be used.
	 */
	public String setIcon(OutlineLookup o);
	
}
