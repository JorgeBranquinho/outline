package pa.iscde.outlaw;

import java.awt.Color;
import java.awt.Panel;
import java.io.File;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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
		
		Label label = new Label(viewArea, SWT.NONE);
		label.setImage(imageMap.get("smiley.png"));
		Text txtpackage = new Text(viewArea, SWT.WRAP);
		Text txtclass = new Text(viewArea, SWT.WRAP);
		txtclass.setEditable(false);
		txtclass.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		txtpackage.setEditable(false);
		txtpackage.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
		JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
		File f = services.getOpenedFile();
		txtclass.setText(f.getName());
		txtpackage.setText(getPackage(f.getParent()));
		txtclass.setText("teste2");
		
	}

	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-1];
	}

}
