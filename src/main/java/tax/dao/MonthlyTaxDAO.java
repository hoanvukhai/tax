package tax.dao;

import java.util.List;

import tax.model.MonthlyTax;

public interface MonthlyTaxDAO {
	public boolean saveMonthlyTax(MonthlyTax monthlyTax);

	public boolean updateMonthlyTax(MonthlyTax monthlyTax);

	public MonthlyTax getOneMonthlyTax(Integer id);

	public boolean deleteOneMonthlyTax(Integer id);

	public List<MonthlyTax> listMonthlyTax();

	public List<MonthlyTax> listMonthlyTax(Integer taxId);
}
