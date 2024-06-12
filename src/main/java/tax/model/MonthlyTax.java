package tax.model;

public class MonthlyTax {
	private Integer monthlyTaxId;
	private String time;
	private Boolean status;
	private Integer taxId;

	public MonthlyTax(Integer monthlyTaxId, String time, Boolean status, Integer taxId) {
		super();
		this.monthlyTaxId = monthlyTaxId;
		this.time = time;
		this.status = status;
		this.taxId = taxId;
	}

	public MonthlyTax(String time, Boolean status, Integer taxId) {
		super();
		this.time = time;
		this.status = status;
		this.taxId = taxId;
	}

	public MonthlyTax() {
	}

	public MonthlyTax(Integer taxId) {
		super();
		this.taxId = taxId;
	}

	public Integer getMonthlyTaxId() {
		return monthlyTaxId;
	}

	public void setMonthlyTaxId(Integer monthlyTaxId) {
		this.monthlyTaxId = monthlyTaxId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	@Override
	public String toString() {
		return "MonthlyTax [monthlyTaxId=" + monthlyTaxId + ", time=" + time + ", status=" + status + ", taxId=" + taxId
				+ "]";
	}

}
