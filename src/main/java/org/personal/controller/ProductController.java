/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.controller;

import org.personal.model.Product;
import org.personal.model.Products;
import java.util.List;
import javax.inject.Inject;
import org.personal.model.Categories;
import org.personal.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author elida
 */
@Controller
@SessionAttributes({"editableProduct"})
public class ProductController {

    private final static Logger L = LoggerFactory.getLogger(MainController.class);
    private Products productsDao;
    @Inject
    private Categories categoriesDao;

    @Inject
    public ProductController(final Products productsDao) {
        this.productsDao = productsDao;
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productsDao.getAll();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoriesDao.getAll();
    }

    @ModelAttribute("product")
    public Product getProduct() {
        return new Product();
    }

    @ModelAttribute("editableProduct")
    public Product getEditableProducts() {
        return new Product();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String categories() {
        return "products";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public @ResponseBody
    String saveProduct(@RequestBody Product product, final BindingResult result) {
        System.out.println("product:: " + product);
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
        }
        productsDao.create(product);
        for (Product p : productsDao.getAll()) {
            System.out.println("insert:: " + p.toString());
        }
        if (product == null) {
            return "{\"status\":\"fail\"}";
        } else {
            L.info("Added product " + product);
            return "{\"status\":\"success\",\"hash\":\"" + product.getHash() + "\"}";
        }

    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    @ResponseBody
    public String editProduct(@Validated @RequestBody Product product, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
            final ModelAndView view = new ModelAndView("products");
            view.addObject("editWithErrors", "true");
            return "{\"status\":\"fail\"}";
        }
        productsDao.update(product);
        L.info("Edited product: " + product);
        return "{\"status\":\"success\"}";
    }

    @RequestMapping(value = "/removeProduct/{hash}", method = RequestMethod.POST)
    public ModelAndView removePerson(@PathVariable("hash") final String hash) {
        productsDao.delete(hash);
        L.debug("Attempting to remove product with hash: {}", hash);
        return new ModelAndView("redirect:/products");
    }

}
