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

	private ArrayList<OutlineField> fields;
	private ArrayList<OutlineMethod> methods;

	public FileTreeLabelProvider(ArrayList<OutlineField> fields, ArrayList<OutlineMethod> methods) {
		this.fields=fields;
		this.methods=methods;
	}

	@Override
	public Image getImage(Object arg0) {
		// TODO Auto-generated method stub
		Image x= new Image(null, "images\\smiley.png");
		return x;
	}

	@Override
	public String getText(Object arg0) {
		// TODO Auto-generated method stub
		return "teste";
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
