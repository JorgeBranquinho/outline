package pa.iscde.outlaw.jorge;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.graphics.Image;

public class OutlineClass implements OutlineLookup{

	private ArrayList<OutlineMethod> method = new ArrayList<OutlineMethod>();
	private ArrayList<OutlineField> fields = new ArrayList<OutlineField>();
	private String name;
	private Map<String, Image> imageMap;
	
	
	public OutlineClass(String parentClass) {
		// TODO Auto-generated constructor stub
		setName(parentClass);
	}

	public ArrayList<OutlineMethod> getMethod() {
		return method;
	}
	
	public void setMethod(ArrayList<OutlineMethod> method) {
		this.method = method;
	}
	
	public ArrayList<OutlineField> getFields() {
		return fields;
	}
	
	public void setFields(ArrayList<OutlineField> fields) {
		this.fields = fields;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name.replace(".java", "");
	}

	@Override
	public OutlineClass getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(OutlineClass parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisibility(String visibility) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatic(boolean isStatic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFinal(boolean isFinal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void setImg(String imgType) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public String getImg() {
		// TODO Auto-generated method stub
		return "class_obj.gif";
	}


	
	
}
