package pa.iscde.outlaw;

import java.io.File;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
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
		viewArea.setLayout(new RowLayout(SWT.HORIZONTAL));
		Label label = new Label(viewArea, SWT.NONE);
		label.setImage(imageMap.get("smiley.png"));
		Text text = new Text(viewArea, SWT.WRAP);
		text.setEditable(false);
		text.setBounds(0, 0, 0, 0);
		
		
		JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
		File f = services.getOpenedFile();
		text.setText(f.getName());
		
		
	}

}
