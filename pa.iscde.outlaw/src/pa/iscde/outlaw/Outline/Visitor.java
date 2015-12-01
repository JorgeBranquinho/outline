package pa.iscde.outlaw.Outline;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import pa.iscde.outlaw.Visibility;

public class Visitor extends ASTVisitor{

	protected static OutlineMethod outtmp;
	private String parentClass;
	private ArrayList<OutlineMethod> methods = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	private ArrayList<OutlineClass> children_classes = new ArrayList<OutlineClass>();
	private OutlineClass clazz;
	private File file;
	//private Visibility vis= Visibility.PACKAGE_PRIVATE;

	public Visitor(File file) {
		this.setFile(file);
		setParentClass(file.getName());
	}

	/*@Override//ta aqui para q? nao � usado
	public void endVisit(MethodDeclaration node) {
		super.endVisit(node);
	}*/
	
	//XXX estes dois metodos n�o devem ser usados....ignorar
	
	/*@Override
	public boolean visit(SimpleName node){//ok isto faz coisas....mtas coisas! USAR ISTO!!!
		//System.err.println("-->					" + node.toString());
		if (node.getLocationInParent() == FieldAccess.NAME_PROPERTY)       return true;
		if (node.getLocationInParent() == QualifiedName.NAME_PROPERTY)       return true;
		IBinding binding=((Name)node).resolveBinding();
		if (binding instanceof IVariableBinding) {
			IVariableBinding vBinding=(IVariableBinding)binding;
			//if (vBinding.isField()) {
			ITypeBinding bc=vBinding.getDeclaringClass();
			//System.out.println("porra po java pq raio n estudamos python..." + bc);
			//}
		}
		return true;
	}*/

	@Override
	public boolean visit(TypeDeclaration node) {
		int flags = node.getModifiers();
		if(!node.isPackageMemberTypeDeclaration()){//isto vs isMemberClass
			OutlineClass tmpNestedClass = new OutlineClass(node.getName().toString(), clazz.getName(), true, false);
			children_classes.add(tmpNestedClass);
			node.accept(new ASTVisitor() {
				@Override
				public boolean visit(MethodDeclaration node) {
					OutlineMethod tmpNestedMethod = new OutlineMethod(node.getName().toString(), node.getReturnType2(), 
							node.isConstructor(), node.getModifiers(), node.parameters(), null);
					//return super.visit(node);
					Visitor.this.addToMethodList(children_classes.get(children_classes.size()),tmpNestedMethod);
					return false;//isto vs super.visit(node) -> evitar q outer class veja os metodos
				}
			});
			//FIXME: ^ retornar o outlinemethod q ta dentro da anon (nao sei como se faz)e add a lista de methods da nested (facil)
			
			//TODO:fazer o mesmo para os fields dentro da nested (facil)
			
		}else{
			if(Modifier.isPrivate(flags)){
				clazz.setVisibility(Visibility.PRIVATE);
			}else if(Modifier.isProtected(flags)){
				clazz.setVisibility(Visibility.PROTECTED);
			}else if(Modifier.isPublic(flags)){
				clazz.setVisibility(Visibility.PUBLIC);
			}else{
				clazz.setVisibility(Visibility.PACKAGE_PRIVATE);
			}

			clazz.setAbstract(Modifier.isAbstract(flags));
			clazz.setInterface(node.isInterface());
			clazz.setFinal(Modifier.isFinal(flags));
			clazz.setStatic(Modifier.isStatic(flags));
			clazz.setEnum(false);
			clazz.checkProperties(flags);
			clazz.checkVisibility(flags);
			clazz.setImg();
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		String classname=node.getParent().toString().subSequence(0, node.getParent().toString().indexOf("{")).toString();
		children_classes.add(new OutlineClass(classname + " {...}", clazz.getName(), false, true));
		//TODO:ir buscar metodos e fields, (igual ao de cima)
		return super.visit(node);
		//return false;//aqui com false os metodos desta classe ja nao sao visitados
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		int flags = node.getModifiers();
		if(Modifier.isPublic(flags)){
			clazz.setVisibility(Visibility.PUBLIC);
		}else{
			clazz.setVisibility(Visibility.PACKAGE_PRIVATE);
		}
		clazz.setEnum(true);
		//System.out.println(node.getName());
		for(Object constant: node.enumConstants()){
			fields.add(new OutlineField(constant.toString(), clazz));
		}
		clazz.checkProperties(flags);
		clazz.checkVisibility(flags);
		clazz.setImg();
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		//*****
		//aqui devia estar um IBinding (of isaac)
		//*****
		String parentname=node.getParent().toString().subSequence(0, node.getParent().toString().indexOf("{")).toString();
		//System.err.println("fuck this thing in particular___" + parentname);
		fields.add(new OutlineField(node.toString().replaceAll("[;\\n]", "").split("=")[0], node.getType(), 
				node.getModifiers(), clazz));
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		//*****novo
		/*
		Block block = node.getBody();
				final String oi=node.getName().toString();
        block.accept(new ASTVisitor() {
            public boolean visit(MethodInvocation node) {
            	System.err.println("sou "+oi + " e =" + node.getName());
                System.out.println("Name: " + node.getName());
                Expression expression = node.getExpression();
                if (expression != null) {
                    System.out.println("Expr: " + expression.toString());
                    ITypeBinding typeBinding = expression.resolveTypeBinding();
                    if (typeBinding != null) {
                        System.out.println("Type: " + typeBinding.getName());
                    }
                }
                IMethodBinding binding = node.resolveMethodBinding();
                if (binding != null) {
                    ITypeBinding type = binding.getDeclaringClass();
                    if (type != null) {
                        System.out.println("Decl: " + type.getName());
                    }
                }

                return true;
            }
        });
		 */
		//******novo
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
		clazz=new OutlineClass(parentClass, getPackage(getFile().getAbsolutePath()));
		clear();
	}

	public OutlineClass getClazz() {
		clazz.setFields(fields);
		clazz.setMethod(methods);
		clazz.setChildren_classes(children_classes);
		return clazz;
	}

	public void setClazz(OutlineClass clazz) {
		this.clazz = clazz;
	}

	private void clear(){
		methods.clear();
		fields.clear();
		children_classes.clear();
	}

	private String getPackage(String parent) {
		String[] path=parent.split("\\\\");
		return path[path.length-2];
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void addToMethodList(OutlineClass outlineClass, OutlineMethod out){
		outlineClass.getMethod().add(out);
	}
	
	
}
