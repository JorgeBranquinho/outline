package pa.iscde.outlaw.jorge;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Visitor extends ASTVisitor{

	private String parentClass;
	private ArrayList<OutlineMethod> methods = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	private OutlineClass clazz;
	private File file;

	public Visitor(File file) {
		this.file=file;
		setParentClass(file.getName());
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		//System.out.println(node.toString().replaceAll("[;\\n]", "") + "??" + node.getType());
		fields.add(new OutlineField(node.toString().replaceAll("[;\\n]", ""), node.getType(), 
				node.getModifiers(), clazz));
		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		int flags = node.getModifiers();
		if(Modifier.isPrivate(flags)){
			clazz.setVisibility("Private");
		}else if(Modifier.isProtected(flags)){
			clazz.setVisibility("Protected");
		}else if(Modifier.isPublic(flags)){
			clazz.setVisibility("Public");
		}else{
			clazz.setVisibility("Package private");
		}
		
		clazz.setInterface(Modifier.isInterface(flags));
		clazz.setFinal(Modifier.isFinal(flags));
		clazz.setStatic(Modifier.isStatic(flags));
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		methods.add(new OutlineMethod(node.getName().toString(), node.getReturnType2(), 
				node.isConstructor(), node.getModifiers(), node.parameters(), clazz));
		return super.visit(node);
	}

	public ArrayList<OutlineMethod> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<OutlineMethod> methods) {
		this.methods = methods;
	}

	public ArrayList<OutlineField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<OutlineField> fields) {
		this.fields = fields;
	}

	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
		clazz=new OutlineClass(parentClass, getPackage(file.getAbsolutePath()));
		clear();
	}

	public OutlineClass getClazz() {
		clazz.setFields(fields);
		clazz.setMethod(methods);
		//		classPropertiesDone ^= true;
		return clazz;
	}

	public void setClazz(OutlineClass clazz) {
		this.clazz = clazz;
	}

	private void clear(){
		methods.clear();
		fields.clear();

	}
	
	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-2];
	}
}
