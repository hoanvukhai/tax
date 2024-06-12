package tax.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import tax.dao.MonthlyTaxDAO;
import tax.dao.MonthlyTaxDAOImpl;
import tax.dao.TaxDAO;
import tax.dao.TaxDAOImpl;
import tax.dao.TaxRangeDAO;
import tax.dao.TaxRangeDAOImpl;
import tax.dao.UserDAO;
import tax.dao.UserDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tax")
public class SpringMvcConfig {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TaxManagement");
		dataSource.setUsername("root");
		dataSource.setPassword("123456789");

		return dataSource;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(getDataSource());
	}

	@Bean
	public TaxDAO getTaxDAO() {
		return new TaxDAOImpl(getDataSource());
	}

	@Bean
	public MonthlyTaxDAO getMonthlyTaxDAO() {
		return new MonthlyTaxDAOImpl(getDataSource());
	}

	@Bean
	public TaxRangeDAO getTaxRangeDAO() {
		return new TaxRangeDAOImpl(getDataSource());
	}
}
