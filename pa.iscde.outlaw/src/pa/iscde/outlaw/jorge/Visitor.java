package pa.iscde.outlaw.jorge;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Visitor extends ASTVisitor{

	private ArrayList<OutlineMethod> methods = new ArrayList<OutlineMethod>();

	@Override
	public boolean visit(MethodDeclaration node) {
		System.out.println(node.getName().toString() + "!!");
		methods.add(new OutlineMethod(node.getName().toString(), node.getReturnType2(), node.isConstructor()));
		
		return super.visit(node);
	}

	public ArrayList<OutlineMethod> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<OutlineMethod> methods) {
		this.methods = methods;
	}
}
