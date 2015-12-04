package pa.iscde.outlaw.Outline;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import pa.iscde.outlaw.Visibility;
public class Visitor extends ASTVisitor{

	//protected static OutlineMethod outtmp;
	private String parentClass;
	private List<OutlineMethod> removedMethods = Lists.newArrayList();
	private List<OutlineField> removedFields = Lists.newArrayList();
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
							node.isConstructor(), node.getModifiers(), node.parameters(), children_classes.get(children_classes.size()-1));
					Visitor.this.addToMethodList(children_classes.get(children_classes.size()-1),tmpNestedMethod);
					return false;
				}
				@Override
				public boolean visit(FieldDeclaration node) {
					OutlineField tmpNestedField = new OutlineField(node.toString().replaceAll("[;\\n]", "").split("=")[0], node.getType(), 
							node.getModifiers(), children_classes.get(children_classes.size()-1));
					Visitor.this.addToFieldList(children_classes.get(children_classes.size()-1),tmpNestedField);
					return false;
				}
			});
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
			@Override
			public boolean visit(FieldDeclaration node) {
				OutlineField tmpNestedField = new OutlineField(node.toString().replaceAll("[;\\n]", "").split("=")[0], node.getType(), 
						node.getModifiers(), children_classes.get(children_classes.size()-1));
				Visitor.this.addToFieldList(children_classes.get(children_classes.size()-1),tmpNestedField);
				return false;
			}
		});
		//TODO:isto devia estar dentro do metodo q chamou -> idk como se faz

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
		RemoveDuplicates();
		clazz.setFields(fields);
		clazz.setMethod(methods);
		clazz.setChildren_classes(children_classes);
		return clazz;
	}

	private void recursiveGetChildrenMethodsAndFields(OutlineClass oc, ArrayList<OutlineMethod> childrenMethods, ArrayList<OutlineField> childrenFields){
		childrenMethods.addAll(oc.getMethod());
		childrenFields.addAll(oc.getFields());
		if(oc.getChildren_classes().isEmpty()) return;
		for(OutlineClass coc: children_classes)
			recursiveGetChildrenMethodsAndFields(coc, childrenMethods, childrenFields);
	}
	
	private void RemoveDuplicates() {
		final ArrayList<OutlineMethod> childrenMethods = new ArrayList<OutlineMethod>();
		final ArrayList<OutlineField> childrenFields = new ArrayList<OutlineField>();
		for(OutlineClass oc: children_classes)
			recursiveGetChildrenMethodsAndFields(oc, childrenMethods, childrenFields);
		Iterables.removeIf(methods, new Predicate<OutlineMethod>(){
			@Override
			public boolean apply(OutlineMethod input) {
				for (Iterator<OutlineMethod> iter = childrenMethods.listIterator(); iter.hasNext(); ) {
					if(input.toString().equals(iter.next().toString())){
						Visitor.this.addToRemovedMethods(input);
						continue;
					}
				}
				return false;
			}
		});
		Iterables.removeIf(fields, new Predicate<OutlineField>(){
			@Override
			public boolean apply(OutlineField input) {
				for (Iterator<OutlineField> iter = childrenFields.listIterator(); iter.hasNext(); ) {
					if(input.toString().equals(iter.next().toString())){
						Visitor.this.addToRemovedFields(input);
						continue;
					}
				}
				return false;
			}
		});
		methods.removeAll(removedMethods);
		fields.removeAll(removedFields);
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

	public void addToFieldList(OutlineClass outlineClass, OutlineField out){
		outlineClass.getFields().add(out);
	}

	public void addToRemovedMethods(OutlineMethod out){
		removedMethods.add(out);
	}

	public void addToRemovedFields(OutlineField out){
		removedFields.add(out);
	}
}
//FIXME: nao faz bem inners de inners :c
