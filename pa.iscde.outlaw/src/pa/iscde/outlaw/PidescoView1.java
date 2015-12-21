package pa.iscde.outlaw;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import pa.iscde.outlaw.Outline.OutlineClass;
import pa.iscde.outlaw.Outline.OutlineField;
import pa.iscde.outlaw.Outline.OutlineMethod;
import pa.iscde.outlaw.Outline.Visitor;
import pa.iscde.outlaw.TreeView.OutlineTreeView;
import pa.iscde.outlaw.extensibility.OutlineFilter;
import pa.iscde.outlaw.extensibility.OutlineIcon;
import pa.iscde.outlaw.extensibility.OutlineType;
import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class PidescoView1 implements PidescoView {

	private Visitor v;
	private Composite viewArea;
	public static Map<String, Image> imageMap;
	private OutlineTreeView otv;
	private ArrayList<FilterView> filterviews = new ArrayList<FilterView>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private final JavaEditorServices services = Activator.getActivator().getService();
	//private final JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
	private ArrayList<IconChange> iconchange = new ArrayList<IconChange>();
	public static String path;

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		this.setViewArea(viewArea);
		this.setImageMap(imageMap);
		viewArea.setBackground(viewArea.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		resetImgPath();
		viewArea.setLayout(new GridLayout());

		final File f = services.getOpenedFile();

		if (f != null) {
			v = new Visitor(f);
			services.parseFile(f, v);
			IconChange();
			otv = new OutlineTreeView(viewArea, v, imageMap, services);
		} else {
			otv = new OutlineTreeView(viewArea, imageMap, services);
		}

		ApplyFilter();

		services.addListener(new JavaEditorListener.Adapter() {

			@Override
			public void fileOpened(File file) {
				resetImgPath();
				if (v == null) {
					v = new Visitor(file);

				}

				if (!v.equals(null)) {
					v.setFile(file);
					v.setParentClass(file.getName());
					services.parseFile(file, v);
					IconChange();
					otv.update(v.getClazz());
				}

			}

			@Override
			public void fileSaved(File file) {
				super.fileSaved(file);
				resetImgPath();
				v.setFile(file);
				v.setParentClass(file.getName());
				services.parseFile(file, v);
				IconChange();
				otv.update(v.getClazz());
			}
		});
	}

	private void resetImgPath() {
		path=PidescoView1.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\images\\";
	}

	private void IconChange() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extRegistry.getExtensionPoint("pa.iscde.outlaw.icon");// test.textext
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension e : extensions) {
			IConfigurationElement[] confElements = e.getConfigurationElements();
			for (IConfigurationElement c : confElements) {
				try {
					iconchange.add(
							new IconChange(c.getAttribute("name"), (OutlineIcon) c.createExecutableExtension("class"),c.getAttribute("imgpath")));
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (iconchange.size() > 0) {
			for (IconChange ic : iconchange) {
				path=ic.getImgPath();
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.CLASS)) {
					ic.getCreateExecutableExtension().setIcon(v.getClazz());
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						ic.getCreateExecutableExtension().setIcon(oc);
					}
				}
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.FIELD)) {
					for(OutlineField of: v.getFields()){
						ic.getCreateExecutableExtension().setIcon(of);
					}
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						for (OutlineField of : oc.getFields()) {
							ic.getCreateExecutableExtension().setIcon(of);
						}
					}

				}
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.METHOD)) {
					for(OutlineMethod om: v.getMethods()){
						ic.getCreateExecutableExtension().setIcon(om);
					}
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						for (OutlineMethod om : oc.getMethods()) {
							ic.getCreateExecutableExtension().setIcon(om);
						}
					}
				}
			}
		}
	}

	private void ApplyFilter() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extRegistry.getExtensionPoint("pa.iscde.outlaw.filter");// test.textext
		IExtension[] extensions = extensionPoint.getExtensions();
		for (IExtension e : extensions) {
			IConfigurationElement[] confElements = e.getConfigurationElements();
			for (IConfigurationElement c : confElements) {
				try {
					filterviews.add(new FilterView(c.getAttribute("name"),
							((OutlineFilter) c.createExecutableExtension("class"))));
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (filterviews.size() > 0) {
			Label separator = new Label(viewArea, SWT.HORIZONTAL | SWT.SEPARATOR);
			separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			for (final FilterView fv : filterviews) {
				Button b = new Button(viewArea, SWT.CHECK);
				b.setText(fv.getViewName());
				buttons.add(b);
				b.addSelectionListener(new SelectionListener() {
					private boolean selected = false;

					@Override
					public void widgetSelected(SelectionEvent e) {
						selected = !selected;
						if (otv == null)
							return;
						if (selected) {
							for (Button b : buttons) {
								if (b != e.getSource())
									b.setEnabled(false);
							}
							otv.setNewFilter(fv.getViewFilter());
						} else {
							for (Button b : buttons) {
								b.setEnabled(true);
							}
							resetImgPath();
							File openedfile = services.getOpenedFile();
							v.setFile(openedfile);
							v.setParentClass(openedfile.getName());
							services.parseFile(openedfile, v);
							IconChange();
							otv.update(v.getClazz());
						}
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});
			}
		}
	}

	private void setImageMap(Map<String, Image> imageMap) {
		PidescoView1.imageMap = imageMap;
	}

	private void setViewArea(Composite viewArea) {
		this.viewArea = viewArea;
	}

	public Composite getViewArea() {
		return viewArea;
	}

	public Map<String, Image> getImageMap() {
		return imageMap;
	}
}