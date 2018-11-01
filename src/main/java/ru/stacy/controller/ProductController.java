package ru.stacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.stacy.entity.Product;
import ru.stacy.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("RETURNING PRODUCTS:" + productService.listAllProducts());
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        System.out.println("SHOW PRODUCT: " + id);
        return "productshow";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        System.out.println("EDIT PRODUCT: " + id);
        return "productform";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        System.out.println("PRODUCT NEW");
        return "productform";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        System.out.println("SAVE PRODUCT");
        return "redirect:/products";
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        System.out.println("DELETE PRODUCT: " + id);
        return "redirect:/products";
    }
}
