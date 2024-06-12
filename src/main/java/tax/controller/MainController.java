package tax.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tax.dao.MonthlyTaxDAO;
import tax.dao.TaxDAO;
import tax.dao.TaxRangeDAO;
import tax.dao.UserDAO;
import tax.model.MonthlyTax;
import tax.model.Tax;
import tax.model.TaxRange;
import tax.model.User;

@Controller
public class MainController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TaxDAO taxDAO;

	@Autowired
	private MonthlyTaxDAO monthlyTaxDAO;

	@Autowired
	private TaxRangeDAO taxRangeDAO;

	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView model) {
		Tax newTax = new Tax();
		model.addObject("tax", newTax);
		List<TaxRange> taxRangeList = taxRangeDAO.getTaxRangeList();
		model.addObject("taxRangeList", taxRangeList);

		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/calculateTax", method = RequestMethod.POST)
	public ModelAndView calculateTax(@ModelAttribute Tax tax, ModelAndView model) {

		List<TaxRange> taxRangeList = taxRangeDAO.getTaxRangeList();
		model.addObject("taxRangeList", taxRangeList);

		Double salary = tax.getSalary();
		if (salary <= 0) {
			model.addObject("noti", "Nhập lương không hợp lệ");
			model.setViewName("home");
			return model;
		}
		Double taxMoney = salary * taxDAO.getTaxPercentge(salary) / 100;

		model.addObject("taxMoney", taxMoney);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = "/loginPage")
	public ModelAndView loginPage(ModelAndView model) {
		User newUser = new User();
		model.addObject("user", newUser);

		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(ModelAndView model) {
		User newUser = new User();
		model.addObject("user", newUser);
		model.setViewName("register_form");

		return model;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user, ModelAndView model) {
		if (user.getPassword().length() < 6 || user.getUsername().length() < 6) {
			model.addObject("noti", "Mật khẩu và tài khoản > 6 kí tự");
			model.setViewName("register_form");
			return model;
		}
		if (!userDAO.register(user)) {
			model.addObject("noti", "Tài khoản đã tồn tại");
			model.setViewName("register_form");
		} else
			model.setViewName("index");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView model, @ModelAttribute User user, HttpSession session) {
		User u = userDAO.login(user);
		if (u != null) {
			session.setAttribute("userId", u.getUserId());
			Tax t = taxDAO.getTaxByUserId(u.getUserId());
			System.out.println(t);
			if (t != null) {
				List<MonthlyTax> monthlyTaxList = monthlyTaxDAO.listMonthlyTax(t.getTaxId());
				model.addObject("monthlyTaxList", monthlyTaxList);
				model.addObject("tax", t);
				model.addObject("user", u);
				model.setViewName("tax");
			} else {
				model.addObject("user", u);
				model.setViewName("tax");
			}
		} else {
			model.addObject("noti", "Đăng nhập thất bại!");
			model.setViewName("index");
		}
		return model;
	}

	@RequestMapping(value = "/addTax", method = RequestMethod.GET)
	public ModelAndView addTax(ModelAndView model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Integer userId = (Integer) session.getAttribute("userId");

			Tax tax = taxDAO.getTaxByUserId(userId);

			if (tax != null) {
				model.addObject("tax", tax);
			} else {
				Tax newTax = new Tax(userId);
				model.addObject("tax", newTax);
			}

			model.setViewName("tax_form");

			return model;
		} else {
			return new ModelAndView("redirect:/loginPage");
		}
	}

	@RequestMapping(value = "/saveTax", method = RequestMethod.POST)
	public ModelAndView saveTax(@ModelAttribute Tax tax, ModelAndView model, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Tax t = taxDAO.getTaxByUserId(tax.getUserId());
			if (t != null) {
				Tax t1 = new Tax(tax.getTaxId(), tax.getFullName(), tax.getCompanyName(), tax.getEmail(),
						tax.getAddress(), tax.getPhone(), tax.getSalary(), tax.getUserId());
				if (!taxDAO.editTax(t1)) {
					model.addObject("noti", "Nhập  quá dài");
					model.setViewName("tax_form");
					return model;
				}
				List<MonthlyTax> monthlyTaxList = monthlyTaxDAO.listMonthlyTax(t.getTaxId());
				model.addObject("monthlyTaxList", monthlyTaxList);
			} else {
				Tax t1 = new Tax(tax.getFullName(), tax.getCompanyName(), tax.getEmail(), tax.getAddress(),
						tax.getPhone(), tax.getSalary(), tax.getUserId());
				if (!taxDAO.saveTax(t1)) {
					model.addObject("noti", "Nhập  quá dài");
					model.setViewName("tax_form");
					return model;
				}
			}
			Tax t2 = taxDAO.getTaxByUserId(tax.getUserId());
			model.addObject("tax", t2);
			User user = new User(t2.getUserId());
			model.addObject("user", user);
			model.setViewName("tax");

			return model;
		} else
			return new ModelAndView("redirect:/loginPage");

	}

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView pay(HttpSession session, ModelAndView model, HttpServletRequest request) {
		if (session.getAttribute("userId") != null) {
			if (request.getParameter("taxId") != null && request.getParameter("taxId") != "") {
				Integer taxId = Integer.parseInt(request.getParameter("taxId"));
				Tax tax = taxDAO.getTaxByTaxId(taxId);
				model.addObject("tax", tax);
				model.setViewName("pay");
			} else {
				model.addObject("noti", "Bạn cần khai báo thuế trước!");
				model.setViewName("tax");
			}

			return model;
		} else
			return new ModelAndView("redirect:/loginPage");

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("userId");
		return new ModelAndView("redirect:/loginPage");
	}

	@RequestMapping(value = "/admin")
	public ModelAndView adminHome(ModelAndView model) {
		User newUser = new User();
		model.addObject("user", newUser);
		model.setViewName("adminLogin");
		return model;
	}

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public ModelAndView adminLogin(ModelAndView model, @ModelAttribute User user, HttpSession session) {
		session.setAttribute("admin", "admin");
		if (userDAO.loginForAdmin(user)) {
			List<Tax> listTax = taxDAO.listTax();
			model.addObject("listTax", listTax);
			model.setViewName("manage");
		} else {
			model.addObject("noti", "Đăng nhập thất bại!");
			model.setViewName("adminLogin");
		}
		return model;
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(ModelAndView model, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			List<Tax> listTax = taxDAO.listTax();
			model.addObject("listTax", listTax);
			model.setViewName("manage");
			return model;
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/seeTaxDetail")
	public ModelAndView seeDetailTax(ModelAndView model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Tax tax = taxDAO.getTaxByUserId(userId);
			model.addObject("tax", tax);

			List<MonthlyTax> listMonthlyTax = monthlyTaxDAO.listMonthlyTax(tax.getTaxId());
			model.addObject("listMonthlytTax", listMonthlyTax);

			model.setViewName("detailTax");
			return model;
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/logoutAdmin")
	public ModelAndView logoutAdmin(ModelAndView model, HttpServletRequest request, HttpSession session) {
		session.removeAttribute("admin");

		model.setViewName("redirect:/admin");
		return model;
	}

	@RequestMapping(value = "/createMonthlyTax", method = RequestMethod.GET)
	public ModelAndView createMonthlyTax(ModelAndView model, HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("admin") != null) {
			Integer taxId = Integer.parseInt(request.getParameter("taxId"));

			MonthlyTax newMonthlyTax = new MonthlyTax(taxId);

			model.addObject("monthlyTax", newMonthlyTax);
			model.setViewName("createMonthlyTax");
			return model;
		} else
			return new ModelAndView("redirect:/admin");

	}

	@RequestMapping(value = "/saveMonthlyTax", method = RequestMethod.POST)
	public ModelAndView saveMonthlyTax(@ModelAttribute MonthlyTax monthlyTax, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			monthlyTaxDAO.saveMonthlyTax(monthlyTax);
			Tax tax = taxDAO.getTaxByTaxId(monthlyTax.getTaxId());
			return new ModelAndView("redirect:/seeTaxDetail?userId=" + tax.getUserId());
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/removeMonthlyTax", method = RequestMethod.GET)
	public ModelAndView removeMonthlyTax(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("admin") != null) {
			Integer monthlyTaxId = Integer.parseInt(request.getParameter("monthlyTaxId"));
			MonthlyTax monthlyTax = monthlyTaxDAO.getOneMonthlyTax(monthlyTaxId);
			monthlyTaxDAO.deleteOneMonthlyTax(monthlyTaxId);
			Tax tax = taxDAO.getTaxByTaxId(monthlyTax.getTaxId());
			return new ModelAndView("redirect:/seeTaxDetail?userId=" + tax.getUserId());
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/editMonthlyTax", method = RequestMethod.GET)
	public ModelAndView editMonthlyTax(HttpServletRequest request, HttpSession session) {

		if (session.getAttribute("admin") != null) {
			Integer monthlyTaxId = Integer.parseInt(request.getParameter("monthlyTaxId"));

			MonthlyTax monthlyTax = monthlyTaxDAO.getOneMonthlyTax(monthlyTaxId);

			ModelAndView model = new ModelAndView("monthlyTaxForm");

			model.addObject("monthlyTax", monthlyTax);
			return model;
		} else {
			return new ModelAndView("redirect:/admin");
		}
	}

	@RequestMapping(value = "/updateMonthlyTax", method = RequestMethod.POST)
	public ModelAndView updateMonthlyTax(@ModelAttribute MonthlyTax monthlyTax, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			System.out.print(monthlyTax);
			monthlyTaxDAO.updateMonthlyTax(monthlyTax);
			Tax tax = taxDAO.getTaxByTaxId(monthlyTax.getTaxId());
			return new ModelAndView("redirect:/seeTaxDetail?userId=" + tax.getUserId());
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/manageTaxRange", method = RequestMethod.GET)
	public ModelAndView manageTaxRange(ModelAndView model, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			List<TaxRange> listTaxRange = taxRangeDAO.getTaxRangeList();
			model.addObject("listTaxRange", listTaxRange);
			model.setViewName("taxRange");
		} else
			return new ModelAndView("redirect:/admin");
		return model;
	}

	@RequestMapping(value = "/editTaxRange", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			TaxRange taxRange = taxRangeDAO.getOneTaxRange(id);

			ModelAndView model = new ModelAndView("tax_range_form");

			model.addObject("taxRange", taxRange);

			return model;
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/saveTaxRange", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute TaxRange taxRange, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			if (taxRange.getId() == null) {
				if (taxRangeDAO.createTaxRange(taxRange)) {
					return new ModelAndView("redirect:/manageTaxRange");
				} else {
					ModelAndView model = new ModelAndView("tax_range_form");
					model.addObject("taxRange", taxRange);
					model.addObject("noti", "Nhập số dương và phần trăm <= 100");
					return model;
				}
			} else {
				if (taxRangeDAO.updateTaxRange(taxRange)) {
					return new ModelAndView("redirect:/manageTaxRange");
				} else {
					ModelAndView model = new ModelAndView("tax_range_form");
					model.addObject("taxRange", taxRange);
					model.addObject("noti", "Nhập số dương và phần trăm <= 100");
					return model;
				}
			}
		} else
			return new ModelAndView("redirect:/admin");

	}

	@RequestMapping(value = "/createTaxRange", method = RequestMethod.GET)
	public ModelAndView createTaxRange(ModelAndView model, HttpSession session) {
		TaxRange newTaxRange = new TaxRange();
		model.addObject("taxRange", newTaxRange);
		model.setViewName("tax_range_form");
		return model;
	}

	@RequestMapping(value = "/removeTaxRange", method = RequestMethod.GET)
	public ModelAndView removeTaxRange(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("admin") != null) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			taxRangeDAO.removeTaxRange(id);
			return new ModelAndView("redirect:/manageTaxRange");
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/manageUser", method = RequestMethod.GET)
	public ModelAndView manageUser(ModelAndView model, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			List<User> listUser = userDAO.list();
			model.addObject("listUser", listUser);
			model.setViewName("user");
		} else
			return new ModelAndView("redirect:/admin");

		return model;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUser(ModelAndView model, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			User newUser = new User();
			model.addObject("user", newUser);
			model.setViewName("user_form");
			return model;
		} else
			return new ModelAndView("redirect:/admin");

	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(ModelAndView model, @ModelAttribute User user, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			if (user.getUserId() == null) {
				if (userDAO.register(user)) {
					return new ModelAndView("redirect:/manageUser");
				} else {
					model.addObject("user", user);
					model.setViewName("user_form");
					model.addObject("noti", "Nhập ký tự trong khoảng 6 - 50 và không bị trùng tài khoản");
					return model;
				}
			} else {
				if (userDAO.updateUser(user)) {
					return new ModelAndView("redirect:/manageUser");
				} else {
					model.addObject("user", user);
					model.setViewName("user_form");
					model.addObject("noti", "Nhập ký tự trong khoảng 6 - 50");
					return model;
				}
			}
		} else
			return new ModelAndView("redirect:/admin");

	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			User user = userDAO.getOneUser(userId);

			ModelAndView model = new ModelAndView("user_form");

			model.addObject("user", user);

			return model;
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public ModelAndView removeUser(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("admin") != null) {
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			userDAO.deleteUser(userId);
			return new ModelAndView("redirect:/manageUser");
		} else
			return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/backLogin", method = RequestMethod.GET)
	public ModelAndView backLogin(HttpSession session, HttpServletRequest request) {
		return new ModelAndView("redirect:/loginPage");
	}

	@RequestMapping(value = "/backHome", method = RequestMethod.GET)
	public ModelAndView backHome(HttpSession session, HttpServletRequest request) {
		return new ModelAndView("redirect:/");
	}

}
