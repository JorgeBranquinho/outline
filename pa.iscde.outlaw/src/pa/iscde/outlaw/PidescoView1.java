package pa.iscde.outlaw;

import java.awt.Color;
import java.awt.Panel;
import java.io.File;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.swt.SWT;
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
		viewArea.setLayout(new RowLayout(SWT.VERTICAL));

		Composite composite = new Composite(viewArea, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));

		Label label = new Label(composite, SWT.NONE);
		label.setImage(imageMap.get("smiley.png"));
		Text txtpackage = new Text(composite, SWT.WRAP);
		Text txtclass = new Text(viewArea, SWT.WRAP);
		txtclass.setEditable(false);
		txtclass.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		txtpackage.setEditable(false);
		txtpackage.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		final JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
		final File f = services.getOpenedFile();
		txtclass.setText(f.getName());
		txtpackage.setText(getPackage(f.getParent()));
		Button b = new Button(viewArea, SWT.NONE);
		b.setText("Hanauta Sanchou Yahazu Giri!!");
		b.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				services.parseFile(f, new Visitor());
			}
		});
		//OutlineTreeView otv = new OutlineTreeView(composite);
	}

	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-1];
	}

}
