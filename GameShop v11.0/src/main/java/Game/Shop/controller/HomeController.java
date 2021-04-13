package Game.Shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Game.Shop.dao.*;
import Game.Shop.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	public static ClientAccount clientLogin;
	public static ClientAccount clientLogout;

	//public boolean bought = false;

	public static Product produs;

	public static Product produs2;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private GenreDAO genreDAO;

	@Autowired
	private CountryDAO countryDAO;

	@RequestMapping(value="/")
	public ModelAndView login(ModelAndView model) throws IOException{
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logOut(ModelAndView model) {
		clientLogout= new ClientAccount();
		if (clientLogin.getUsername().contentEquals("admin")){
			clientLogout.setUsername("admin");
		}
		else {
			clientLogout = clientDAO.get(clientLogin.getId());
		}
		model.setViewName("SuccessLogOut");
		return model;
	}


	@RequestMapping(value = "/logoutSuccess", method = RequestMethod.GET)
	public ModelAndView logOutSuccess() {

		return new ModelAndView("redirect:/");
	}


	@RequestMapping(value="/error")
	public ModelAndView Error(ModelAndView model) throws IOException{
		model.setViewName("Error");
		return model;
	}


	@RequestMapping(value="/admin")
	public ModelAndView listProduct(ModelAndView model) throws IOException{
		List<Product> listProduct = productDAO.list();
		model.addObject("listProduct", listProduct);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value="/checkClient",method = RequestMethod.GET)
	public ModelAndView listClient(HttpServletRequest request) throws IOException{
		clientLogin=new ClientAccount();
		String username = String.valueOf(request.getParameter("username"));
		String password = String.valueOf(request.getParameter("password"));
		if(username.contentEquals("admin") && password.contentEquals("admin")){
			clientLogin.setUsername("admin");
			return new ModelAndView("redirect:/admin");
		}
		else {
			ClientAccount client = clientDAO.getUsernamePassword(username, password);
			if(client==null){
				return new ModelAndView("redirect:/error");
			}
			else {
				clientLogin=client;
				return new ModelAndView("redirect:/client");
			}
		}
	}

	@RequestMapping(value="/client")
	public ModelAndView listProductSimplified(ModelAndView model) throws IOException{
		List<Product> listProduct = productDAO.list();
		Product produsGenre1 = genreDAO.findRecommandation(clientLogin.getId()).get(0);
		Product produsGenre2 = genreDAO.findRecommandation(clientLogin.getId()).get(1);
		Product produsCountry = countryDAO.findRecommandation(clientLogin.getId());
		model.addObject("listProduct", listProduct);
		model.addObject("genre1",produsGenre1);
		model.addObject("genre2",produsGenre2);
		model.addObject("country1",produsCountry);

		model.setViewName("ClientHome");

		return model;
	}

	@RequestMapping(value = "/newClient", method = RequestMethod.GET)
	public ModelAndView newClient(ModelAndView model) {
		ClientAccount newClient = new ClientAccount();
		model.addObject("client", newClient);
		model.setViewName("ClientForm");
		return model;
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public ModelAndView addClient(@ModelAttribute ClientAccount client) {
		clientDAO.saveOrUpdate(client);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/newProduct", method = RequestMethod.GET)
	public ModelAndView newProduct(ModelAndView model) {
		Product newProduct = new Product();
		model.addObject("product", newProduct);
		model.setViewName("ProductForm");
		return model;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute Product product) {
		productDAO.saveOrUpdate(product);
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public ModelAndView buyProduct(@ModelAttribute OrderItem item,ModelAndView model) {

		orderDAO.addOrder(clientLogin.getId(),produs.getId(),item.getQuantity(), item.getRating());
		model.setViewName("OrderSuccess");
		return model;
	}


	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request) {
		String productId = String.valueOf(request.getParameter("id"));
		productDAO.delete(productId);
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public ModelAndView editProduct(HttpServletRequest request) {
		String productId = String.valueOf(request.getParameter("id"));
		Product product = productDAO.get(productId);
		ModelAndView model = new ModelAndView("ProductForm");
		model.addObject("product", product);

		return model;
	}

	@RequestMapping(value = "/editPopularity", method = RequestMethod.GET)
	public ModelAndView editPopularity(HttpServletRequest request) {
		String productId = String.valueOf(request.getParameter("id"));
		List<Country> countries = countryDAO.list(productId);
		produs2=productDAO.get(productId);
		ModelAndView model = new ModelAndView("PopularityForm");
		model.addObject("countries", countries);
		return model;
	}

	@RequestMapping(value = "/productDetails", method = RequestMethod.GET)
	public ModelAndView getProduct(HttpServletRequest request) {
		String productId = String.valueOf(request.getParameter("id"));
		Product product = productDAO.get(productId);
		produs=product;
		OrderItem item= new OrderItem();
		ModelAndView model = new ModelAndView("GameDetails");
		model.addObject("product", product);
		model.addObject("item",item);

		return model;
	}

	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView myAccount(HttpServletRequest request) {
		String clientId = String.valueOf(clientLogin.getId());
		ClientAccount client  = clientDAO.get(clientId);
		ModelAndView model = new ModelAndView("EditAccount");
		model.addObject("client", client);

		return model;
	}

	@RequestMapping(value = "/editAccount", method = RequestMethod.POST)
	public ModelAndView editAccount(@ModelAttribute ClientAccount client) {
		clientDAO.saveOrUpdate(client);
		return new ModelAndView("redirect:/client");
	}

	@RequestMapping(value = "/newCountry", method = RequestMethod.GET)
	public ModelAndView newCountry(ModelAndView model) {
		Country newProduct = new Country();
		model.addObject("country", newProduct);
		model.setViewName("AddPopularity");
		return model;
	}


	@RequestMapping(value = "/addPopularity", method = RequestMethod.POST)
	public ModelAndView addCountry(@ModelAttribute Country country) {
		country.setProductId(produs2.getId());
		country.setProductName(produs2.getName());
		countryDAO.saveOrUpdate(country);
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/deleteCountry", method = RequestMethod.GET)
	public ModelAndView deleteCountry(HttpServletRequest request) {
		String countryId = String.valueOf(request.getParameter("name"));
		countryDAO.delete(countryId);
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/orderSuccess", method = RequestMethod.GET)
	public ModelAndView OrderSuccess() {
		return new ModelAndView("redirect:/client");
	}



	@RequestMapping(value="/myCart")
	public ModelAndView myCart(ModelAndView model) throws IOException{
		List<OrderItem> listItem = orderDAO.list(clientLogin.getId());
		model.addObject("listitem", listItem);
		model.setViewName("Cart");

		return model;
	}

	@RequestMapping(value = "/deleteCart", method = RequestMethod.GET)
	public ModelAndView deleteCart(HttpServletRequest request) {
		int orderId =Integer.valueOf(request.getParameter("id"));
		orderDAO.delete(orderId);
		return new ModelAndView("redirect:/myCart");
	}

	@RequestMapping(value = "/buyAll", method = RequestMethod.GET)
	public ModelAndView buyAll(ModelAndView model) {
		List<OrderItem> list= orderDAO.list(clientLogin.getId());
		List<Product> produsRate = orderDAO.findRecommandation(clientLogin.getId());
		Product produsRate1 = new Product();
		Product produsRate2 = new Product();
		if(produsRate == null){
			produsRate1.setImage("https://i.imgur.com/Ad0gQ88.png");
			produsRate2.setImage("https://i.imgur.com/krIZe4u.jpg");
		}
		else {
			produsRate1 = produsRate.get(0);
			produsRate2 = produsRate.get(1);
		}
		for (OrderItem i: list) {
			orderDAO.deleteFinal(i.getOrderId());
		}
		model.addObject("rate1",produsRate1);
		model.addObject("rate2",produsRate2);
		model.setViewName("BuySuccess");
		return model;
	}

	@RequestMapping(value = "/buySuccess", method = RequestMethod.GET)
	public ModelAndView BuySuccess() {
		return new ModelAndView("redirect:/client");
	}


}
