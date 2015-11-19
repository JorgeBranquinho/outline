package pa.iscde.outlaw.ivo;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.outlaw.jorge.OutlineClass;
import pa.iscde.outlaw.jorge.OutlineField;
import pa.iscde.outlaw.jorge.OutlineMethod;
import pa.iscde.outlaw.jorge.OutlineRoot;
import pa.iscde.outlaw.jorge.Visitor;

//FALTA ACABAR
public class OutlineTreeView {

	private OutlineRoot root;
	private TreeViewer tv;
	
	public OutlineTreeView(Composite c, Visitor v, Map<String, Image> imageMap){

		root= new OutlineRoot();
		root.setClazz(v.getClazz());
		
		tv = new TreeViewer(c, SWT.NONE);
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider(imageMap));
		
		tv.setInput(root);
		tv.expandAll();
	}
	
	public void update(OutlineClass clazz){
		root.setClazz(clazz);
		tv.setInput(root);
		tv.expandAll();
	}
}
