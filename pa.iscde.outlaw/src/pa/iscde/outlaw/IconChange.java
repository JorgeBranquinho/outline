package pa.iscde.outlaw;

public class IconChange {

	private String name;
	private OutlineIcon createExecutableExtension;
	private String path;

	public IconChange(String name, OutlineIcon createExecutableExtension,String path) {
		this.setName(name);
		this.setCreateExecutableExtension(createExecutableExtension);
		this.setPath(path);
	}

	private void setPath(String path) {
		// TODO Auto-generated method stub
		this.path=path;
	}

	public OutlineIcon getCreateExecutableExtension() {
		return createExecutableExtension;
	}

	public void setCreateExecutableExtension(OutlineIcon createExecutableExtension) {
		this.createExecutableExtension = createExecutableExtension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return path;
	}

}
