package pa.iscde.outlaw;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class Activator implements BundleActivator {
	private JavaEditorServices service;
	private static Activator activator;
	@Override
	public void start(BundleContext context) throws Exception {
		activator=this;
		ServiceReference<JavaEditorServices> javaeditor = context.getServiceReference(JavaEditorServices.class);
		service=context.getService(javaeditor);
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
}
