package pa.iscde.outlaw;

public class IconChange {

	private String name;
	private OutlineIcon createExecutableExtension;

	public IconChange(String name, OutlineIcon createExecutableExtension) {
		this.setName(name);
		this.setCreateExecutableExtension(createExecutableExtension);
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

}
