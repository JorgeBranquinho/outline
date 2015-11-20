package pa.iscde.outlaw;

import java.awt.Color;
import java.awt.Panel;
import java.io.File;
import java.util.Map;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

import pa.iscde.outlaw.ivo.OutlineTreeView;
import pa.iscde.outlaw.jorge.Visitor;
import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class PidescoView1 implements PidescoView {

	private Visitor v;
	private Composite viewArea;
	private Map<String, Image> imageMap;
	private OutlineTreeView otv;

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		
		this.setViewArea(viewArea);
		this.setImageMap(imageMap);
		viewArea.setLayout(new GridLayout(SWT.VERTICAL, false));
		//Composite composite = new Composite(viewArea, SWT.NONE);
		//composite.setLayout(new GridLayout(2, true));
		viewArea.setBackground(viewArea.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		CLabel txtpackage = new CLabel(viewArea, SWT.NONE);
		//txtpackage.setImage(imageMap.get("smiley.png"));
		
		//CLabel txtclass = new CLabel(viewArea, SWT.NONE);
		
		//Label label = new Label(composite, SWT.NONE);
		//label.setImage(imageMap.get("smiley.png"));
		//Text txtpackage = new Text(composite, SWT.WRAP);
		//Text txtclass = new Text(viewArea, SWT.WRAP);
		//txtclass.setEditable(false);
		//txtclass.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		//txtpackage.setEditable(false);
		
		
		//txtpackage.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		
		
		final JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
		
		final File f = services.getOpenedFile();
		
		if(f!=null){
			System.out.println("OI");
			v= new Visitor(f.getName());
			services.parseFile(f, v);
			otv = new OutlineTreeView(viewArea,v,imageMap);
		}else{
			otv = new OutlineTreeView(viewArea,imageMap);
		}
			
		services.addListener(new JavaEditorListener.Adapter() {
			@Override
			public void fileOpened(File file) {
				// TODO Auto-generated method stub
				if(v==null){
					v= new Visitor(file.getName());
				}
				
				//services.parseFile(f, v);
				
				if(!v.equals(null)){
					System.out.println(file.getName());
					v.setParentClass(file.getName());
					services.parseFile(file, v);
					System.out.println(v.getClazz().getName());
					otv.update(v.getClazz());
				}
				
//				OutlineTreeView otv = new OutlineTreeView(PidescoView1.this.getViewArea(),v,PidescoView1.this.getImageMap());
				
			}
			
			@Override
			public void fileClosed(File file) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		/*if(f!=null){
//			Visitor v= new Visitor(f.getName());
			//txtclass.setText(f.getName());
			//txtpackage.setText(getPackage(f.getParent()));
			//services.parseFile(f, v);
			v= new Visitor(f.getName());
			services.parseFile(f, v);
			otv = new OutlineTreeView(viewArea,v,imageMap);
			//Button b = new Button(viewArea, SWT.NONE);
			//b.setText("Hanauta Sanchou Yahazu Giri!!");
			/*b.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					services.parseFile(f, new Visitor(f.getName()));
					
				}
			});*/
			
			
		//}
		
		
	}

	
	
	private void setImageMap(Map<String, Image> imageMap) {
		// TODO Auto-generated method stub
		this.imageMap=imageMap;
	}

	private void setViewArea(Composite viewArea) {
		// TODO Auto-generated method stub
		this.viewArea=viewArea;
	}

	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-1];
	}

	public Composite getViewArea() {
		return viewArea;
	}

	public Map<String, Image> getImageMap() {
		return imageMap;
	}

}
