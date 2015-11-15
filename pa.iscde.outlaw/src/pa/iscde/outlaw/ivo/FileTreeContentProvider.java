package pa.iscde.outlaw.ivo;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import pa.iscde.outlaw.jorge.OutlineField;
import pa.iscde.outlaw.jorge.OutlineMethod;
import pa.iscde.outlaw.jorge.Visitor;


//FALTA ACABAR
public class FileTreeContentProvider implements ITreeContentProvider  {

	private static final Object[] EMPTY_ARRAY = new Object[0];
	

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
		/*if (arg0 instanceof OutlineField) {
			System.out.println("FTC:"+arg0.toString());
		} else if (arg0 instanceof OutlineMethod) {
			System.out.println("FTC:"+arg0.toString());
		}*/
		System.out.println("GETCHILDREN");
		if(arg0 instanceof Object){
			 return ((ArrayList<Object>) arg0).toArray();
		}
		return EMPTY_ARRAY;
		
		/*Object[] clazz = null;
		
		for(int i=0;i<fields.size();i++){
			clazz[i]=fields.get(i);
		}
		for(int j=0;j<methods.size();j++){
			clazz[fields.size()+j]=methods.get(j);
		}
		
		return clazz;*/
	}

	@Override
	public Object[] getElements(Object arg0) {
		
		System.out.println("getElements");
		if(arg0 instanceof Object){
			System.out.println("obj");
			 return ((ArrayList<Object>) arg0).toArray();
		}
		return EMPTY_ARRAY;
		
//		Object[] clazz= new Object[fields.size()+methods.size()];
//		
//		for(int i=0;i<fields.size();i++){
//			clazz[i]=fields.get(i);
//		}
//		for(int j=0;j<methods.size();j++){
//			clazz[fields.size()+j]=methods.get(j);
//		}
//		
//		return clazz;
	}

	@Override
	public Object getParent(Object arg0) {
		// TODO Auto-generated method stub
		System.out.println("getParent");
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO Auto-generated method stub
		System.out.println("hasChildren");
		if(arg0 instanceof OutlineField || arg0 instanceof OutlineMethod){
			System.out.println("tr");
			return true;
		}
		
		return false;
	}

}
