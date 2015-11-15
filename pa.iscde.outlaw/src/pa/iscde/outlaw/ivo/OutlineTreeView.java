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
	
	public OutlineTreeView(Composite c, Visitor v){
		// Add a checkbox to toggle whether the labels preserve case

		this.c=c;
		this.v=v;
		fields=v.getFields();
		methods=v.getMethods();
		final TreeViewer tv = new TreeViewer(c);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new FileTreeContentProvider(fields,methods));
		tv.setLabelProvider(new FileTreeLabelProvider(fields,methods));

		tv.setInput("root");
		
		
	}	
}
