package pa.iscde.outlaw.jorge;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Visitor extends ASTVisitor{

	private String parentClass;
	private ArrayList<OutlineMethod> methods = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	private OutlineClass clazz;
	
	public Visitor(String parentClass) {
		setParentClass(parentClass);
		clazz=new OutlineClass(parentClass);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		//System.out.println(node.toString().replaceAll("[;\\n]", "") + "??" + node.getType());
	
		fields.add(new OutlineField(node.toString().replaceAll("[;\\n]", ""), node.getType(), 
				node.getModifiers(), clazz));
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		//System.out.println(node.getName().toString() + "!!");
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
	}

	public OutlineClass getClazz() {
		
		clazz.setFields(fields);
		clazz.setMethod(methods);
		return clazz;
	}

	public void setClazz(OutlineClass clazz) {
		this.clazz = clazz;
	}
}
