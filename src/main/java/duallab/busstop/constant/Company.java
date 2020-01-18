package duallab.busstop.constant;

public enum Company {
	POSH("Posh"),
	GROTTY("Grotty");

	private final String companyName;

	private Company(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

}
