package tax.model;

public class Tax {
	private Integer taxId;
	private String fullName;
	private String companyName;
	private String email;
	private String address;
	private String phone;
	private Double salary;
	private Double tax;
	private Integer userId;

	public Tax(Integer taxId, String fullName, String companyName, String email, String address, String phone,
			Double salary, Double tax, Integer userId) {
		super();
		this.taxId = taxId;
		this.fullName = fullName;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.tax = tax;
		this.userId = userId;
	}

	public Tax(String fullName, String companyName, String email, String address, String phone, Double salary,
			Double tax, Integer userId) {
		super();
		this.fullName = fullName;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.tax = tax;
		this.userId = userId;
	}

	public Tax(String fullName, String companyName, String email, String address, String phone, Double salary,
			Integer userId) {
		super();
		this.fullName = fullName;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.userId = userId;
	}

	public Tax(Integer taxId, String fullName, String companyName, String email, String address, String phone,
			Double salary, Integer userId) {
		super();
		this.taxId = taxId;
		this.fullName = fullName;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.userId = userId;
	}

	public Tax(Integer userId) {
		super();
		this.userId = userId;
	}

	public Tax(Double salary) {
		super();
		this.salary = salary;
	}

	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Tax [taxId=" + taxId + ", fullName=" + fullName + ", companyName=" + companyName + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", salary=" + salary + ", tax=" + tax + ", userId="
				+ userId + "]";
	}

}
