package pa.iscde.outlaw;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import extensionpoints.ISearchEvent;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class Activator implements BundleActivator {
	private JavaEditorServices service;
	private ISearchEvent search_event;
	private static Activator activator;
	@Override
	public void start(BundleContext context) throws Exception {
		activator=this;
		ServiceReference<JavaEditorServices> javaeditor = context.getServiceReference(JavaEditorServices.class);
		service=context.getService(javaeditor);
		
		ServiceReference<ISearchEvent> deepS_editor = context.getServiceReference(ISearchEvent.class);
		search_event = context.getService(deepS_editor); 
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public static Activator getActivator() {
		return activator;
	}

	public JavaEditorServices getService() {
		return service;
	}

	public void setService(JavaEditorServices service) {
		this.service = service;
	}
	
	public ISearchEvent getSearch_event() {
		return search_event;
	}
}
