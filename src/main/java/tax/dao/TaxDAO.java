package tax.dao;

import java.util.List;

import tax.model.Tax;
import tax.model.TaxRange;

public interface TaxDAO {
	public boolean saveTax(Tax tax);
	
	public boolean editTax(Tax tax);
	
	public List<Tax> listTax();
	
	public Tax getTaxByUserId(int userId);
	
	public Tax getTaxByTaxId(int taxId);

	List<TaxRange> getTaxRangeList();

	double getTaxPercentge(Double salary);

}
