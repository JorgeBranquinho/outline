package pa.iscde.outlaw;

public class FilterView {

	private String viewName;
	private OutlineFilter viewFilter;
	
	public FilterView(String name, OutlineFilter filter){
		this.viewName=name;
		this.viewFilter=filter;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public OutlineFilter getViewFilter() {
		return viewFilter;
	}

	public void setViewFilter(OutlineFilter viewFilter) {
		this.viewFilter = viewFilter;
	}
}
