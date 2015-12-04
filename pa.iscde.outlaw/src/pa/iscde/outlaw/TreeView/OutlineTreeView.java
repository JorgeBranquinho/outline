package pa.iscde.outlaw.TreeView;

import java.io.File;
import java.util.Map;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineRoot;
import pa.iscde.outlaw.Outline.Visitor;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class OutlineTreeView {

	private OutlineRoot root;
	private TreeViewer tv;
	private JavaEditorServices services;

	public OutlineTreeView(Composite c, Visitor v, Map<String, Image> imageMap){

		root= new OutlineRoot();
		root.setClazz(v.getClazz());
		root.setPackagezz(v.getClazz().getPackagezz());
		tv = new TreeViewer(c, SWT.NONE);
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider(imageMap));

		tv.setInput(root);
		tv.expandAll();
		tv.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event){
				System.err.println("fds finalmente achei esta porra");
				System.err.println(event.getSelection());
				File f=services.getOpenedFile();
				if(f==null)return;
				System.out.println("FILE: " + f.getName());
				services.selectText(f, 859-122, 2);
			}
		});
	}

	public void update(OutlineClass clazz){
		root.setClazz(clazz);
		root.setPackagezz(clazz.getPackagezz());
		tv.setInput(root);
		tv.expandAll();

	}

	public OutlineTreeView(Composite c,Map<String, Image> imageMap ){
		root= new OutlineRoot();
		tv = new TreeViewer(c, SWT.NONE);
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider(imageMap));
	}

	public void clear() {
		tv.setInput(null);
	}

	public void setServices(JavaEditorServices services) {
		this.services=services;
	}
}
