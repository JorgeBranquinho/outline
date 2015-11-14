package pa.iscde.outlaw.ivo;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

//FALTA ACABAR
public class OutlineTreeView {

	public OutlineTreeView(Composite c){
		TreeViewer tv = new TreeViewer(c,SWT.NONE);
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider());

		tv.setInput("root");
	}	
}
