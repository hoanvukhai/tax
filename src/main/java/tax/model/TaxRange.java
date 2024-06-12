package tax.model;

public class TaxRange {

	private Integer id;
	private Double startRange;
	private Double endRange;
	private Double taxPercentage;

	public TaxRange(Integer id, Double startRange, Double endRange, Double taxPercentage) {
		super();
		this.id = id;
		this.startRange = startRange;
		this.endRange = endRange;
		this.taxPercentage = taxPercentage;
	}

	public TaxRange() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getStartRange() {
		return startRange;
	}

	public void setStartRange(Double startRange) {
		this.startRange = startRange;
	}

	public Double getEndRange() {
		return endRange;
	}

	public void setEndRange(Double endRange) {
		this.endRange = endRange;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	@Override
	public String toString() {
		return "TaxRange [id=" + id + ", startRange=" + startRange + ", endRange=" + endRange + ", taxPercentage="
				+ taxPercentage + "]";
	}

}
