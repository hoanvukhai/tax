package tax.dao;

import java.util.List;

import tax.model.TaxRange;

public interface TaxRangeDAO {
	public boolean createTaxRange(TaxRange taxRange);

	public List<TaxRange> getTaxRangeList();

	public boolean updateTaxRange(TaxRange taxRange);

	public boolean removeTaxRange(Integer id);

	public double getTaxPercentge(Double salary);

	public TaxRange getOneTaxRange(Integer id);

}
