package pa.iscde.outlaw.TreeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineField;
import pa.iscde.outlaw.Outline.OutlineMethod;
import pa.iscde.outlaw.Outline.OutlineRoot;
import pa.iscde.outlaw.Outline.Visitor;
import pa.iscde.outlaw.extensibility.OutlineFilter;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class OutlineTreeView {

	private OutlineRoot root;
	private TreeViewer tv;

	public OutlineTreeView(Composite c, Visitor v, Map<String, Image> imageMap, final JavaEditorServices services){
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
				File f=services.getOpenedFile();
				if(f==null)return;
				//				services.selectText(f, 859-122, 2);
			}
		});
	}

	public void update(OutlineClass clazz){
		root.setClazz(clazz);
		root.setPackagezz(clazz.getPackagezz());
		tv.setInput(root);
		tv.expandAll();

	}

	public OutlineTreeView(Composite c,Map<String, Image> imageMap, JavaEditorServices services ){
		root= new OutlineRoot();
		tv = new TreeViewer(c, SWT.NONE);
		tv.setContentProvider(new FileTreeContentProvider());
		tv.setLabelProvider(new FileTreeLabelProvider(imageMap));
	}

	public void clear() {
		tv.setInput(null);
	}

	public void setNewFilter(ArrayList<OutlineFilter> activefilters) {
		boolean toRemove;
		testClassFilter(activefilters, root.getClazz());
		ArrayList<OutlineClass> classesToRemove = new ArrayList<OutlineClass>();
		for(OutlineClass childclass: root.getClazz().getChildren_classes()){
			toRemove=false;
			for(OutlineFilter filter: activefilters)
				if(!filter.showClassFilter(childclass))
					toRemove=true;
			if(toRemove)
				classesToRemove.add(childclass);
		}
		root.getClazz().getChildren_classes().removeAll(classesToRemove);
		ArrayList<OutlineField> fieldsToRemove = new ArrayList<OutlineField>();
		for(OutlineField childfield: root.getClazz().getFields()){
			toRemove=false;
			for(OutlineFilter filter: activefilters)
				if(!filter.showFieldFilter(childfield))
					toRemove=true;
			if(toRemove)
				fieldsToRemove.add(childfield);
		}
		root.getClazz().getFields().removeAll(fieldsToRemove);
		ArrayList<OutlineMethod> methodsToRemove = new ArrayList<OutlineMethod>();
		for(OutlineMethod childmethod: root.getClazz().getMethods()){
			toRemove=false;
			for(OutlineFilter filter: activefilters)
				if(!filter.showMethodFilter(childmethod))
					toRemove=true;
			if(toRemove)
				methodsToRemove.add(childmethod);
		}
		root.getClazz().getMethods().removeAll(methodsToRemove);
		toRemove=false;
		for(OutlineFilter filter: activefilters)
			if(!filter.showClassFilter(root.getClazz()))
				toRemove=true;
		if(toRemove)
			root.getClazz().setName("("+root.getClazz().getName()+")");
		tv.setInput(root);
		tv.expandAll();
	}


	private void testClassFilter(ArrayList<OutlineFilter> activefilters, OutlineClass oc) {
		boolean toRemove;
		ArrayList<OutlineClass> orphans = new ArrayList<OutlineClass>();
		for(OutlineClass child: oc.getChildren_classes()){
			toRemove=false;
			for(OutlineFilter filter: activefilters)
				if(!filter.showClassFilter(child))
					toRemove=true;
			if(toRemove){
				oc.getFields().addAll(child.getFields());
				oc.getMethods().addAll(child.getMethods());
				orphans= child.getChildren_classes();
			}
			ArrayList<OutlineField> fieldsToRemove = new ArrayList<OutlineField>();
			for(OutlineField childfield: child.getFields()){
				toRemove=false;
				for(OutlineFilter filter: activefilters)
					if(!filter.showFieldFilter(childfield))
						toRemove=true;
				if(toRemove)
					fieldsToRemove.add(childfield);
			}
			child.getFields().removeAll(fieldsToRemove);
			ArrayList<OutlineMethod> methodsToRemove = new ArrayList<OutlineMethod>();
			for(OutlineMethod childmethod: child.getMethods()){
				toRemove=false;
				for(OutlineFilter filter: activefilters)
					if(!filter.showMethodFilter(childmethod))
						toRemove=true;
				if(toRemove)
					methodsToRemove.add(childmethod);
			}
			child.getMethods().removeAll(methodsToRemove);
			testClassFilter(activefilters, child);
		}
		oc.getChildren_classes().addAll(orphans);
	}
}
