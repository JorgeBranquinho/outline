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
	 *  we will already know what type it is going to be (e.g. Class, Method, Field).
	 * @return The Object type that is going to have its icon changed, using the Enum "OutlineType"
	 */
	public OutlineType getType();
	/**
	 * Description: The user can check any type of visibility or property before applying the change.
	 * Important: The only way to change the icon is by calling the setImgPath("file_name"),
	 * where file_name is the name of the image.
	 * @param o Object of type OutlineLookup that can be used to do any additional checks, or to just change the icon. 
	 * @return boolean
	 */
	public boolean setIcon(OutlineLookup o);
	
}
