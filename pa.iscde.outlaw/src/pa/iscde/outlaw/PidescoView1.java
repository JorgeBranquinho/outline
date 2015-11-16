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
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class PidescoView1 implements PidescoView {

	public PidescoView1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		viewArea.setLayout(new GridLayout(SWT.VERTICAL, false));

		//Composite composite = new Composite(viewArea, SWT.NONE);
		//composite.setLayout(new GridLayout(2, true));

		//CLabel txtpackage = new CLabel(viewArea, SWT.NONE);
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
			Visitor v= new Visitor(f.getName());
			//txtclass.setText(f.getName());
			//txtpackage.setText(getPackage(f.getParent()));
			services.parseFile(f, v);
			OutlineTreeView otv = new OutlineTreeView(viewArea,v);
			//Button b = new Button(viewArea, SWT.NONE);
			//b.setText("Hanauta Sanchou Yahazu Giri!!");
			/*b.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					services.parseFile(f, new Visitor(f.getName()));
					
				}
			});*/
			
			
		}
	}

	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-1];
	}

}
