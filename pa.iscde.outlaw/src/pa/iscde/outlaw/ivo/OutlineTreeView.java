package pa.iscde.outlaw.ivo;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.outlaw.jorge.OutlineField;
import pa.iscde.outlaw.jorge.OutlineMethod;
import pa.iscde.outlaw.jorge.Visitor;

//FALTA ACABAR
public class OutlineTreeView {

	private Composite c;
	private Visitor v;
	private ArrayList<OutlineField> fields;
	private ArrayList<OutlineMethod> methods;
	private ArrayList<Object> clazz= new ArrayList<Object>();
	//private String tst= "test";
	public OutlineTreeView(Composite c, Visitor v){

		this.c=c;
		this.v=v;
		fields=v.getFields();
		methods=v.getMethods();
		
		clazz.addAll(fields);
		clazz.addAll(methods);
		
//		for(Object o: v.getClazz().getFields()){
//			System.out.println("|||+"+o.toString());
//		}
//		for(Object o: v.getClazz().getMethod()){
//			System.out.println("|||-"+o.toString());
//		}
		
		TreeViewer tv = new TreeViewer(c);
		//tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider());

		tv.setInput(v.getClazz());
		tv.expandAll();
		
		
	}	
}
