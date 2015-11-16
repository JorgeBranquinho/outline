package pa.iscde.outlaw.ivo;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

import pa.iscde.outlaw.jorge.OutlineField;
import pa.iscde.outlaw.jorge.OutlineMethod;

//FALTA ACABAR
public class FileTreeLabelProvider implements ILabelProvider {


	@Override
	public Image getImage(Object arg0) {//16x16
		// TODO Auto-generated method stub
		Image x= new Image(null, "C:\\Users\\Asus\\git\\outline\\pa.iscde.outlaw\\images\\smiley.png");
		return null;
	}

	@Override
	public String getText(Object arg0) {
		
		if (arg0 instanceof OutlineField) {
			System.out.println("CALL ME:"+arg0.toString());
		       return ((OutlineField) arg0).toString();
		} else if (arg0 instanceof OutlineMethod) {
			System.out.println("CALL ME:"+arg0.toString());
		       return ((OutlineMethod) arg0).toString();
		}
		
		return "OI";
	}

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
