package pa.iscde.outlaw.ivo;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import pa.iscde.outlaw.jorge.OutlineField;
import pa.iscde.outlaw.jorge.OutlineMethod;
import pa.iscde.outlaw.jorge.Visitor;


//FALTA ACABAR
public class FileTreeContentProvider implements ITreeContentProvider  {

	private ArrayList<OutlineField> fields;
	private ArrayList<OutlineMethod> methods;
	
	public FileTreeContentProvider(ArrayList<OutlineField> fields, ArrayList<OutlineMethod> methods) {
		this.fields=fields;
		this.methods=methods;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getChildren(Object arg0) {
		Object[] clazz = null;
		
		for(int i=0;i<fields.size();i++){
			clazz[i]=fields.get(i);
		}
		for(int j=0;j<methods.size();j++){
			clazz[fields.size()+j]=methods.get(j);
		}
		
		return clazz;
	}

	@Override
	public Object[] getElements(Object arg0) {
		Object[] clazz= new Object[fields.size()+methods.size()];
		
		for(int i=0;i<fields.size();i++){
			clazz[i]=fields.get(i);
		}
		for(int j=0;j<methods.size();j++){
			clazz[fields.size()+j]=methods.get(j);
		}
		
		return clazz;
	}

	@Override
	public Object getParent(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO Auto-generated method stub
		if(fields.size()!=0 || methods.size()!=0){
			return true;
		}
		
		return false;
	}

}
