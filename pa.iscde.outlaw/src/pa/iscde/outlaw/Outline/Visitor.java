package pa.iscde.outlaw.Outline;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
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

	public Visitor(File file) {
		this.setFile(file);
		setParentClass(file.getName());
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		int flags = node.getModifiers();
		if(!node.isPackageMemberTypeDeclaration()){
			OutlineClass tmpNestedClass = new OutlineClass(node.getName().toString(), clazz.getName(), true, false);
			children_classes.add(tmpNestedClass);
			node.accept(new ASTVisitor() {
				@Override
				public boolean visit(MethodDeclaration node) {
					OutlineMethod tmpNestedMethod = new OutlineMethod(node.getName().toString(), node.getReturnType2(), 
							node.isConstructor(), node.getModifiers(), node.parameters(), null);
					Visitor.this.addToMethodList(children_classes.get(children_classes.size()-1),tmpNestedMethod);
					return false;
				}
			});
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
		OutlineClass tmpAnonClass = new OutlineClass(classname + " {...}", clazz.getName(), false, true);
		children_classes.add(tmpAnonClass);
		node.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration node) {
				OutlineMethod tmpNestedMethod = new OutlineMethod(node.getName().toString(), node.getReturnType2(), 
						node.isConstructor(), node.getModifiers(), node.parameters(), null);
				Visitor.this.addToMethodList(children_classes.get(children_classes.size()-1),tmpNestedMethod);
				return false;
			}
		});
		//TODO:ir buscar metodos e fields, (igual ao de cima)
		//return super.visit(node);
		return false;//aqui com false os metodos desta classe ja nao sao visitados
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
		fields.add(new OutlineField(node.toString().replaceAll("[;\\n]", "").split("=")[0], node.getType(), 
				node.getModifiers(), clazz));
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
