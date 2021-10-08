package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable Long id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());

		model.addAttribute("products", productService.getAllProductByCategoryid(id));

		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable Long id) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "viewProduct";
	}

	@GetMapping("/cart/removeItem/{index}")
	public String cartIteamRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

		return "checkout";
	}

}
