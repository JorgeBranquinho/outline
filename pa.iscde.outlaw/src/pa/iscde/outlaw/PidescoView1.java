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
import pt.iscte.pidesco.extensibility.PidescoView;
import pt.iscte.pidesco.javaeditor.internal.JavaEditorActivator;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class PidescoView1 implements PidescoView {

	private Visitor v;
	private Composite viewArea;
	private Map<String, Image> imageMap;
	private OutlineTreeView otv;
	private ArrayList<FilterView> filterviews = new ArrayList<FilterView>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private final JavaEditorServices services = JavaEditorActivator.getInstance().getServices();
	private ArrayList<IconChange> iconchange = new ArrayList<IconChange>();

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {

		this.setViewArea(viewArea);
		this.setImageMap(imageMap);
		viewArea.setBackground(viewArea.getDisplay().getSystemColor(SWT.COLOR_WHITE));

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
				if (v == null) {
					v = new Visitor(file);

				}

				if (!v.equals(null)) {

					v.setFile(file);
					v.setParentClass(file.getName());
					services.parseFile(file, v);
					IconChange();
					otv.update(v.getClazz());
					ApplyFilter();
				}

			}

			@Override
			public void fileSaved(File file) {
				super.fileSaved(file);
				v.setFile(file);
				v.setParentClass(file.getName());
				services.parseFile(file, v);
				IconChange();
				otv.update(v.getClazz());
				ApplyFilter();
			}
		});
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
							new IconChange(c.getAttribute("name"), (OutlineIcon) c.createExecutableExtension("class")));
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		if (iconchange.size() > 0) {
			for (IconChange ic : iconchange) {
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.CLASS)) {
					ic.getCreateExecutableExtension().isVisible(v.getClazz());
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						ic.getCreateExecutableExtension().isVisible(oc);
					}
				}
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.FIELD)) {
					for(OutlineField of: v.getFields()){
						ic.getCreateExecutableExtension().isVisible(of);
					}
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						for (OutlineField of : oc.getFields()) {
							ic.getCreateExecutableExtension().isVisible(of);
						}
					}

				}
				if (ic.getCreateExecutableExtension().getType().equals(OutlineType.METHOD)) {
					for(OutlineMethod om: v.getMethods()){
						ic.getCreateExecutableExtension().isVisible(om);
					}
					for (OutlineClass oc : v.getClazz().getChildren_classes()) {
						for (OutlineMethod om : oc.getMethods()) {
							ic.getCreateExecutableExtension().isVisible(om);
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
							File openedfile = services.getOpenedFile();
							v.setFile(openedfile);
							v.setParentClass(openedfile.getName());
							services.parseFile(openedfile, v);
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
		this.imageMap = imageMap;
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