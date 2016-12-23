/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.controller;

import org.personal.model.Categories;
import org.personal.model.Category;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author elida
 */
@Controller
@SessionAttributes({"editableCategory"})
public class CategoryController {

    private final static Logger L = LoggerFactory.getLogger(MainController.class);
    @Inject
    private Categories categoriesDao;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoriesDao.getAll();
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }

    @ModelAttribute("editableCategory")
    public Category getEditableCategories() {
        return new Category();
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories() {
        return "categories";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public ModelAndView saveCategory(@Validated @ModelAttribute("category") final Category category, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
            return new ModelAndView("categories");
        }
        categoriesDao.create(category);
        L.info("Added product: " + category);
        return new ModelAndView("redirect:/categories");
    }

    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public ModelAndView editCategory(@Validated @ModelAttribute("editableCategory") final Category category, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
            final ModelAndView view = new ModelAndView("categories");
            view.addObject("editWithErrors", "true");
            return view;
        }
        categoriesDao.update(category);
        L.info("Edited product: " + category);
        return new ModelAndView("redirect:/categories");
    }

    @RequestMapping(value = "/removeCategory/{id}", method = RequestMethod.POST)
    public ModelAndView removeCategory(@PathVariable("id") final Long id) {
        categoriesDao.delete(id);
        L.debug("Attempting to remove product with hash: {}", id);
        return new ModelAndView("redirect:/categories");
    }

}
